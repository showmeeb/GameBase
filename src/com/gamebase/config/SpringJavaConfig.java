package com.gamebase.config;

import java.util.Arrays;

import javax.naming.NamingException;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import com.gamebase.general.model.BytesToDateConverter;
import com.gamebase.member.model.filter.CookieFilter;


@Configuration
@EnableTransactionManagement
@EnableRedisRepositories(basePackages = "com.gamebase.general.model")
@ComponentScan(basePackages = { "com.gamebase", "com.gamebase.general", "com.gamebase.article",
		"com.gamebase.member", "com.gamebase.tradesystem" })
//SpringJavaConfig設定與資料庫有關的操作
@Import(com.gamebase.config.SpringWebSocketJavaConfig.class)
public class SpringJavaConfig {

	@Bean
	public DataSource dataSource() {
		JndiObjectFactoryBean factory = new JndiObjectFactoryBean();
		factory.setJndiName("java:comp/env/jdbc/GameBase");
		factory.setProxyInterface(DataSource.class);

		try {

			// look up context JNDI object
			factory.afterPropertiesSet();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (DataSource) factory.getObject();

	}

	@Bean
	public RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName("localhost");
		factory.setPort(6379);
		factory.setDatabase(0); // default is DB0

		return factory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(connectionFactory());
		return template;
	}
	@Bean
    public RedisCustomConversions redisCustomConversions(BytesToDateConverter bytesToDate) {
        return new RedisCustomConversions(Arrays.asList(bytesToDate));
    }

	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(dataSource()).configure("hibernate.cfg.xml").buildSessionFactory();
	}

	@Bean
	public PlatformTransactionManager transactionManagement() {
		return new HibernateTransactionManager(sessionFactory());

	}
	
}
