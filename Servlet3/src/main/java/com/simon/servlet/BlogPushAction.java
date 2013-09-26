package com.simon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 负责客户端的推送
 *
 * @author yongboy
 * @date 2011-1-13
 * @version 1.0
 */
@WebServlet(urlPatterns = { "/blogpush" }, asyncSupported = true)
public class BlogPushAction extends HttpServlet {
	private static final long serialVersionUID = 8546832356595L;
	private static final Logger log = LoggerFactory
			.getLogger(BlogPushAction.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("Cache-Control", "private");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter writer = response.getWriter();

		// 创建Comet Iframe
		writer.println("<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">");
		writer.println("<script type=\"text/javascript\">var comet = window.parent.comet;</script>");
		writer.flush();

		final AsyncContext ac = request.startAsync();
		ac.setTimeout(10 * 60 * 1000);// 10分钟时间;tomcat7下默认为10000毫秒

		ac.addListener(new AsyncListener() {
			public void onComplete(AsyncEvent event) throws IOException {
				log.info("the event : " + event.toString()
						+ " is complete now !");
				NewBlogListener.ASYNC_AJAX_QUEUE.remove(ac);
			}

			public void onTimeout(AsyncEvent event) throws IOException {
				log.info("the event : " + event.toString()
						+ " is timeout now !");

				// 尝试向客户端发送超时方法调用，客户端会再次请求/blogpush，周而复始
				log.info("try to notify the client the connection is timeout now ...");
				String alertStr = "<script type=\"text/javascript\">comet.timeout();</script>";
				writer.println(alertStr);
				writer.flush();
				writer.close();

				NewBlogListener.ASYNC_AJAX_QUEUE.remove(ac);
			}

			public void onError(AsyncEvent event) throws IOException {
				log.info("the event : " + event.toString() + " is error now !");
				NewBlogListener.ASYNC_AJAX_QUEUE.remove(ac);
			}

			public void onStartAsync(AsyncEvent event) throws IOException {
				log.info("the event : " + event.toString()
						+ " is Start Async now !");
			}
		});

		NewBlogListener.ASYNC_AJAX_QUEUE.add(ac);
	}
}
