package cn.ff.leetchat.preTest;

import cn.ff.leetchat.jms.service.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import javax.jms.Queue;

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
}
