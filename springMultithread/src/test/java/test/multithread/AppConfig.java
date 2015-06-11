package test.multithread;

import multithread.ejb.EJBConnection;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.CommonsPoolTargetSource;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"multithread.ejb"})
public class AppConfig {
    /*@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(0);
        return messageSource;
    }*/

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public EJBConnection Client1() {
    	EJBConnection ejbCon = new EJBConnection();
    	ejbCon.setFactory("");
    	ejbCon.setUrl("url");
        return ejbCon;
    }
    
    @Bean
    @Scope("prototype")
    public EJBConnection Client2() {
    	EJBConnection ejbCon = new EJBConnection();
    	ejbCon.setFactory("");
    	ejbCon.setUrl("url");
        return ejbCon;
    }

    @Bean
    public CommonsPoolTargetSource Client2PoolTarget() {      
        CommonsPoolTargetSource commonsPoolTargetSource = new CommonsPoolTargetSource();
        commonsPoolTargetSource.setMinIdle(4);
        commonsPoolTargetSource.setMaxSize(50);
        commonsPoolTargetSource.setTargetBeanName("Client2");
        commonsPoolTargetSource.setTargetClass(EJBConnection.class);
        System.err.println("I'm alive!!!");
        return commonsPoolTargetSource;
    }

    @Bean
    public ProxyFactoryBean Client2Pool() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetSource(Client2PoolTarget());
        return proxyFactoryBean;
    }

    @Bean
    public MethodInvokingFactoryBean poolConfigAdvisor() {
        MethodInvokingFactoryBean poolConfigAdvisor = new MethodInvokingFactoryBean();
        poolConfigAdvisor.setTargetObject(Client2PoolTarget());
        poolConfigAdvisor.setTargetMethod("getMaxIdle");
        return poolConfigAdvisor;
    }
    
    @Bean
    public ThreadLocalTargetSource Client2ThreadTarget() {
    	ThreadLocalTargetSource thread = new ThreadLocalTargetSource();
    	thread.setTargetBeanName("Client2");
    	thread.setTargetClass(EJBConnection.class);
         System.err.println("I'm alive!!!");
         return thread;
    }
    
    @Bean
    public ProxyFactoryBean Client2Thread() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetSource(Client2ThreadTarget());
        return proxyFactoryBean;
    }

}