package com.yyh.authoritymanagement.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author yyh
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactoryPrimary",
    transactionManagerRef = "transactionManagerPrimary",
    basePackages = {"com.yyh.authoritymanagement.model.repository.beansrepository"}) //设置Repository所在位置
public class PrimaryConfig {

  @Autowired
  @Qualifier("primaryDataSource")
  private DataSource primaryDataSource;

  @Autowired
  private Properties jpaPrimaryProperties;
  @Value("${spring.jpa.database-platform}")
  private String primaryDialect;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String ddlAuto;

  @Value("${spring.jpa.hibernate.show-sql}")
  private boolean showSql;

  @Primary
  @Bean(name = "entityManagerPrimary")
  public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
    return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
  }

  @Primary
  @Bean(name = "entityManagerFactoryPrimary")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
//这一部分 自己修改，网上内容不是这样，但是引用后，不显示有问题存在，所以自己改成如下
    LocalContainerEntityManagerFactoryBean entityManagerFactory
        = builder
        .dataSource(primaryDataSource)
        .packages("com.yyh.authoritymanagement.model.beans")
        .persistenceUnit("primaryPersistenceUnit")
        .build();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", ddlAuto);
    properties.put("hibernate.dialect", primaryDialect);
    properties.put("hibernate.show_sql", showSql);
    entityManagerFactory.setJpaProperties(jpaPrimaryProperties);
    entityManagerFactory.setJpaPropertyMap(properties);
    return entityManagerFactory;
  }

  @Primary
  @Bean(name = "transactionManagerPrimary")
  public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
    return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
  }

}