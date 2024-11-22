package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.List;
import java.util.Locale;

// 컨트롤러 계층에서 요청이 전달되면 finddAllMenu 호출되면 dao 계층에 findAllMenu 메소드 호출
// 그럼 해당하는 Mapper 파일은 xml 과 소통을 하고요.
@Controller
@RequestMapping("/menu/*")
public class MenuController {
    
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
        // 해당 구문을 수행했을때 db 인설트다되었다 한다음에 alert 창으로 알려주기
        menuService.registMenu(newMenu);
        // addFlashAttribute 는 해당하는 키값으로 데이터를 쓸수있다는 것입니다.
        rttr.addFlashAttribute("successMessage", messageSource.getMessage("regist",new Object[]{newMenu.getName(),newMenu.getPrice()},locale));

        return "redirect:/menu/list";
    }

    @GetMapping("find")
    public String findPage() {
        return "menu/find"; // 검색 폼 뷰 반환
    }

    // POST 요청: 폼 제출 후 메뉴 검색 결과 표시
    @PostMapping("find")
    public String findSelectedMenu(Model model, @RequestParam("menuName") String menuName) {
        // 메뉴 서비스 호출하여 이름으로 메뉴 목록 검색
        List<MenuDTO> findMenu = menuService.findMenu(menuName);

        // 검색된 메뉴 목록을 모델에 추가
        model.addAttribute("findMenu", findMenu);

        // 검색 결과를 출력할 뷰로 이동
        return "menu/findTable";
    }
}