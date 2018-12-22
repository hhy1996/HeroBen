package com.socket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint("/websocket")
public class Socket {
	public static Map<String, Stack<Session>> sessionMap = new HashMap<String, Stack<Session>>();
	private Session session;

	@OnOpen
	public void startSocket(Session session) {
		this.session = session;
	}

	@OnMessage
	public void getMessgae(Session session, String str, boolean last) {
		if (session.isOpen()) {
			try {
				Gson gson = new Gson();
				Message msg = gson.fromJson(str, Message.class);
				Message toMessage = msg;
				toMessage.setFrom(msg.getId());
				toMessage.setTo(msg.getTo());
				if (msg.getMsg().equals("newUser")) {
					Stack<Session> sessionStack;
					if (sessionMap.containsKey(msg.getId())) {
						sessionStack = sessionMap.get(msg.getId());
						sessionStack.push(session);
					} else {
						sessionStack = new Stack<Session>();
						sessionStack.push(session);
					}
					sessionMap.put(msg.getId(), sessionStack);
				} else {
					Stack<Session> sessionStack = sessionMap.get(msg.getTo());
					while (!sessionStack.isEmpty()) {
						Session toSession = sessionStack.peek();
						if (toSession != null && toSession.isOpen()) {
							toSession.getBasicRemote().sendText(gson.toJson(toMessage).toString(), last);
							break;
						} else {
							sessionStack.pop();
						}
					}
					if (sessionStack.isEmpty()) {
						toMessage.setMsg("系统提示:对方不在线,消息发送失败");
						toMessage.setFrom("系统");
						session.getBasicRemote().sendText(gson.toJson(toMessage).toString(), last);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		}
	}
}