package com.fastcampus.springboot;

import com.fastcampus.springboot.ch4.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@ServletComponentScan
public class SpringbootApplication {
    public static void main(String[] args) {
        // web app 사용하지 않음, 톰캣 안 뜸
/*        SpringApplication app = new SpringApplication(SpringbootApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);*/
        SpringApplication app = new SpringApplication(SpringbootApplication.class);
        app.run(args);
/*        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        Arrays.stream(beanDefinitionNames)
                .filter(b -> !b.startsWith("org"))
                .forEach(System.out::println);*/
    }

}
