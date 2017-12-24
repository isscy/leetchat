package cn.ff.leetchat.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;
@Configuration
public class DBConfig {

	@Autowired
	private Environment environment;


	@Bean(name="dataSource")
	public DruidDataSource dataSource() throws PropertyVetoException {
		DruidDataSource dataSource = new DruidDataSource();

		dataSource.setDriverClassName(environment.getProperty("db.driverClassName"));
		dataSource.setUrl(environment.getProperty("db.url"));
		dataSource.setUsername(environment.getProperty("db.username"));
		dataSource.setPassword(environment.getProperty("db.password"));


		return dataSource;
	}
}
