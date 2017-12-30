package cn.ff.leetchat.jms.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	/*
	@JmsListener(destination = "portal.admin.topic",containerFactory = "jmsListenerContainerTopic") // 监听指定消息主题
	public void receiveTopic(String message) {
		System.out.println(message);
	}

	@JmsListener(destination = "portal.admin.queue",containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
	public void receiveQueue(String message) {
		System.out.println(message);
	}
	*/
	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	@JmsListener(destination = "mytest.queue")
	public void receiveQueue(String text) {
		System.out.println("Consumer收到的报文为:"+text);
	}


}
