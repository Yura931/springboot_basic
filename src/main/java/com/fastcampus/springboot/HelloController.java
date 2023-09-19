package com.fastcampus.springboot;

import com.fastcampus.springboot.ch2.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

// 1. 원격 프로그램으로 등록
// 2. URL과 메서드를 연결
@Controller
public class HelloController {

    // 2. URL과 메서드를 연결
    @RequestMapping("/")
    public String main(HttpServletResponse response) throws Exception {
        return "index";
    }

    @GetMapping("/")
    public String test(Model model, HttpServletRequest request) {
        request.setAttribute("year", 2023);
        HttpSession session = request.getSession();
        session.setAttribute("id", "asdf");
        ServletContext application = session.getServletContext();
        application.setAttribute("email", "service@fastcampus.com");

        model.addAttribute("lastName", "yura");
        model.addAttribute("firstName", "jang");
        model.addAttribute("list", Arrays.asList("aaa", "bbb", "ccc", "ddd"));
        model.addAttribute("bno", 123);
        model.addAttribute("user", new User("aaa", 20));
        return "test";
    }
}
