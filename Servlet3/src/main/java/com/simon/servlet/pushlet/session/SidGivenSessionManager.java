package com.simon.servlet.pushlet.session;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.Protocol;
import nl.justobjects.pushlet.core.Session;
import nl.justobjects.pushlet.core.SessionManager;
import nl.justobjects.pushlet.util.PushletException;

/**
 * 通过获取外部指定的sessionId来生成Session
 * 前端js在PL._init()后,指定PL.sessionId=userId来完成前端设置.
 * @author simon
 * @date Apr 18, 2015
 */
public class SidGivenSessionManager extends SessionManager {
	
//	public static final String SID = "p_mid";
	
	@Override
	public Session createSession(Event anEvent) throws PushletException {
//		String sid = anEvent.getField(SID);
		String sid = anEvent.getField(Protocol.P_ID);
		if (sid == null || sid.replaceAll(" ", "").length() == 0) {
			throw new PushletException("请求参数中缺少Pushlet的Session id参数!");
		}
		return Session.create(sid);
	}
	
}
