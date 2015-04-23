package com.simon.servlet.server.send.event;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.servlets.EventSource;
import org.eclipse.jetty.servlets.EventSourceServlet;

/**
 * 需要设置tomcat的server.xml中的
 * <Connector protocol="org.apache.coyote.http11.Http11NioProtocol" />
 * @author simon
 * @date Apr 23, 2015
 */
public class ServerSendEventServlet extends EventSourceServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected EventSource newEventSource(HttpServletRequest request) {
		return new MovementEventSource(800, 600, 20);
	}
	
}
