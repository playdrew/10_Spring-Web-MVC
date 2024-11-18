package com.ohgiraffers.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /* comment. Interceptor 는 만들었다고 해서 끝이 아니다.
    *           WebMvcConfigurer 를 통해서 만든 인터셉터를
    *           등록하는 과정이 필요하다.*/

    // 등록을 하기 위해선 클래스에 대해서 알아야 하기 때문에 필드주입
    @Autowired
    private HandlerInterceptor handlerInterceptor;

    // 우리가 만든 인터셉터를 Registry = 저장소
    // 저장소에 등록을 하는 역할을 하게 된다.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor)
                // 인터셉터가 어떤 요청에 동작할 것인지 정의
                // 핸들인터셉터는 /*(모든요청)에 인터셉터가 동작하게끔 할 수 있어요
                .addPathPatterns("/*")
                // 익스클루드패스패턴은 인터셉터의 제외를 의미한다
                // static(정적인 요소) 파일을 불러오는 것도
                // 하나의 요청이다.
                // 따라서 인터셉터가 단순 화면 꾸미기 위한
                // 요청에도 동작을 하는 것은 비효율적인기 때문에
                // 제외할 경로를 지정하는 것이 중요하다.
                .excludePathPatterns("/css/*")
                .excludePathPatterns("/asset/*")
                .excludePathPatterns("/js/*")
                .excludePathPatterns("/images/*");


    }
}
