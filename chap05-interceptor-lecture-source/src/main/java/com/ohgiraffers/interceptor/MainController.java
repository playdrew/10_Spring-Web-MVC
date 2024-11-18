package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// ioc 컨테이너에 등록
@Controller
public class MainController {

    // 아무런 요청을 안했을때 / main 이란 페이지를 돌려주겠단 컨트롤러 생성
    @GetMapping("/")
    public String main(){
        return "main";
    }
}
