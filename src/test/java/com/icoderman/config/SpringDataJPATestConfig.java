package com.icoderman.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "com.icoderman.app.dao")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SpringDataJPATestConfig {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String HBM2DDL_AUTO = "validate";  // update
	private static final String DIALECT = "org.hibernate.dialect.MySQL5InnoDBDialect";
	private static final String PACKAGES_TO_SCAN = "com.icoderman";
/*
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
		edb.setType(EmbeddedDatabaseType.H2);
		edb.addScript("sql/schema.sql");
		edb.addScript("sql/test-data.sql");
		EmbeddedDatabase embeddedDatabase = edb.build();
		return embeddedDatabase;
	}
*/
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(JDBC_DRIVER);
		dataSource.setUrl("jdbc:mysql://localhost:3306/spring_envers_db?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setPackagesToScan(PACKAGES_TO_SCAN);
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Map<String, Object> jpaProperties = new HashMap<>();
		jpaProperties.put("hibernate.dialect", DIALECT);
		jpaProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);
		jpaProperties.put("hibernate.show_sql", Boolean.FALSE);
		jpaProperties.put("hibernate.format_sql", Boolean.FALSE);
		factory.setJpaPropertyMap(jpaProperties);

		return factory;
	}

	/*
	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan("com.icoderman");
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(false);
		adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
		return adapter;
	}
*/
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}

}
