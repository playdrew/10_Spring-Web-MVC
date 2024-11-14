package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    
    // 메인페이지 렌더링 할 수 있도록 설정
    @GetMapping("/")
    public String main(){
        return "main";
    }
}
