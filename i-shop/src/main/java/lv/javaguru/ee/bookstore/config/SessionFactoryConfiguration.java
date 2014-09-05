package lv.javaguru.ee.bookstore.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@EnableTransactionManagement
@Configuration
public class SessionFactoryConfiguration {
	
	@Autowired
	private LiquibaseSchemaUpdate liquibaseSchemaUpdate;


	@Bean(destroyMethod = "close")
	public DataSource dataSource(
			@Value("${database.driverClass}") String driver,
			@Value("${database.jdbcUrl}") String url,
			@Value("${database.userName}") String user,
			@Value("${database.password}") String password) throws PropertyVetoException {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setDefaultAutoCommit(false);
		
		liquibaseSchemaUpdate.execute(dataSource);

		return dataSource;
	}

	@Bean
	public Properties hibernateProperties(
			@Value("${hibernate.dialect}") String dialect,			
			@Value("${hibernate.show_sql}") boolean showSql,
			@Value("${hibernate.format_sql}") boolean formatSql,
			@Value("${hibernate.hbm2ddl.auto}") String hbm2ddl) {

		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.format_sql", formatSql);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddl);

		return properties;
	}

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource,
	                                     @Value("${hibernate.packagesToScan}") String packagesToScan,
	                                     @Qualifier("hibernateProperties") Properties properties) throws Exception {

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setPackagesToScan(packagesToScan);
		sessionFactoryBean.setHibernateProperties(properties);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}
