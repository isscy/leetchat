package cn.ff.leetchat.sys.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "leetchat")
public class BasalProperties {

	private ActiveProperties active = new ActiveProperties();

	public ActiveProperties getActive() {
		return active;
	}

	public void setActive(ActiveProperties active) {
		this.active = active;
	}
}
