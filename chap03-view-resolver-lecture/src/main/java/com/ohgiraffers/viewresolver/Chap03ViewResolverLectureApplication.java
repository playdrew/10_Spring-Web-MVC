package com.ohgiraffers.viewresolver;

// static 은 정적인 리소스 : 더 이상 변수처리를 하지 않는 것
// template 은 동적인 리소스 : 서버에서 페이지를 주거나 데이터를 담아 보내거나 데이터에 따라 바뀔 수 있는 내용을 작성

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chap03ViewResolverLectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap03ViewResolverLectureApplication.class, args);
    }

}
