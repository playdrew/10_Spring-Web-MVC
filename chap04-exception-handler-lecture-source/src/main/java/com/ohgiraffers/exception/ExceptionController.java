package com.ohgiraffers.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    //"location.href='nullpointer'"
    // 핸들러 메소드
    @GetMapping("nullpointer")
    public String nullpointerException(){
        // 의도적으로 에러발생
        // null 값에 참조를 하게 되면
        // NullPointerException 이 발생하게 된다.
        String str = null;
        System.out.println(str.charAt(0));

        return "/";
    }

    /* comment. @ExceptionHandler 어노테이션에 우리가 처리하고 싶은
    *           예외 클래스를 정의하면
    *           정의한 클래스의 예외가 발생 시 nullHandler 메소드가
    *           동작을 하게 된다.*/
    @ExceptionHandler(NullPointerException.class)
    public String nullHandler(NullPointerException exception){

        System.out.println("controller 레벨에서 NullPointer 예외 처리");
        return "error/nullPointer";
    }

    @GetMapping("userexception")
    public String userException() throws MemberNotFoundException {
        boolean check = true;
        if(check){
            throw new MemberNotFoundException("어디 갔니...");
        }
        return "/";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String userException(Model model,MemberNotFoundException exception){
        model.addAttribute("exception",exception);

        return "error/memberNotFound";
    }


}
