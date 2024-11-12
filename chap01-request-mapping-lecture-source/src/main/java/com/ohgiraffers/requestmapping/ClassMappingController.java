package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/order/*")
/* comment. 클래스 레벨에 RequestMapping 어노테이션 사용가능
*           공통되는 URL 을 설정해두고 *(와일드카드)를 이용하면
*           조금 더 포괄적인 URL 패턴을 설정할 수 있다.
* */
// 이런식으로 주문관련 기능 메뉴관련 기능 분화를 시킨다.
public class ClassMappingController {

    /* 1. Class 레벨에 매핑하기 */
    // 처리할 메소드가 4개 order 로 시작해요 클래스수준에서 RequestMapping("order/*")을 해줘서 해결해요
    @GetMapping("/regist")
    public String reigstOrder(Model model){

        model.addAttribute("message","GET 방식의 주문 등록 핸들러 메소드 호출됨...");

        return "mappingResult";
    }

    /* 2. 여러 URL 매핑하기 */
    // modify , delete 동시에 처리
    // 여러개를 나열하고 싶으면 중괄호 사용
    // 이런 식으로는 잘 사용하지 않습니다.
    @RequestMapping(value={"modify","delete"},method = RequestMethod.POST)
    public String modifytAndDelete(Model model){
        model.addAttribute("message","POST 방식의 수정 , 삭제 둘 다 처리하는 핸들러 메소드 호출");
        return "mappingResult";
    }

    /* 3. path variable (url 경로를 타고 오는 값) */
    /* comment. @PathVariable 어노테이션을 통해 요청 URL 로 부터
    *           변수를 받아 올 수 있다.
    *           path variable 로 전달되는 {변수명} 값은 반드시
    *           매개변수 명과 일치해야한다.
    *           만약에 동일하지 않으면 @PathVariable("이름") 을 설정해야
    *           한다.*/
    // 한 행을 식별할때 pk를 쓰는데 메뉴리스트를 보여주는데요 하나클릭했을때 메뉴상세보기가 있을때
    // 예를 들어 주문상세보기를 했어요 . 주문내역이 뜨는데 1번 주문을 보고 싶을때 주문의 엔티티 pk 값을 보내주게 됩니다.
    // 상세조회를 할때 pk로 조회를 하는데요. 1번 관훈 2번 은미님 조회할껀데 3번하면 용운님만 조회할 수 있는 메소드이며 14개를 만들어야 한다는 것입니다.
    // detail/3 이렇게 하면요. 그래서 {orderNo}를 사용해요
    @GetMapping("detail/{orderNo}")
    // @PathVariable 을 담을 int no 매개변수 생성
    // @PathVariable( int orderNo ) 매개변수일치
    // @PathVariable("OrderNo") int no 또는 어노테이션일치
    public String orderDetail(Model model, @PathVariable("orderNo") int no){
        model.addAttribute("message",no + "번 주문 상세 조회 핸들러 메소드 호출됨...");
        return "mappingResult";
    }
}
