package com.example.app.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		transactionManagerRef = "addressTransactionManager",
		entityManagerFactoryRef = "addressEntityManagerFactory",
		basePackages = { "com.example.app.model.address" }
		)
public class AddressDB {
	@Bean(name = "addressDSProps")
	@ConfigurationProperties("address.datasource")
	public DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "addressDataSource")
	@ConfigurationProperties("address.datasource")
	public DataSource dataSource(@Qualifier("addressDSProps") DataSourceProperties properties) {
		return properties.initializeDataSourceBuilder().build();
	}
	
	@Bean(name = "addressEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean addressEntityManagerFactory(
		EntityManagerFactoryBuilder builder,
		@Qualifier("addressDataSource") DataSource addressDataSource
	) {
		return builder
				.dataSource(addressDataSource)
				.packages("com.example.app.model.address")
				.persistenceUnit("address")
				.build();
	}

	@Bean(name = "addressTransactionManager")
	@ConfigurationProperties("address.jpa")
	public PlatformTransactionManager addressTransactionManager(
			@Qualifier("addressEntityManagerFactory") EntityManagerFactory addressEntityManagerFactory) {
		return new JpaTransactionManager(addressEntityManagerFactory);
	}
}