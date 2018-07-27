package com.luv2code.springdemo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.luv2code.springdemo")
@PropertySource({"classpath:persistence-mysql.properties","classpath:security-persistence-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer{
	@Autowired
	private Environment env;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Bean
	public DataSource myDataSource() {
		 // create connection pool
		 ComboPooledDataSource myDataSource = new ComboPooledDataSource();
		 // set the jdbc driver
		 try {
		 myDataSource.setDriverClass("com.mysql.jdbc.Driver");
		 }
		 catch (PropertyVetoException exc) {
			 throw new RuntimeException(exc);
		 }
		 // for sanity's sake, let's log url and user ... just to make sure we are reading the data
		 logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
		 logger.info("jdbc.user=" + env.getProperty("jdbc.user"));
		 
		 // set database connection props
		 myDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		 myDataSource.setUser(env.getProperty("jdbc.user"));
		 myDataSource.setPassword(env.getProperty("jdbc.password"));
		 
		 // set connection pool props
		 myDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		 myDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		 myDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		 myDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
	
		 return myDataSource;
	}
	
	// define a bean for security data source
	 @Bean
	public DataSource securityDataSource() {

		 // create connection pool
		 ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
	
		 // set the jdbc driver class
		 try {
			 securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
		 } catch (PropertyVetoException exc) {
			 logger.info("Matthias:"+ exc.getMessage());
			 throw new RuntimeException(exc);
		 }
	
		 // log this info  just to make sure we are REALLY reading data from properties file
		 logger.info(">>> security.jdbc.url=" + env.getProperty("security.jdbc.url"));
		 logger.info(">>> security.jdbc.user=" + env.getProperty("security.jdbc.user"));
	
		 // set database connection props
		 securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
		 securityDataSource.setUser(env.getProperty("security.jdbc.user"));
		 securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
	
		 // set connection pool props
		 securityDataSource.setInitialPoolSize(getIntProperty("security.connection.pool.initialPoolSize"));
		 securityDataSource.setMinPoolSize(getIntProperty("security.connection.pool.minPoolSize"));
		 securityDataSource.setMaxPoolSize(getIntProperty("security.connection.pool.maxPoolSize"));
		 securityDataSource.setMaxIdleTime(getIntProperty("security.connection.pool.maxIdleTime"));
	
		 return securityDataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		 // create session factorys
		 LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		 
		 // set the properties
		 sessionFactory.setDataSource(myDataSource());
		 sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		 sessionFactory.setHibernateProperties(getHibernateProperties());
		 
		 return sessionFactory;
	}
	
	private Properties getHibernateProperties() {
		 // set hibernate properties
		 Properties props = new Properties();
		 props.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
		 props.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
		 return props;
	} 
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
	

	private int getIntProperty(String propertyName) {
		String propertyValue = env.getProperty(propertyName);
		int propertyValueInt = Integer.parseInt(propertyValue);
		return propertyValueInt;
	}
	

}
