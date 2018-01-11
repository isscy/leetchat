package cn.ff.leetchat.socket.entity;

import java.util.Date;

public class Message {

	private long from;	//发送者
	private String fromName;	//发送者

	private long to;	//接受
	private String text;
	private Date date;


	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
