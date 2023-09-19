package com.fastcampus.springboot.ch2;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PerformanceInterceptor implements HandlerInterceptor { // 단일 책임의 원칙(SRP) - 하나의 메서드는 하나의 책임만 갖는다.
//    long startTime; // iv - 인스턴스 변수, 싱글톤(하나의 객체)이라서 여러 쓰레드가 하나의 객체를 공유, 작업 중 다른 쓰레드가 덮어 쓸수 있음 조심히 사용
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 전처리 작업
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);   // iv에 저장하지 말고 request객체에 저장해서 후처리 메서드에 넘겨줌

        // handler - 요청하고 연결된 컨트롤러의 메서드
        HandlerMethod method = (HandlerMethod) handler;
        System.out.println("method.getMethod() = " + method.getMethod());   // URL하고 연결된 메서드
        System.out.println("method.getBean() = " + method.getBean()); // 메서드가 포함된 컨트롤러

        // return true일때 다음 Interceptor나 컨트롤러를 호출, false면 호출 안함.
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 2. 후처리 작업

        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        System.out.print("[" + ((HttpServletRequest) request).getRequestURI() + "]");
        System.out.println("Time = " + (endTime - startTime));

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
