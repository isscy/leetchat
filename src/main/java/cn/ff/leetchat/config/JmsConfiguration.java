package cn.ff.leetchat.config;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import java.util.Collections;

@Configuration
@EnableJms
public class JmsConfiguration {

//	/**
//	 * topic模式的ListenerContainer
//	 * @param activeMQConnectionFactory
//	 * @return
//	 */
//	@Bean
//	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
//		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//		bean.setPubSubDomain(true);
//		bean.setConnectionFactory(activeMQConnectionFactory);
//		/**
//		 * 使用消息转换器
//		 */
//		bean.setMessageConverter(jacksonJmsMessageConverter());
//		return bean;
//	}
//	/**
//	 * queue模式的ListenerContainer
//	 * @param activeMQConnectionFactory
//	 * @return
//	 */
//	@Bean
//	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory activeMQConnectionFactory) {
//		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//		bean.setConnectionFactory(activeMQConnectionFactory);
//		/**
//		 * 使用消息转换器
//		 */
//		bean.setMessageConverter(jacksonJmsMessageConverter());
//		return bean;
//	}
//	/**
//	 * 消息转换器
//	 * @return
//	 */
//	@Bean
//	public MessageConverter jacksonJmsMessageConverter() {
//		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//		converter.setTargetType(MessageType.TEXT);
//		converter.setTypeIdPropertyName("_type");
//		return converter;
//	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("mytest.queue") ;
	}





}
