package com.jakub.config;

import com.jakub.dao.PraceDyplomoweDAO;
import com.jakub.dao.TematyDAO;
import com.jakub.dao.UsersDAO;
import com.jakub.dao.WiadomosciDAO;
import com.jakub.daoimpl.PraceDyplomoweDAOImpl;
import com.jakub.daoimpl.TematyDAOImpl;
import com.jakub.daoimpl.UsersDAOImpl;
import com.jakub.daoimpl.WiadomosciDAOImpl;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

/**
 * Created by Jakub on 26.05.2017.
 */

@Configuration
@ComponentScan("com.jakub.*")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class ApplicationContextConfig {
    @Autowired
    private Environment env;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
        rb.setBasenames(new String[]{"messages/validator"});
        return rb;
    }

    //konfiguracja widoków jsp
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        System.out.println(viewResolver.toString());
        return viewResolver;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    //konfiguracja łączenia z bazą danych
    @Bean(name = "dataSource")
    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass(env.getProperty("ds.database-driver"));
        dataSource.setJdbcUrl(env.getProperty("ds.url"));
        dataSource.setUser(env.getProperty("ds.username"));
        dataSource.setPassword(env.getProperty("ds.password"));

        return dataSource;
    }


    //konfiguracja obiektu sessionFactory
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DriverManagerDataSource dataSource) throws Exception {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.put("current_session_context_class", env.getProperty("current_session_context_class"));


        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setPackagesToScan(new String[]{"model"});
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();

        SessionFactory sf = factoryBean.getObject();
        System.out.println(" getSessionFactory: " + sf);
        return sf;
    }

    //konfiguracja managera transakcji
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Bean(name = "userDAO")
    public UsersDAO getCategoryDAO() {
        return new UsersDAOImpl();
    }

    @Bean(name = "wiadomosciDAO")
    public WiadomosciDAO getWiadomosciDAO() {
        return new WiadomosciDAOImpl();
    }

    @Bean(name = "tematyDAO")
    public TematyDAO getTematyDAO() {
        return new TematyDAOImpl();
    }


    @Bean(name = "praceDyplomoweDAO")
    public PraceDyplomoweDAO getPraceDyplomoweDAO() {
        return new PraceDyplomoweDAOImpl();
    }
}

