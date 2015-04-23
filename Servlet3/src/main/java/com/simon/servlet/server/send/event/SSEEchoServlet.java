package com.simon.servlet.server.send.event;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SSEEcho", urlPatterns = {"/sseecho"})
public class SSEEchoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// set content type
		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");
		String msg = req.getParameter("msg");
		PrintWriter writer = resp.getWriter();
		// send sse data
		writer.write("data:" + msg + "\n\n");
	}
	
}
