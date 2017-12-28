package cn.ff.leetchat.socket.controller;

import cn.ff.leetchat.socket.entity.Shout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

	private static final Logger logger = LoggerFactory.getLogger(SocketController.class);

	/**
	 * 处理发往 /app/marco 目的地的消息
	 */
	@MessageMapping("/marco")
	public void handleShout(Shout incoming ){
		logger.info("Received message: " + incoming.getMessage());

	}

	@SubscribeMapping({"/marco2"})
	public Shout handleSubscription(){
		Shout outgoing = new Shout();
		outgoing.setMessage("good luck!");
		return outgoing;
	}
}
