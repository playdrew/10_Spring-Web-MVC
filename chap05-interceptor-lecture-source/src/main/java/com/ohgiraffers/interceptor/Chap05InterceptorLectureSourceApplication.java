package com.ohgiraffers.interceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//인터셉터 ,가로채기 ,요청필터 ,필터서블릿기능 , 인터셉터스프링기능 ,특정 url 만지정 , 스프링 ioc 컨테이너 : 빈들과 빈들사이의 관계형성 ,스프링제공 스프링가진 ioc컨테이너의 빈들에 침투 , 인터셉터는 빈들사이에서 동작, 필터에서 걸려지고 나서 aop 동작전 인터셉터가 걸러줌 , 미리 구현된 기능 자연스럽게 녹아든 개념 , 필터는 서블릿 인터셉트는 스프링 스프링이므로 빈들 사이 , 접근 가능 필터는 불가능


@SpringBootApplication
public class Chap05InterceptorLectureSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap05InterceptorLectureSourceApplication.class, args);
    }

}
