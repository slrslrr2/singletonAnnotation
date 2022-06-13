# singletonAnnotation
@Configuration, @Bean, @ComponentScan, @Component, @SpringBootApplication, @Autowired, @RequiredArgsConstructor, @ServletComponentScan


**헷갈리는 어노테이션 정리**

웹애플리케이션의 경우,<br>고객의 요청이 올 때마다 객체를 매번 만들어주면 많은 트래픽이 발생된다.<br>이러한 문제점을 해결해주기위해 스프링은 객체를 **싱글톤으로 만들**어서 **객체를 공유**하게된다.<br>제가 얘기하고 싶은건 싱글톤을 만들어주는 어노테이션과 그와 관련된 어노테이션이기때문에<br>혹시 **싱글톤**에 모르는 분들이 있다면 자세한 내용은 찾아보셧으면 좋겠습니다.<br>



- @Configuration, @Bean
- @ComponentScan, @Component



- @SpringBootApplication
- @Autowired
- @RequiredArgsConstructor



- @ServletComponentScan



## 1. @Configuration, @Bean

설명 : **빈을 등록**하기위한 **설정파일을 지정해주는 어노테이션**이다.

부가설명: 

​    고객의 요청이 올 때마다 객체 매번 new 연산자를 통해서 새로운 객체를 생성하다보면

​    많은 트래픽과 메모리가 과부화될 수 있기때문에 이는 너무 비효율적입니다.

​    그렇기때문에 스프링은 객체를 **싱글톤으로 만들**어서 **객체를 공유**하게된다.

   혹시 **싱글톤**에 모르는 분들이 있다면 자세한 내용은 찾아보셧으면 좋겠습니다.

​    

사용법 : AppConfig.java 설정파일로 등록하고 @Bean으로 지정된 메소드들을 **서블릿 컨테이너**에 **싱글톤**으로 등록해주고 싶다면,

@Configuration을 상단에 명시하고 사용하면 된다.

```java
class ConfigServiceImpl implements Service {
    @Override
    public void join(String memberId) {
        System.out.println("memberId = " + memberId);
    }
}

interface Service {
    void join(String memberId);
}
```



```java
@Configuration
class ConfigurationConfig{
    @Bean
    public Service configService(){
        return new ConfigServiceImpl();
    }
	
    // Bean 선언 안했기에 등록 안됨
    public Service configService2(){ 
        return new ConfigServiceImpl(); 
    }
}
```

그리고 아래와 같이 테스트 코드를 작동 시키면 스프링컨테이너에 @Bean으로 선언한 객체들이 싱글톤으로 스프링컨테이너로 등록되었음을 확인할 수 있다.

```java
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
```

##  

**실행결과**

```java
beanDefinitionName = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
beanDefinitionName = org.springframework.context.annotation.internalAutowiredAnnotationProcessor
beanDefinitionName = org.springframework.context.annotation.internalCommonAnnotationProcessor
beanDefinitionName = org.springframework.context.event.internalEventListenerProcessor
beanDefinitionName = org.springframework.context.event.internalEventListenerFactory
    
beanDefinitionName = configurationConfig
beanDefinitionName = ConfigService
```

여기서 문제, @Configuration을 제외하면 어떻게 될까?



##  

## 2. @ComponentScan, @Component

설명 : @ComponentScan을 사용하면 **해당패키지, 하위패키지를 확인**하며 <br>

부가설명 : 두번째로 스프링컨테이너에 **싱글톤으로 객체를 등록하는 방법**입니다.

@Component의 대상은 아래와 같다.

- @Component, @Controller, @Service, @Repository, @Configuration
- 해당 어노테이션을 까본다면 @Component가 선언되어있다.

```java
@Configuration // 설정파일임을 알려준다.
@ComponentScan
class ComponentConfig {

}
```





## 3. @SpringBootApplication

설명 : SpringBoot를 사용하면 @SpringBootApplication이 생성되는데
@SpringBootApplication안에 **@ComponentScan**이 등록되어있다.
즉, 해당 어노테이션 하단에 @Component로 등록된 객체들을 모두 스프링컨테이너에 등록해준다.

##  

## 4. @Autowired

설명 :

- @Bean, @Component 등록된 스프링컨테이너 안에 있는 싱글톤 객체들을 불러와 사용할 수 있는 어노테이션입니다.

- **스프링컨테이너에 등록된 객체**들을 다음 어노테이션을 붙이면
  타입을 확인한 후 해당하는 빈을 찾아 **자동으로 주입**해준다.(DI)
- 해당 메소드에 하나의 생성자만 있는 경우 @Autowied는 생략 가능하다

```java
@Component
public class dependencyInjectionServiceImpl implements dependencyInjectionService {
    private final ComponentService componentService;

    @Autowired
    public dependencyInjectionServiceImpl(ComponentService componentService) {
        this.componentService = componentService;
    }

    @Override
    public void injectTest() {
        componentService.join("gbitkim");
    }
}
```

##  

##  

## 5. @RequiredArgsConstructor

- 롬복에서 제공해주는 메소드로써, final객체를 매개변수로 생성자함수를 만들어준다.

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

```java
@Component
@RequiredArgsConstructor
public class dependencyInjectionServiceImpl implements dependencyInjectionService {
    private final ComponentService componentService;

//    @Autowired // 생성자 함수가 하나인 경우 생략 가능
//    결과적으로, @Autowired를 생략하고
//    @RequiredArgsConstructor를 적용하면서 final생성함수를 자동으로 생성자를 만들어준다
//    롬복 짱짱짱
//    public dependencyInjectionServiceImpl(ComponentService componentService) {
//        this.componentService = componentService;
//    }

    @Override
    public void injectTest() {
        componentService.join("gbitkim");
    }
}
```

##  

##  

## 6. @ServletComponentScan

**SpringBoot 환경**에서 **서블릿 자동등록**해준다.

현 패키지포함하여 하위패키지(**필터, 서블릿, 리스너**)를스캔해서

빈으로 등록한다.

단 아래와 같은 어노테이션이 등록되어있어야한다.

 

- 리스너: @WebListener
- 서블릿: @WebServlet
- 필터: @WebFilter
