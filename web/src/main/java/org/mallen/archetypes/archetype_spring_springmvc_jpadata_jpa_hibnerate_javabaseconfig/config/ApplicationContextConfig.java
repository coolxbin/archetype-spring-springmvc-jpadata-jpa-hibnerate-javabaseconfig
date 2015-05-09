package org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
// 加载属性文件
@PropertySource("classpath:jdbc-mysql.properties")
//<!-- spring-jpa-data扫描路劲 -->
@EnableJpaRepositories(basePackages={"org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.persiste"})
//<!-- 使用注解方式事务管理 --><tx:annotation-driven transaction-manager="transactionManager" />
@EnableTransactionManagement
//<!-- 读取注解来创建bean --><context:component-scan base-package="org.mallen.example.sshdemo.business" />
@ComponentScan(basePackages={"org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.business"})
public class ApplicationContextConfig {
	private static final String PROPERTY_NAME_DATABASE_URL = "database.url";
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "database.driverClassName";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "database.password";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "database.username";

    private static final String PROPERTY_NAME_DATABASE_CONNECTION_POOL_INITIAL_SIZE = "connpool.initialSize";
    private static final String PROPERTY_NAME_DATABASE_CONNECTION_POOL_MAX_TOTAL = "connpool.maxTotal";
    private static final String PROPERTY_NAME_DATABASE_CONNECTION_POOL_MAX_IDLE = "connpool.maxIdle";
    private static final String PROPERTY_NAME_DATABASE_CONNECTION_POOL_MIN_IDEL = "connpool.minIdle";
    
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_GENERATE_DDL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    
	@Resource
    private Environment env;
	
	@Bean
	public DataSource dataSource(){
		BasicDataSource result = new BasicDataSource();
		result.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		result.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		result.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		result.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		
		result.setInitialSize(env.getRequiredProperty(PROPERTY_NAME_DATABASE_CONNECTION_POOL_INITIAL_SIZE, Integer.class));
		result.setMaxTotal(env.getRequiredProperty(PROPERTY_NAME_DATABASE_CONNECTION_POOL_MAX_TOTAL, Integer.class));
		result.setMaxIdle(env.getRequiredProperty(PROPERTY_NAME_DATABASE_CONNECTION_POOL_MAX_IDLE, Integer.class));
		result.setMinIdle(env.getRequiredProperty(PROPERTY_NAME_DATABASE_CONNECTION_POOL_MIN_IDEL, Integer.class));
		
		return result;
	} 
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
		result.setDataSource(dataSource());
		result.setPackagesToScan("org.mallen.archetypes.archetype_spring_springmvc_jpadata_jpa_hibnerate_javabaseconfig.model");
		result.setJpaVendorAdapter(jpaVendorAdapter());
		result.setJpaPropertyMap(jpaProperties());

		return result;
	}

	private Map<String, Object> jpaProperties() {
		Map<String, Object> result = new HashMap<>();
		result.put("hibernate.format_sql", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		result.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
		return null;
	}

	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter result = new HibernateJpaVendorAdapter();
		result.setDatabasePlatform(env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		result.setShowSql(env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL, Boolean.class));
		result.setGenerateDdl(env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_GENERATE_DDL, Boolean.class));
		return result;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager result = new JpaTransactionManager();
		result.setEntityManagerFactory(entityManagerFactory().getObject());
		return result;
	}
}
