package com.fastcampus.springboot.ch3.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AppContext  { // 스프링 컨테이너 흉내내보기, 맵에 저장후 편하게 사용할 수 있도록 하는 것
    Map map = new HashMap();

    AppContext() {
        map.put("car", new SportsCar());
        map.put("engine", new Engine());
        map.put("door", new Door());
    }

    AppContext(Class clazz) {
        Object config = null;    // config 객체 생성
        try {
            config = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Method[] methods = clazz.getDeclaredMethods();  // 메서드를 담은 배열
        for (Method m : methods) {
            for(Annotation anno : m.getDeclaredAnnotations()) {
                if(anno.annotationType() == Bean.class) {
                    try {
                        map.put(m.getName(), m.invoke(config, null));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        doAutowired(); // @Autowired를 찾아서 빈(객체)간의 자동 연결처리
        doResource(); // @Resource를 찾아서 빈(객체)간의 자동 연결처리
    }

    private void doResource() {
        for(Object bean : map.values()) {
            for(Field fld : bean.getClass().getDeclaredFields()) {
                if(fld.getAnnotation(Resource.class) != null ) {
                    try {
                        fld.set(bean, getBean(fld.getName()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void doAutowired() {
        for(Object bean : map.values()) {
            for(Field fld : bean.getClass().getDeclaredFields()) {
                if(fld.getAnnotation(Autowired.class) != null) {
                    try {
                        fld.set(bean, getBean(fld.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public Object getBean(String id) {
        return map.get(id);
    }

    public Object getBean(Class clazz) {
        for(Object obj : map.values())
            if(clazz.isInstance(obj))
                return obj;
        return null;
    }
}
