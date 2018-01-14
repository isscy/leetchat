package cn.ff.leetchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/socket");//端点
	}

	/**
	 * 简单的消息代理
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//消息代理处理这两个前缀的信息
		//registry.enableSimpleBroker("/queue", "/topic");


		registry.enableStompBrokerRelay("/topic", "/queue");



		// 以应用程序为目的地的消息会直接路由到带有MessageMapping的注解的控制权方法中。而发送到代理上的消息，
		// 包括MessageMapping方法返回值形成的消息将会路由到代理上，并最终发送到订阅这些目的地的客户端
		registry.setApplicationDestinationPrefixes("/app");//发往应用程序的消息
	}
}

/*
@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketConfigurer{
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketHandler(), "socket");
	}

	@Bean
	public SocketHandler socketHandler(){
		return new SocketHandler();
	}
}
*/