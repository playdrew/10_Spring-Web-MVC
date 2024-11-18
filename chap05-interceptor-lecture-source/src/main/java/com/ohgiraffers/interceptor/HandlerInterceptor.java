package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

// 인터셉터를 위한 인터페이스 HandlerInterceptor 상속
@Component
public class HandlerInterceptor implements org.springframework.web.servlet.HandlerInterceptor {

    private final MenuService menuService;

    /* comment. Interceptor 는 Spring 에서 제공하는 기능이기 때문에
    *           우리가 Bean 으로 등록해둔 객체들을 언제든 참조(의존성 주입)
    *           할 수 있다.
    * */

    @Autowired
    public HandlerInterceptor(MenuService menuService){
        this.menuService = menuService;
    }

    // 버튼에 링크 적어둔 다음에 예를 처리할 핸들러메소드
    // 뷰에서 요청할때 서블릿컨테이너 에서 거름망해주는게 필터 서블릿컨트롤러 에서 --인터셉터--> ioc(컨트롤러 서비스 레파지토리)

    /* 전처리 메소드 */
    // pre 이전에 동작한 다는 뜻입니다.
    // Controller 에 도달하기 전에 가로채요.. 시작시간 request 에 동작
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandler 호출됨 ...");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);

        /* comment. true 값을 리턴하게 되면 Interceptor 의 다음 계층인
        *           Controller 를 호출하게 된다.
        *           false 값을 리턴하게 되면 컨트럴러의 핸들러 메소드를
        *           호출하지 않게 된다.*/

        return true;
    }

    // 컨트롤러가 본인의 역할을 다하고 뷰를 리턴하는 도중에 인터셉트하는 것이고요
    /* 후처리 메소드 */
    // 인터센터는 서블릿컨테이너와 ioc 컨테이너 사이에 동작하는 녀석
    // 전처리 요청으로 request 메소드로 startTime 을 잰거고요
    // 컨트롤러의 마지막 역할은 view 페이지를 리턴
    // 여기선 아직 데이터는 담지 않았습니다.
    // 인터셉터는 응답할때도 낚아 챌수 있기 때문에 화면정보구성가능
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler 호출됨...");
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval",endTime-startTime);
    }

    // 화면에 데이터를 담아준 이후에 동작하는 녀석
    /* 가장 마지막 view 가 렌더링 된 이후 동작하는 메소드 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출됨...");
        // 빈으로 등록해놓았던 클래스에 접근할수 있는지 테스트
        menuService.method();
    }
}
