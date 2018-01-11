package cn.ff.leetchat.jms.service.impl;

import cn.ff.leetchat.jms.entity.Mail;
import cn.ff.leetchat.jms.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;

@Service("producer")
public class ProducerImpl implements Producer{
/*
	@Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
	private JmsMessagingTemplate jmsTemplate;
	@Autowired
	private Queue queue;
	 //发送消息，destination是发送到的队列，message是待发送的消息
	public void sendMessage(Destination destination, final String message){
		jmsTemplate.convertAndSend(queue, message);
	}
	*/

	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;
	/* 这个不需要吧
	public void setJmsTemplate(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}*/
	@Override
	public void sendMail(Mail mail){
		jmsTemplate.convertAndSend(mail);
	}



}
