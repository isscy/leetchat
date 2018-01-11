package cn.ff.leetchat.jms.service.impl;

import cn.ff.leetchat.jms.entity.Mail;
import cn.ff.leetchat.jms.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service("topic")
public class TopicImpl implements Producer{

	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate){
		this.jmsTopicTemplate = jmsTemplate;

	}
	@Override
	public void sendMail(Mail mail) {
		jmsTopicTemplate.convertAndSend(mail);
	}
}
