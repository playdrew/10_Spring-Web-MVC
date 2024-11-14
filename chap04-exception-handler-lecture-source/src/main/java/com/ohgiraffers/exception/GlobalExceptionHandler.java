package com.ohgiraffers.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/* comment. AOP 기능 중 Advice 는 반복적으로 동작해야 할
*           코드들을 뭉처둔 메소드를 의미하는 단어이다.
*           @ControllerAdvice 어노테이션은 마찬가지로
*           공통적으로 발생하는 예외처리를 담당하는
*           어노테이션을 의미한다*/
@ControllerAdvice
public class GlobalExceptionHandler {

    /* comment. @ExceptionHandler 의 우선순위는
    *           해당 클래스에 핸들러가 있으면 클래스 레벨이 우선순위를
    *           가지게 된다.
    *           만약 해당 클래스에 핸들러가 없으면 이후 전역의 핸들러를
    *           동작시키게 된다.
    **/

    // 가장 가까운 클래스부터 처리 만약에 클래스내에 핸들러가 있으면 클래스레벨에서 수준
    // 클래스에 찾아봤을때 없으면 전역수준의 핸들러에서 처리
    @ExceptionHandler(NullPointerException.class)
    public String globalNull(NullPointerException exception){
        System.out.println("전역 레벨의 exception 처리");
        return "error/nullPointer";
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public String globalUser(Model model, MemberNotFoundException exception){
        model.addAttribute("exception",exception);
        return "error/memberNotFound";
    }

    // 부모 타입을 이용하면 구체적인 예외 상황을 지정하지
    // 않아도 처리가 가능해지기 때문에
    // 기본적으로 동작할 예외 상황을 처리할 수 있다.
    // ArrayIndexOutOfBoundsException.class 대신 Exception 들의 부모타입인 Exception 이용
    @ExceptionHandler(Exception.class)
    public String defaultException(Exception exception){
        return "error/default";
    }

}
