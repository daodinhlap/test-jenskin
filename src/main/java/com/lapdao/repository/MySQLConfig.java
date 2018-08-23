package com.lapdao.repository;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/*@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory", 
    transactionManagerRef = "mysqlTransactionManager",
    basePackages = {"com.lapdao.repository"})
public class MySQLConfig {
  
  private final Logger logger = LoggerFactory.getLogger(MySQLConfig.class);

  @Bean(name = "policyMysqlDataSourceProperties")
  @ConfigurationProperties(prefix="mysql.datasource")
  protected DataSourceProperties userMysqlDataSourceProperties() {
    return new DataSourceProperties();
  }

  private DataSource createDataSource(DataSourceProperties properties) {
    String driverClassName = properties.determineDriverClassName();
    String url = properties.determineUrl();
    String username = properties.determineUsername();

    logger.debug("driverClassName: {} - url: {} - username: {}", driverClassName, url, username);
    return (DataSource) DataSourceBuilder.create(properties.getClassLoader()).type(DataSource.class)
        .driverClassName(driverClassName).url(url).username(username).password(properties.determinePassword())
        .build();
  }

  @Autowired
  @Bean(name = "mysqlDataSource")
  public DataSource mysqlDataSource(@Qualifier("policyMysqlDataSourceProperties") DataSourceProperties properties) {
    DataSource dataSource = createDataSource(properties);
    DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);
    DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
    String validationQuery = databaseDriver.getValidationQuery();
    
    if (validationQuery != null) {
      dataSource.setTestOnBorrow(true);
      dataSource.setValidationQuery(validationQuery);
    }
    
    return dataSource;
  }

  private DatabasePopulator createDatabasePopulator() {
    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.setContinueOnError(true);
    return databasePopulator;
  }

  @Autowired
  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder, 
                                                                          @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
    logger.info("\n\n----> Init Bean MysqlEntityManagerFactory mysqlDataSource {}, \n\n", mysqlDataSource);
    return builder.dataSource(mysqlDataSource)
        .packages("com.homedirect.policy.internal.model.entity")
        .persistenceUnit("mysql")
        .build();
  }

  @Autowired
  @Bean(name = "mysqlTransactionManager")
  public PlatformTransactionManager mysqlTransactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
    return new JpaTransactionManager(mysqlEntityManagerFactory);
  }

}
*/