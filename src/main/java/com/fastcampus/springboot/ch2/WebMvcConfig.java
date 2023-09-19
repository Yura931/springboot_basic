package com.fastcampus.springboot.ch2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor())
                .addPathPatterns("/**") // 인터셉터를 적용할 대상
                .excludePathPatterns("/css/**", "/js/**"); // 인터셉터 적용을 제외할 대상

        // [/css/main.css]Time = 13, 웹페이지 요청 시 link된 파일들도 개별 요청이 들어감, 이런것들은 볼필요 없기 때문에 적용 대상에서 제외
                
    }
}
