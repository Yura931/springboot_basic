package com.fastcampus.springboot.ch3.di3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

@Component
class Car {
//    @Autowired
    Engine[] engine;

//    @Autowired
    Door door;

    public Car() {}

    @Autowired // 생성자 주입 (기본 생성자 없는 경우 생략 가능), 생성자가 있는 경우 자동 주입
    public Car(Engine[] engine, Door door) {
        this.engine = engine;
        this.door = door;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + Arrays.toString(engine) +
                ", door=" + door +
                '}';
    }
}

@Component
class Engine {}

@Component
class SuperEngine extends Engine {}
@Component
class TurboEngine extends Engine {}

@Component
class Door {}

public class Main {
    public static void main(String[] args) {
        // AC를 생성 - AC의 설정 파일은 AppConfig.class
        ApplicationContext ac = new AnnotationConfigApplicationContext(com.fastcampus.springboot.ch3.di3.AppConfig.class);
        Car car = (Car) ac.getBean("car");
        System.out.println("car = " + car);

        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames())); // AC 저장소에 등록되어있는 Bean
//        ex1(ac);
    }

    public static void ex1(ApplicationContext ac) {

        Car car = ac.getBean("car", Car.class); // byName 객체(빈)을 조회
        Engine engine = ac.getBean(Engine.class); // byType
        Engine engine2 = ac.getBean(Engine.class); // byType
        Engine engine3 = ac.getBean(Engine.class); // byType

        System.out.println("car = " + car);

        //* Bean은 기본적으로 singleton으로 설정되어 있기 때문에 같은 주소를 가진다, 다른 주소를 가지게 하고 싶은 경우 prototype으로 설정해주면 된다. //
        System.out.println("engine = " + engine);
        System.out.println("engine2 = " + engine2);
        System.out.println("engine3 = " + engine3);
        //*//

        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount());
        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames())); // AC 저장소에 등록되어있는 Bean
        System.out.println("ac.containsBeanDefinition(\"engine\") = " + ac.containsBeanDefinition("engine"));
        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car"));
        System.out.println("ac.isPrototype(\"engine\") = " + ac.isPrototype("engine"));

        SysInfo info = ac.getBean(SysInfo.class);
        System.out.println("info = " + info);

        Map<String, String> env = System.getenv();
        System.out.println("env = " + env);
    }
}
