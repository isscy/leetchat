package cn.ff.leetchat.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

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

	//@Bean
	ConnectionFactory jmsConnectionFactory() {
		PooledConnectionFactory pool = new PooledConnectionFactory();
		pool.setConnectionFactory(new ActiveMQConnectionFactory("stomp://localhost:61613"));
		return pool;

	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("mytest.queue") ;
	}

	public Queue queueDestination(){
		\
	}





}
