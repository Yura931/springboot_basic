package com.fastcampus.springboot.ch3.di1;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Properties;

class Car{

    @Autowired
    private Engine engine;
    private Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
class SportsCar extends Car {}

class Truck extends Car {}

class Engine{}
class Door{}

public class Main {
    public static void main(String[] args) throws Exception {
        // 코드를 많이 변경하지 않고 context.txt 파일만 변경하면 됨
        // 소스코드를 건들지 않고 원하는 객체를 바꿀 수 있다는 점이 장점
        // 스프링이 이런식으로 되어 있음
        // 변경 포인트를 줄이는 것이 중요


        Car car = (Car) getObject("car");
        Engine engine = (Engine) getObject("engine");
        System.out.println("car = " + car);
        System.out.println("engine = " + engine);
    }

    static Object getObject(String key) throws Exception {
        Properties prop = new Properties();
        Class clazz = null; // 지정된 클래스이름에 해당하는 클래스 객체를 얻는다.

            prop.load(new FileReader("config.txt"));
            String className = prop.getProperty(key); // 지정한 key의 value를 반환
            clazz = Class.forName(className);

        // Java 9부터는 newInstance() 메서드 대신 getDeclaredConstructor().newInstance()를 사용하는 것이 권장
        Constructor<?> constructor = clazz.getDeclaredConstructor(); // 기본 생성자를 가져옵니다.
        return constructor.newInstance(); // 생성자를 호출하여 인스턴스를 생성합니다.

        // return clazz.newInstance(); // new SportCar();
    }
    static Car getCar() {
        return new SportsCar();
    }
}
