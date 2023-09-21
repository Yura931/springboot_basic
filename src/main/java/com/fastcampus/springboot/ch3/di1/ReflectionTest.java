package com.fastcampus.springboot.ch3.di1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Car car = new Car();

        // 설계도 얻는 방법들
        Class carClass = car.getClass();    // 1. 객체로부터 Class 객체 얻기
        carClass = Car.class;               // 2. 객체 리터럴로부터 Class객체 얻기
        carClass = Class.forName("com.fastcampus.springboot.ch3.di1.Car");

        // 1. 설계도 객체로부터 객체 생성하기
        Car car2 = (Car) carClass.newInstance();
        System.out.println("car2 = " + car2);

        // 2. 클래스에 선언된 멤버변수(field)와 method목록 얻기
        Field[] mvArr = carClass.getDeclaredFields();
        Method[] methodArr = carClass.getDeclaredMethods(); // 직접 선언한 메서드
        Method[] methodArrObj = carClass.getMethods(); // 조상 메서드까지 전부 다 나옴

        for (Field mv : mvArr) System.out.println(mv.getName());
        for(Method method : methodArr) System.out.println(method.getName());

        Method method = carClass.getMethod("setEngine", Engine.class);
        method.invoke(car, new Engine()); // car.setEngine(new Engine())
        System.out.println("car = " + car);

        // 3. mv에 set붙여서 setter를 호출하기
        for (Field mv : mvArr) {
            // field의 멤버변수로 선언한 Engine, Door를 반복문을 돌면서 set이름을 만들어서 메서드를 호출
            // 메서드 동적호출 가능!

            System.out.println("mv = " + mv);
            String methodName = "set" + StringUtils.capitalize(mv.getName());   // "set" + "Engine" = "setEngine"
            System.out.println("methodName = " + methodName);
            method = carClass.getMethod(methodName, mv.getType());   // carClass.getMethod("setEngine", Engine.class);
            method.invoke(car, mv.getType().newInstance()); // car.setEngine(new Engine())
        }
        System.out.println("car = " + car);
        
        // 4. mv에 @Autowired붙었는지 확인하기
        for (Field mv : mvArr) {
            Annotation[] annoArr = mv.getDeclaredAnnotations();
            for (Annotation anno : annoArr) {
                System.out.println("mv.getName() = " + mv.getName());
                System.out.println("anno.annotationType().getSimpleName() = " + anno.annotationType().getSimpleName());
                System.out.println("(anno.annotationType() == Autowired.class) = " + (anno.annotationType() == Autowired.class));
            }
        }
        
        // 5. @Autowired붙은 mv에 setter호출하기
        car = new Car();
        for (Field mv : mvArr) {
            Annotation[] annoArr = mv.getDeclaredAnnotations();
            for (Annotation anno : annoArr) {
                System.out.println("mv.getName() = " + mv.getName());
                if(anno.annotationType() == Autowired.class) {
                    // setter 호출
                    String methodName = "set" + StringUtils.capitalize(mv.getName());
                    method = carClass.getMethod(methodName, mv.getType());
                    method.invoke(car, mv.getType().newInstance());
                }
            }
        }

        System.out.println("car = " + car);


    }
}
