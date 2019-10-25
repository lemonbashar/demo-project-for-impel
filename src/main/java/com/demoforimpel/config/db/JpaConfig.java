package com.demoforimpel.config.db;

import com.demoforimpel.data.props.ApplicationProperties;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration

@EnableJpaRepositories(basePackages = "com.demoforimpel.repository", entityManagerFactoryRef = "jpaManager")
public class JpaConfig {
    static final String[] ANNOTATED_PACKAGES = {"com.demoforimpel.domain"};
    private final ApplicationProperties properties;

    public JpaConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Primary
    @Bean("jpaManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter vendorAdapter) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPersistenceUnitName(properties.getDatabase().getJpa().getPersistentUnit());
        factoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(ANNOTATED_PACKAGES);
        return factoryBean;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(properties.getDatabase().isShowSql());
        adapter.setDatabasePlatform(properties.getDatabase().getDialect());
        adapter.setGenerateDdl(properties.getDatabase().isGenerateDDL());
        if (properties.getDatabase().getDatabaseType() == null) throw new RuntimeException("You Must Specify The DatabaseType, POSTGRESQL/MYSQL");
        adapter.setDatabase(Database.valueOf(properties.getDatabase().getDatabaseType().name()));

        return adapter;
    }

    @Bean("transactionManager")
    public PlatformTransactionManager hibernateTransactionManager(final LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory.getObject());

        return hibernateTransactionManager;
    }
}
