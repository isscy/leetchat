package cn.ff.leetchat.jms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service
public class Consumer2 {
	private final static Logger logger = LoggerFactory.getLogger(Consumer2.class);

	// 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
	//@JmsListener(destination = "mytest.queue")
	public void receiveQueue(String text) {
		logger.warn("Consumer2收到的报文为:"+text);
	}


}
