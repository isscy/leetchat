package cn.ff.leetchat.sys.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
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
