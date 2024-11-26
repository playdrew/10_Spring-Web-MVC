package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuAndCategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

// 컨트롤러 계층에서 요청이 전달되면 finddAllMenu 호출되면 dao 계층에 findAllMenu 메소드 호출
// 그럼 해당하는 Mapper 파일은 xml 과 소통을 하고요.
@Controller
@RequestMapping("/menu/*")
public class MenuController {

    // 로깅이란 우리가 들어온 흔적 남김
    /*comment. Logging
    *          어플리케이션이 실행 중 발생하는 이벤트(정보,경고,오류)등을
    *          기록하는 과정.
    *          이는 사용자 화면을 위해 만드는 기능이 아닌, 개발자가 어플리케이션의
    *          상태를 추척하고, 모니터링 하는 데 사용할 수 있다.*/
    // 로거는 인터페이스이 므로 인스턴스 만들수 없음. LogManager 로 만듬
    private static final Logger logger = LogManager.getLogger(MenuController.class);
    
    private final MenuService menuService;

    /* bean 으로 등록한 메세지 소스 사용*/
    private final MessageSource messageSource;
    
    // ioc 컨테이너에서 menuService 와 menuController 와 연관성을 만든것
    @Autowired
    public MenuController(MenuService menuService,MessageSource messageSource){
        this.menuService = menuService;
        this.messageSource = messageSource;
    }

    @GetMapping("list")
    public String findMenuList(Model model){
        
        // 전체 메뉴 조회는 menuDTO 타입이 여러개 이기 때문에
        // List 
        List<MenuDTO> menuList = menuService.findAllMenus();
        for(MenuDTO menu : menuList){
            System.out.println("menu = " + menu);
        }

        model.addAttribute("menuList",menuList);
        
        // menu/list 라는 뷰페이지작성
        return "menu/list";
    }

    @GetMapping("regist")
    public void registPage(){}

    // fetch 에 대한 요청 url 을 핸들할 핸들러메소드 생성
    // 자바스크립트공간이므로 일반적인 방식으로 생성하면 안되요
    // @ResponseBody 생성
    // 그리고 요청 url 을 value = "category" 그리고 두번째 인자로 응답할 공간을
    // 컨트롤러는 뷰까지 리턴할 의무가 있는데 리스판스바디가 붙이면 뷰까지 리턴할 필요없이 데이터만 리턴해요
    // 리스판스바디는 리턴하는게 데이터에요
    // 자바객체를 멤버 DTO 를 넘긴다고 했을때 객체타입으로 보낸다해도 프로듀스타입을 설정하면 알아서 자바스크립트 객체표현법으로 JSON 형식의 값으로 변환을 해줘요
    
    /* comment. @ResponseBody 어노테이션은
                기존의 컨트롤러의 역할은 view 를 마지막에
                리턴하는게 의무이다. 하지만 @ResponseBody
                는 view 를 리턴하는 의무가 아닌, 데이터를
                리턴할 수 있게 만든다.
                json -> 자바스크립트 객체 표기법을 의미한다 */

    @ResponseBody
    @GetMapping(value = "category", produces = "application/json;charset=UTF-8")
    public List<CategoryDTO> findCategory(){
         return menuService.findAllCategory();
    }

    // 화면상에서 입력하는 데이터는 name 속성이 중요하다
    // name 과 MenuDTO 를 동일하게 했다
    // redirect에 메시지를 담기 위해 RedirectAttributes 사용
    // @ModelAttribute form 태그를 뭉태기로 받기 위해서 사용
    // redirect 시 요청이 2번일어나므로 저장이 안되지만 그럼에도 값을 저장할려고 하면 RedirectAttributes 를
    // 해서 잠시만 저장하게해요
    // resource/message/message_ko_KR.properties
    // 위치정보를 가지고 있는 Locale 매개변수 선언
    @PostMapping("regist")
    public String registMenu(@ModelAttribute MenuDTO newMenu, RedirectAttributes rttr, Locale locale){
        /* comment. @ModelAttribute : form 태그로 묶어서 넘어오는 값은 클래스 자료형에
        *           담기 위해 작성하는 어노태이션
        *           RedirectAttributes : 리다이렉트 시 저장할 값이 있으면 사용하는 객체
        * */

        /* comment.
        *       TRACE : 상세한 디버깅 정보(매우 세밀한 로그)
        *       DEBUG : 개발 중 디버깅용 정보
        *       INFO : 일반적인 실행 정보
        *       WARN : 잠재적인 문제 경고
        *       ERROR : 실행 중 발생한 오류
        * */
        logger.info("Locale : {}", locale);
        logger.info("newMenu : {} ", newMenu);

        // 해당 구문을 수행했을때 db 인설트다되었다 한다음에 alert 창으로 알려주기
        menuService.registMenu(newMenu);
        // addFlashAttribute 는 해당하는 키값으로 데이터를 쓸수있다는 것입니다.
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("regist",new Object[]{newMenu.getName(),newMenu.getPrice()},locale));

        return "redirect:/menu/list";
    }

    @GetMapping("findByName")
    public String findPage() {
        return "/menu/findByName";
    }

    // POST 요청: 폼 제출 후 메뉴 검색 결과 표시
    @PostMapping("findByName")
    public String findSelectedMenu(Model model, @RequestParam("menuName") String menuName) {

        List<MenuDTO> findMenuByName = menuService.findMenuByName(menuName);
        model.addAttribute("findMenuByName", findMenuByName);
        return "menu/findTable";
    }

    // 조인 리스트를 핸들러할 핸들러메소드 생성
    // 값을 담기 위한 모델객체 생성
    @GetMapping("join/list")
    public String menuAndCategoryList(Model model){

        List<MenuAndCategoryDTO> joinList = menuService.findAllMenuAndCategory();

        model.addAttribute("joinList",joinList);

        return "menu/join";
    }

    /* comment. DELETE 구문 생성
    *           인덱스 페이지에서 delete 버튼 누르면
    *           메뉴 코드를 입력할 수 있는 input 태그와
    *           전송 버튼을 보여주는 view 페이지로 이동
    *           -
    *           이후 값 전달 받아 삭제하는 기능 생성
    *           전송 버튼 누르면 menu/list 페이지로 redirect 진행
    *           리다이랙트 시 사용자에게 alert 창으로
    *           "몇 번 메뉴 삭제 완료되었습니다." 메세지 출력
    * */
    @GetMapping("deleteByCode")
    public String deleteByCodePage(){
        return "menu/deleteByCode";
    }

    @PostMapping
    public String deleteByCode(int menuCode, RedirectAttributes rttr,Locale locale,@ModelAttribute MenuDTO deleteMenuDTOByCode){

        rttr.addFlashAttribute("successMessage", messageSource.getMessage("delete",new Object[]{deleteMenuDTOByCode.getName()},locale));
        menuService.deleteByCode(menuCode);
        return "redirect:/menu/list";

        // 뷰페이지에선 절대경로 / 붙여주기 리턴에선 상대경로
    }

}