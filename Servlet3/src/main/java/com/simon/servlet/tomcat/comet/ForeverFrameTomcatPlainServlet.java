package com.simon.servlet.tomcat.comet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;
/**
 * Tomcat CometProcessor 实现
 * 需要设置Tomcat的配置文件server.xml中的connector的protocol为org.apache.coyote.http11.Http11NioProtocol
 * @author simon
 * @date Apr 20, 2015
 */
public class ForeverFrameTomcatPlainServlet extends HttpServlet implements CometProcessor {

	private static final long serialVersionUID = 1L;

	private List<HttpServletResponse> connections = new ArrayList<>();

	@Override
	public void init(ServletConfig config) throws ServletException {
		Timer timer = new Timer(false);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				synchronized (connections) {
					for (HttpServletResponse resp : connections) {
						try {
							PrintWriter writer = resp.getWriter();
							writer.write(new Date().toString());
							writer.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		}, 1000, 1000);
		super.init(config);
	}

	@Override
	public void event(CometEvent event) throws IOException, ServletException {
//		HttpServletRequest req = event.getHttpServletRequest();
		HttpServletResponse resp = event.getHttpServletResponse();
		switch (event.getEventType()) {
		case BEGIN:
			synchronized (connections) {
				connections.add(resp);
				resp.getWriter().write(new char[256]);
			}
			break;
		case READ:
			break;
		case ERROR:
		case END:
			synchronized (connections) {
				connections.remove(resp);
			}
			resp.getWriter().close();
			event.close();
			break;
		default:
			break;
		}
	}

}
