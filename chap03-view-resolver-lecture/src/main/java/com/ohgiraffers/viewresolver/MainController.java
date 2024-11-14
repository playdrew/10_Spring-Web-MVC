package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// ioc 컨테이너가 알아볼수 있게끔
@Controller
public class MainController {

    // index.html 초기화면을 인식하기 때문에 템플릿의 화면은 8080페이지에 안떠서 에러 발생현상해결
    //
    @GetMapping("/")
    public String main(){return "main";}
}
