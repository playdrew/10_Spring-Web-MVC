package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterceptorController {

    @GetMapping("stopwatch")
    public String handlerMethod() throws InterruptedException {
        System.out.println("Controller 의 핸들러 메소드 호출됨...");

        // 2초간 아무 작동도 하지 않게 만드는 sleep 메소드
        // 쓰레드란 하나의 프로세스가 진행 멀티스레드는 두개의 일을 동시에 처리
        Thread.sleep(2000);

        return "result";
    }

}
