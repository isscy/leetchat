package cn.ff.leetchat.jms.service;

import cn.ff.leetchat.jms.entity.Mail;

public interface Producer {

	void sendMail(Mail mail);
}
