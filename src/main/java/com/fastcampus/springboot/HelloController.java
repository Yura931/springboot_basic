package com.fastcampus.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

// 1. 원격 프로그램으로 등록
// 2. URL과 메서드를 연결
@RestController
public class HelloController {

    // 2. URL과 메서드를 연결
    @RequestMapping("/hello")
    public String main(HttpServletResponse response) {
        System.out.println("Hello");
        return "Hello";
    }
}
