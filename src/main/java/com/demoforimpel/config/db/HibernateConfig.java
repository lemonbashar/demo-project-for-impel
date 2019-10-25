package com.demoforimpel.config.db;

import com.demoforimpel.data.props.ApplicationProperties;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class HibernateConfig {
    private final ApplicationProperties properties;

    public HibernateConfig(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        Properties hbmProperties = new Properties();
        hbmProperties.setProperty(Environment.DIALECT, properties.getDatabase().getDialect());
        hbmProperties.setProperty(Environment.HBM2DDL_AUTO, properties.getDatabase().getHbm2DDLAuto());
        hbmProperties.setProperty(Environment.SHOW_SQL, "" + properties.getDatabase().isShowSql());
        hbmProperties.setProperty(Environment.FORMAT_SQL, "" + properties.getDatabase().isFormatSQL());
        localSessionFactoryBean.setPackagesToScan(JpaConfig.ANNOTATED_PACKAGES);
        localSessionFactoryBean.setHibernateProperties(hbmProperties);
        return localSessionFactoryBean;

    }

    @Bean
    public EntityManager entityManager(SessionFactory sessionFactory) {
        return sessionFactory.createEntityManager();
    }
}
