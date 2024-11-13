package com.ohgiraffers.chap02handlermethodlecturesource;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

@Controller
@RequestMapping("/request/*")
@SessionAttributes("id") // session 에 담을 key 값 설정 - 키를 id로 설정한 순간 자동으로 등록
public class RequestController {

    /* title. 요청 시 값을 전달받는 방법 */
    // 컨트롤러는 응답을 할때 뷰를 지정해주는 의미를 지님
    // 저번 차시에 메서드의 반환타입을 스트링으로 지정하고 뷰페이지 반환했었다
    /* comment view 페이즈를 보여주는 방식
    *          1. String 타입 반환값에 view 파일 이름 작성
    *          2. 메소드의 반환 타입을 void
    *          - 반환 타입을 void 로 하게 되면 요청 url 이
    *          - view 의 이름이 된다.
    * */
    @GetMapping("regist")
    public void regist(){}

    /* comment. 1. WebRequest 객체로 요청 파리미터 전달받기
    *           매개변수 선언부에 WebRequest 객체를 선언하면
    *           해당하는 핸들러 메소드가 호출 시 인자로 값을
    *           전달해준다.
    *           (서블릿 배울 때 doPost 메소드 내부에 HttpServletRequest)
    *           스프링 프레임워크는 내부적으로 Servlet 을 사용하고 있기
    *           때문에 HttpServletRequest 도 사용이 가능하다.
    *           다만 사용을 하지 않는 이유는 WebRequest 객체는
    *           Servlet 에 종속적이지 않으나 기능은  모두 포함하고 있기
    *           때문에 추후에 Servlet 기능이 사라졌을 시 적은 수정 범위로
    *           교체할 수 있다는 장점이 있다.
    *           WebRequest 객체는 Spring 측에서 제공하기 때문에
    *           Spring 친화적으로 많이 사용하는 편이다.
    *           */

    // 스프링프레임워크 psa 는 우리가 언제든지 상속받는 클래스를 유연하게 바꿀수있음.
    // 서블릿 기반 스프링사용 서블릿보다 좋은 기능이 나왔어요 다른 혁신적인 것
    // 그럼 메서드 선언부에 HttoServletRequest 시 다 코드 삭제
    // WebRequest 서블릿을 다 사용하데 직접적으로 사용하지 않으므로
    // WebRequest 는 서블릿을 상속받는다. 혁신적인 것을 상속받는다라는 개념으로?
    // 서블릿 기능을 확장시켜서 스프링에 맞게끔 사용한 기능 WebRequest 사용해서
    // 서블릿이 없어져도 사용가능하게끔 한다.

    // ioc : di aop psa 가 있다.
    // psa 의 예로 마약구매하는 고객이면
    // 마약을 제공하는 브로커가 있다. 브로커는 마약생산국가에서 마약을 밀수
    // 고객 -- 브로커 -- 콜롬비아/멕시코
    // 콜롬비아에서 대대적인 단속때문에 원료가 들어오지 못해요
    // 브로커는 멕시코란 거래처도 알고 있기 때문에 거래처는 안끊김니다.
    // 고객이 거래처가 바로 콜럼비아에서 오면 콜럼비아마약단속때문에 구할 수 없어요
    // 우리가 서블릿이란 개념이 사라진다라면 코드를 대대적으로 수정해야해요
    // 근데 브로커가 스프링이면 스프링측에서 서블릿을 쓰다가 특화적인 기능이 나왔다면
    // 스프링개발자들이 알아서 바꿔요 다른 거래처를 모색하듯이요. 해당 의존성을 꺼내버리고 멕시코의존성을 받음
    // 직접적으로 우리가 코드를 수정할 필요가 없습니다.
    // 우리가 사용하는 웹리퀘스트라는 기능이고요.
    // psa 란 개념이 수수료를 내더라도 가운데 다리를 놓아서 영향성을 최소화한다
    // 우리는 브로커만 알면 되요. 의존성을 줄이고 브로커만 상속받고 거래처를 바꾸던말던 알필요가 없어요


