package hu.hardcore.FPEweblap.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("hu.hardcore")
@EnableTransactionManagement
@Slf4j
public class DbConfig {


    private static final String PROPERTY_NAME_DATABASE_DRIVER = "spring.datasource.hikari.driver-class-name";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "spring.datasource.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "spring.datasource.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "spring.datasource.username";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_JPA_SHOW_SQL = "spring.jpa.show-sql";
    private static final String PROPERTY_NAME_FORMAT_SQL = "spring.jpa.properties.hibernate.format_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Resource
    private Environment env;

    public DbConfig() {
        log.info("Setting up database configuration...");
    }

    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        emf.setJpaProperties(hibernateProperties());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
        hikariConfig.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
        hikariConfig.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
        hikariConfig.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");
        hikariConfig.setConnectionTimeout(300000);
        hikariConfig.setLeakDetectionThreshold(300000);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        transactionManager.setDataSource(dataSource());
        transactionManager.setJpaProperties(hibernateProperties());
        transactionManager.afterPropertiesSet();
        return transactionManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_JPA_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_JPA_SHOW_SQL));
        properties.put(PROPERTY_NAME_FORMAT_SQL, env.getRequiredProperty(PROPERTY_NAME_FORMAT_SQL));
        return properties;
    }

}
