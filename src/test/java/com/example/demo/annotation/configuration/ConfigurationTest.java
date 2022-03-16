package com.example.demo.annotation.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {
    @Test
    void getBeanDefinitionNames(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ConfigurationConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}

@Configuration
class ConfigurationConfig{
    @Bean
    public Service ConfigService(){
        return new ConfigServiceImpl();
    }

    public Service ConfigService2(){ return new ConfigServiceImpl(); }
}

class ConfigServiceImpl implements Service {
    @Override
    public void join(String memberId) {
        System.out.println("memberId = " + memberId);
    }
}

interface Service {
    void join(String memberId);
}