    @PostMapping("regist")
    // public String registMenu (Model model , HttpServletRequest request)
    public String registMenu(Model model, WebRequest request){
        // 값 꺼내기 폼태그의 name 이란 값을 집어넣음
        String menuName = request.getParameter("name");
        int menuPrice = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        // 꺼내온 값의 조함
        String message = menuName + "을(를) 신규 메뉴 목록" + categoryCode +"번 카테고리에 " +
                menuPrice + "원으로 등록을 했습니다!!!";

        // 키 "message" , 값 message
        model.addAttribute("message",message);

        return "request/printResult";
        
        // VIEW - CONTROLLER - VIEW 
        // 서버에서 제조한 값을 보여주고 싶을땐 MODEL 객체에 에드어트리뷰트해서 키값으로 보여주는 것
    }

    // href 속성을 사용하기 때문에 getMapping
    @GetMapping("modify")
    public void modify(){}

    // form 태그에 입력한게 3개 밖에 없는데 나중에 많아지면 겟파라미터를 통해 꺼내는 과정이 많아져요
    // 그래서 그걸 줄이기 위해 @RequestParam 을 사용
    // 근데 규칙이 있어요 매개변수의 값을 담을 폼태그의 네임을 일치 시켜주어야함
    /* comment. @RequestParam
    *           화면에서 요청하는 값을 담아주는 어노테이션이다.
    *           담을 매개변수 앞에 작성을 하게 되며
    *           form 의 name 속성과 일치시켜주어야 한다.
    *           만약 당신이 짱구여서 말을 듣기 싫으면
    *           @RequestParam("폼 form 속성") String 을 사용하고 싶은 변수명
    *           name 속성이 일치하지 않을 때 400-bad request 에러가 발생한다.
    *           이는 required 속성의 기본 값이 true 이기 때문이다.
    *           이 때 required 속성의 값을 false 로 바꿔주게 된다면
    *           해당 name 속성이 일치하지 않더라도 error 를 발생시키지 않고
    *           null 로 처리를 하게 된다.
    *           */

    // required 는 반드시 값을 달라라는 것이고 값이 없으면 에러를 발생시킨다라는 것입니다.
    // defaultValue 는 null 이 아니라 기본값을 설정값으로 하겠다는 것이에요
    @PostMapping("modify")
    // public String modifyMenu(Model model, @RequestParam(required = false) String name, @RequestParam(defaultValue = 0) int modifyPrice)
    public String modifyMenu(Model model, @RequestParam String modifyName, @RequestParam int modifyPrice){

        String message = modifyName + "의 가격을 " + modifyPrice + "로 수정!!";

        model.addAttribute("message",message);

        return "request/printResult";
    }

    /* comment. 요청 파리미터가 여러개인 경우 각각 담는 것이 아닌
    *           Map 을 사용해서 한 번에 담을 수 있다.
    *           맵의 키는 form 태그의 name 속성 값이 된다
    * */
    // requestParam 을 Map 형식으로 바꿨으면 매개변수명을 비교적 신경안써도 된다.
    @PostMapping("modifyAll")
    public String modfiyALL(Model model, @RequestParam Map<String,String> parameters){

        String menuName = parameters.get("modifyName2");
        int menuPrice = Integer.parseInt(parameters.get("modifyPrice2"));

        String message = menuName + "의 가격을 " + menuPrice + "로 수정!!";

        model.addAttribute("message",message);

        return "request/printResult";
    }

    @GetMapping("search")
    public void search(){}

