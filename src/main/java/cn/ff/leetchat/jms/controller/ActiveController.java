package cn.ff.leetchat.jms.controller;

import cn.ff.leetchat.jms.entity.Mail;
import cn.ff.leetchat.jms.service.impl.ProducerImpl;
import cn.ff.leetchat.jms.service.impl.TopicImpl;
import cn.ff.leetchat.socket.entity.Message;
import cn.ff.leetchat.socket.utils.MyWebSocketHandler;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ActiveController {

	private MyWebSocketHandler handler=new MyWebSocketHandler();

	@Autowired
	private ProducerImpl producer;
	@Autowired
	private TopicImpl topic;

	@RequestMapping(value="/produce", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public void produce(@ModelAttribute("mail")Mail mail) throws Exception{
		Message msg = new Message();
		msg.setText("向队列myquene添加一条消息:"+mail.toString());
		handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		producer.sendMail(mail);
	}

	@RequestMapping(value="/topic",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public void topic(@ModelAttribute("mail")Mail mail) throws Exception{
		Message msg = new Message();
		msg.setText("向话题mytopic发布一条消息:"+mail.toString());
		handler.broadcast(new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
		topic.sendMail(mail);
	}

	@RequestMapping("demo")
	public String demo(HttpServletRequest request){
		request.getSession().setAttribute("uid", (long)11);
		return "demo";
	}




}
