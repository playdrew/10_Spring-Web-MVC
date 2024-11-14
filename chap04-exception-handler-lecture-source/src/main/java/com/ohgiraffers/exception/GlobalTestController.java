package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GlobalTestController {

    /* comment. 다른 클래스에서 @ExceptionHandler 메소드를
    *           작성해두었다고 해서 지금 클래스에서 발생하는
    *           예외를 처리할 수는 없다.
    *           Why? -> 전혀 연관 없는 클래스이기 때문에
    **/
    // 클래스범위를 벗어나면 에러의 기능 안나와요 그럼 반복되는 코드 aop 의 일부 기능 사용
    //"location.href='global-nullpointer'"
    @GetMapping("global-nullpointer")
    public String globalNull(){
        String str = null;
        System.out.println(str.charAt(0));
        return "/";
    }
    @GetMapping("global-userexception")
    public String globalUserException() throws MemberNotFoundException {
        boolean check = true;
        if(check){
            throw new MemberNotFoundException("찾는 회원이 없어...");
        }
        return "/";
    }

    @GetMapping("global-default")
    public String manyException(){
        double[] array = new double[0];
        // 배열의 크기를 0으로 만들어 두고
        // 1번째 데이터 출력하려고 할때
        System.out.println(array[0]);
        // 메인페이지
        return "/";
    }
}