    /* comment. 지금은 요청하는 파라미터가 몇 개 안 되어서
    *           @RequestParam 어노테이션을 사용해도 간단하게 작성할 수 있다.
    *           하지만, 받아올 데이터가 많아진다면 관리할 변수나, 키값이
    *           많아질 수 밖에 없다.
    *           @ModelAttribute 객체를 생성하여 요청되는 값을 필드의
    *           form 태그의 name 속성과 비교하여 값을 넣어준다.
    *           @ModelAttribute 담은 값은 view 페이지에서
    *           타입(자료형) 앞글자를 소문자로 한 네이밍 규칙으로
    *           사용할 수 있다.(menuDTO)
    *           다른 이름을 사용하고 싶으면
    *           @ModelAttribute("사용할 값") 이렇게 지정도 할 수 있다.
    *          */
    // 그럼 클래스하나에 뭉태기로 박는거에요
    // 왠만하면 클래스로 받을 거에요 하나 두개 받지 않는 이상
    // 모델어트리뷰트를 이용해서 클래스사용
    // 생략도 가능한데 왠만해선 하지 마세요
    @PostMapping("search")
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu){
        System.out.println("menu = " + menu);
        return "request/searchResult";
    }

    @GetMapping("login")
    public void login(){}

    /* comment. HttpSession 객체 이용해서 요청값 저장하기 */
    // session 을 사용한다는 것은 model 은 임시적이므로 session 을 사용해서 만료될때까지 사용홥니다
    @PostMapping("login1")
    public String sessionTest(HttpSession session, @RequestParam String id){
        session.setMaxInactiveInterval(60*60*24);
        session.setAttribute("id",id); // value 는 form 태그에서 입력했던 id 값 입력
        // 반환타입이 String 이므로 view 에 대한 경로 지정
        return "request/loginResult";
    }

    // Session 에 아이디 저장값을 만료 없애기
    // post 매핑은 값가져올때 쓰고 페이지이동은 get 으로
    @GetMapping("logout1")
    public String logout1(HttpSession session){
        session.invalidate(); // 강제 만료
        return "request/loginResult";
    }
    
    /* comment. @SessionAttribute 를 이용한 session 에 값 담기
    *           클래스 레벨에 SessionAttribute 을 사용하여
    *           session 에 담을 key 값을 설정해두면
    *           Model 영역에 해당 key 로 값이 추가되는 경우
    *           자동으로 session 에 등록해준다.*/
    // 여기선 session 객체를 직접적으로 사용하지 않으므로 인벨리데이트는 동작하지 않는다.
    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){
        model.addAttribute("id",id); // 클래스수준의 어노테이션과 일치되어야한다

        return "request/loginResult";
    }

    /* comment. SessionAttribute 방식은 session 의 상태를 관리하는
                SessionStatus 객체의 setComplete() 메소드를
                사용해야 만료 시킬 수 있다.
    *       */
    @GetMapping("logout2")
    public String logout2(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "request/loginResult";
    }

    @GetMapping("body")
    public void body(){} // 뷰페이지렌더링

    /* comment. @RequestBody 해당 어노테이션은 http 본문 자체를 읽는
    *           모델로 변환시켜주는 어노테이션이다.
    *           출력을 해보니 쿼리스트링 형태로 문자열이 전달된다.
    *           -> key 와 value 형태로 값이 전달되고 있다.
    *           나중에 나올 개념인 JSON(자바스크립트객체표현식) 으로 전달이 되면
    *           Jackson 컨버터 : 자바스크립트 객체 <--> 자바객체
    *           자동 변환해주어 프론트엔드 서버(js 기반) 벡엔드 서버(java 기반)
    *           간의 데이터 전송을 할 수 있게 해준다.
    *           주로 Rest API 를 사용하여 만들 때 많이 사용하며
    *           일반적인 form 태그에서는 거의 사용하지 않는다.
    *           */
    // url 이 정보의 덩어리 requestBody 는 요청 url 을 읽어요
    // post 인데요 인코딩처리가 된 상태에서 값을 받을 수 있어요
    @PostMapping("body")
    public void bodyTest(@RequestBody String body) throws UnsupportedEncodingException {
        System.out.println(body);
        /* comment. 넘어온 값을 보면 알 수 없이 변환이 되어있다.
        *           이 것을 encoding 되어있다라고 말을 하며
        *           해석을 하기 위해서는 decoding 을 해야 한다 */
        // json 방식의 자바스크립트객체표현식이 있는데
        // 마지막 부문에 return 할때 뷰페이지가 아니라
        // 데이터만 전달하고 화면은 프론트엔드서버에서 만들꺼에요
        // 자바는 자바스크립트객체를 읽을 방법이 없는데
        // 자바스크립트객체를 자바객체로 변환해주는게 Jackson 변환기 컨버터라고 하는데
        // 클래스로 묶어서 데이터 전송 자바스크립트사용해서 쫙펼칠것인데
        // 자바스크립트객체로 바꾸는걸 잭슨컨벌터라고하고요.
        // 나중엔 값들을 주고 받을때 RequestBody 와 ResponseBody 로 나뉠 것입니다.
        System.out.println(URLDecoder.decode(body,"UTF-8"));

    }
}
