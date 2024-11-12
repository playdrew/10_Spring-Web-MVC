package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 어플리케이션이 인지하게끔 컴포넌트(컨트롤러)를 선언
/*comment.
*   Spring Boot 는 Servlet 이 내장되어 있다.
*   지금까지는 요청을 처리할 서블릿이 동작을 했다면
*   이제는 Servlet 은 요청을 받는(url 클릭) 즉시 @Controller 어노테이션이
*   달린 클래스에 처리를 클래스에 처리를 위임한다.
* */

@Controller
public class MethodMappingController {

    /* 1. 메소드 방식 미지정 */
    // 사용자의 요청을 매칭시킬 메소드
    // 버튼의 html 에서 요청을 처리할 서블릿 동작을 메소드딴에 작성하게 되면 메소드가 처리하게 됨
    // RequestMapping 어노테이션은 URL 만 일치하면
    // 모든 방식의 요청을 처리하는 Servlet 으로 치면 service 메소드이다.
    @RequestMapping("/menu/regist")
    public String registMenu(Model model){
        // 값을 담아서 view 페이지에 보낸다.
        /* comment. Model 객체는 값을 담을 수 있는 공간이다
        *           key 와 value 형식으로 값을 담게 되며, 추후 응답 시
        *           view 에서 사용할 수 있게 된다. -> 나중에 더 다룰 예정
        *           addAttribute 라는 메소드를 사용해서 값을 담을 수 있다.
        * */

        model.addAttribute("message","메뉴 등록용 핸들러 메소드 동작 확인...");
        // Controller 는 사용자 요청받아서 가공처리를 한다음에 보내주는데 view 페이지를 return 합니다.

        /*comment. Controller 계층의 마지막 임무는 사용자에게 어떻게 응답을
        *          해줄 것인지이다.
        *          메소드의 반환 타입을 String 으로 해서 문자열을 전달하면
        *          resource/templates 하위 경로의 파일을 탐색하게 된다.*/
        // 문자열 전달하면 서블릿이 파일을 찾는데 리소스 하위에 템플릿 하위에 전달한 문자열파일이 있는지 찾습니다.
        return "mappingResult";
    }
    
    /* 2. 메소드 방식 지정 */
    // 요청 URL 을 value 속성, 요청 방식을 method 속성에 지정
    // 위에선 메소드 요청이 미지정시 get 이든 post 든 다 받아 주었다. 이건 다르다
    @RequestMapping(value = "/menu/modify", method= RequestMethod.GET)
    public String modifyMenu(Model model){
        
        model.addAttribute("message","GET 방식만 허용하는 메뉴 수정 핸들러 메소드 호출됨");

        return "mappingResult";
    }

    /*comment. @RequestMapping 만 쓰게 되면
    *          항상 메소드 방식 지정해주고 value 도 작성해야 된다.
    *          따라서 요청 메소드 전용 어노테이션을 제공하여
    *          번거로운 작업을 안하게 도와준다.
    *          요청 메소드       어노테이션
    *          POST            @PostMapping
    *          GET             @GetMapping
    *          PUT             @PutMapping
    *          DELETE          @DeleteMapping
    * */

    @GetMapping("/menu/delete")
    public String getDeleteMenu(Model model){

        model.addAttribute("message","GET 방식의 메뉴 삭제용 핸들러 메소드 호출됨...");

        return "mappingResult";

    }

    @PostMapping("/menu/delete")
    public String postDeleteMenu(Model model){

        model.addAttribute("message","POST 방식의 메뉴 삭제용 핸들러 메소드 호출됨...");

        return "mappingResult";

    }

}
