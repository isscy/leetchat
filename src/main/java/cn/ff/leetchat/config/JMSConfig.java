package cn.ff.leetchat.config;

import cn.ff.leetchat.sys.entity.BasalProperties;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms
public class JMSConfig {

	@Autowired
	private BasalProperties basalProperties;




	@Bean
	public ConnectionFactory connectionFactory() {
		PooledConnectionFactory pool = new PooledConnectionFactory();
		pool.setConnectionFactory(new ActiveMQConnectionFactory(basalProperties.getActivemq().getBrokerUrl()));
		// TODO 用户名密码
		return pool;

	}

	@Bean
	public Queue queueDestination(){
		return new ActiveMQQueue("myqueue") ;
	}
	@Bean
	public Topic topicDestination(){
		return new ActiveMQTopic("mytopic");
	}

	@Bean(name = "jmsTemplate")
	public JmsTemplate jmsTemplate(){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setReceiveTimeout(10000);
		jmsTemplate.setDefaultDestination(queueDestination());
		jmsTemplate.setPubSubDomain(false);// 非 pub/sub（发布/订阅） 即队列模式
		return jmsTemplate;
	}

	@Bean(name = "jmsTopicTemplate")
	public JmsTemplate jmsTopicTemplate(){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		jmsTemplate.setReceiveTimeout(10000);
		jmsTemplate.setDefaultDestination(topicDestination());
		jmsTemplate.setPubSubDomain(true);//订阅模式
		return jmsTemplate;
	}
/*
	@Bean
	public void mailListener1(){

	}

*/








}
