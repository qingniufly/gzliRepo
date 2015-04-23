package com.simon.servlet.server.send.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "asyncSSE", urlPatterns = {"/async-sse"}, asyncSupported = true)
public class AsyncSSEServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

//	@Resource(name = "concurrent/DefaultManagedExecutorService")
//	private ManagedExecutorService managedExecutorService;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");
		
		final String msg = req.getParameter("msg");
		
		final AsyncContext ac = req.startAsync();
		final PrintWriter writer = resp.getWriter();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// echo msg 5 times
				for (int i = 0; i < 5; i++) {
					// SSE data field
					writer.write("data:" + msg + "\n\n");
					writer.flush();
					if (i == 4) {  // last
						// SSE write end
						writer.write("event:close\n\n");
						writer.flush();
					}
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				ac.complete();
			}
		};
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.submit(runnable);
		
//		ManagedExecutorService managedExecutorService;
//		try {
//			InitialContext ctx = new InitialContext();
//			managedExecutorService = (ManagedExecutorService)ctx.lookup("java:comp/DefaultManagedExecutorService");
//			managedExecutorService.submit(runnable);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}
	
}
