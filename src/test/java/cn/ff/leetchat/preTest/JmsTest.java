package cn.ff.leetchat.preTest;

import cn.ff.leetchat.jms.service.Producer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.transport.stomp.Stomp;
import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsTest {

	@Autowired
	private Producer producer;
	@Autowired
	private Queue queue;


	@Test
	public void contextLoads() throws InterruptedException {
		Destination destination = new ActiveMQQueue("mytest.queue");

		for(int i=0; i<100; i++){
			producer.sendMessage(queue, "myname is chhliu!!!");
		}
	}

	@Test
	public void tt() throws Exception {
		StompConnection connection = new StompConnection();
		connection.open("localhost", 61613);

		connection.connect("test", "123");
		StompFrame connect = connection.receive(500000);

		if(!connect.getAction().equals(Stomp.Responses.CONNECTED)) {
			throw new Exception ("Not connected");
		}

		connection.begin("tx1");
		connection.send("/queue/test", "message1", "tx1", null);
		connection.send("/queue/test", "message2", "tx1", null);
		connection.commit("tx1");

		connection.subscribe("/queue/test", Stomp.Headers.Subscribe.AckModeValues.CLIENT);

		connection.begin("tx2");

		StompFrame message = connection.receive(5000);
		System.out.println(message.getBody());
		connection.ack(message, "tx2");

		message = connection.receive();
		System.out.println(message.getBody());
		connection.ack(message, "tx2");

		connection.commit("tx2");

		connection.disconnect();
	}


/*
	public void test2() throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("test","123","tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession();
	}*/






}
