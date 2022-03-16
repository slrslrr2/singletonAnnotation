package com.example.demo.annotation.component;

import com.example.demo.annotation.component.service.impl.DependencyInjectionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class ComponentTest {
    @Test
    void getBeanDefinitionNames(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }

    @Test
    void autowiredTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentConfig.class);
        DependencyInjectionServiceImpl bean = ac.getBean(DependencyInjectionServiceImpl.class);
        bean.injectTest();
    }
}

@Configuration
@ComponentScan
class ComponentConfig {

}