package com.simon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/chat", asyncSupported = true)
public class ChatServlet extends HttpServlet {

	private static final long serialVersionUID = 1973034221073583489L;

	private Map<String, AsyncContext> asyncContexts = new ConcurrentHashMap<String, AsyncContext>();

	private BlockingQueue<String> messages = new LinkedBlockingQueue<String>();

	private Thread notifier = new Thread(new Runnable() {

		@SuppressWarnings("unchecked")
		public void run() {
			while (true) {
				try {
					String message = messages.take();
					System.out.println(message);
					Map<String, String> params = new Gson().fromJson(message, Map.class);
					String username = params.get("username");
					String id = params.get("contextId");

					System.out.println("username is " + username);
					System.out.println("ID is " + id);
					AsyncContext context = asyncContexts.get(id);
					try {
						sendMessage(context.getResponse().getWriter(), message);
					} catch (Exception e) {
						asyncContexts.values().remove(context);
					}
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	});

	private void sendMessage(PrintWriter writer, String msg) {
		System.out.println(msg);
		writer.print(msg.length());
		writer.print(";");
		writer.print(msg);
		writer.print(";");
		writer.flush();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		// Access-Control-Allow-Origin header
		resp.setHeader("Access-Control-Allow-Origin", "*");

		final String uuid = UUID.randomUUID().toString();
		PrintWriter writer = resp.getWriter();
		writer.print(uuid);
		writer.print(";");
		for (int i = 0; i < 1024; i++) {
			writer.print(" ");
		}
		writer.print(";");
		writer.flush();
		final AsyncContext ac = req.startAsync();
		ac.addListener(new AsyncListener() {

			@Override
			public void onTimeout(AsyncEvent arg0) throws IOException {
				asyncContexts.remove(uuid);
			}

			@Override
			public void onStartAsync(AsyncEvent arg0) throws IOException {
				// TODO Auto-generated method stub

			}

			@Override
			public void onError(AsyncEvent arg0) throws IOException {
				asyncContexts.remove(uuid);
			}

			@Override
			public void onComplete(AsyncEvent arg0) throws IOException {
				asyncContexts.remove(uuid);
			}
		});
		asyncContexts.put(uuid, ac);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uuid = req.getParameter("metadata.id");
		AsyncContext context = asyncContexts.get(uuid);
		if (context == null) {
			return ;
		}
		if ("close".equals(req.getParameter("metadata.type"))) {
			context.complete();
			return ;
		}

		String username = req.getParameter("username");
		Map<String, String> message = new LinkedHashMap<>();
		message.put("username", username);
		message.put("contextId", uuid);
		try {
			messages.put(new Gson().toJson(message));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
		messages.clear();
		asyncContexts.clear();
		notifier.interrupt();
		super.destroy();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		notifier.start();
	}


}
