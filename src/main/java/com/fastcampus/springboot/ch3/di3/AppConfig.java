package com.fastcampus.springboot.ch3.di3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration // 설정 클래스!
@ComponentScan // 공통으로 사용하는 설정파일에 직접 Bean을 생성하기보단 패키지 단위로 Component를 스캔하도록 하고, 객체에 Component 애너테이션을 사용해주는 방식이 좋다.
public class AppConfig {
/*
    @Bean
    @Scope("singleton")
    Car car() {
        return new Car();
    }

    @Bean
    @Scope("prototype")
    Engine engine() {
        return new Engine();
    }
*/

/*
    @Bean
    Door door() {
        return new Door();
    }
*/
}
