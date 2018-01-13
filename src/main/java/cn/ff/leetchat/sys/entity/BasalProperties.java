package cn.ff.leetchat.sys.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@Configuration
@Component
@ConfigurationProperties(prefix = "leetchat")
public class BasalProperties {

	private ActiveProperties activemq = new ActiveProperties();


	public ActiveProperties getActivemq() {
		return activemq;
	}

	public void setActivemq(ActiveProperties activemq) {
		this.activemq = activemq;
	}


}

