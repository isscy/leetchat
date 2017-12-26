package cn.ff.leetchat.socket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;


public class SocketHandler extends AbstractWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("serverReceived:" + message.getPayload());
		Thread.sleep(1000);
		session.sendMessage(new TextMessage("hello!"));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("Connection Established");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("Connection Closed! Status" + status);
	}
}
