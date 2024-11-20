package com.ohgiraffers.thymeleaf;

import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lecture/*")
public class LectureController {

    @GetMapping("expression")
    // 데이터와 뷰를 동시에 지정할 수 있는 ModelAndView
    public ModelAndView expression(ModelAndView mv){

        mv.addObject("member",new MemberDTO("조평훈",20,'남',"경기도 수원시"));

        // hello 란 키값과 벨류
        mv.addObject("hello","hi~<h2>타임리프</h2>");
        // 요청 url 과 setting url 은 같은 수도 있지만 다를 수도 있다.
        // 요청 url 과 우리가 보여줄 view 는 전혀 다르다.
        mv.setViewName("lecture/expression");

        return mv;
    }

    @GetMapping("conditional")
    public ModelAndView conditional(ModelAndView mv){

        mv.addObject("num",1);
        mv.addObject("str","바나나");
        mv.setViewName("lecture/conditional");

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑",4,'여',"서울시 노진구"));
        memberList.add(new MemberDTO("시진핑",76,'여',"베이징 사천구"));
        memberList.add(new MemberDTO("티니핑",8,'남',"서울시 광진구"));
        memberList.add(new MemberDTO("핑구",4,'여',"서울시 핑구"));

        mv.addObject("memberList",memberList);

        return mv;
    }

    @GetMapping("etc")
    public ModelAndView etc(ModelAndView mv){
        SearchCriteria criteria = new SearchCriteria(1,10,3);

        List<MemberDTO> memberList = new ArrayList<>();
        memberList.add(new MemberDTO("하츄핑",4,'여',"서울시 노진구"));
        memberList.add(new MemberDTO("시진핑",76,'여',"베이징 사천구"));
        memberList.add(new MemberDTO("티니핑",8,'남',"서울시 광진구"));
        memberList.add(new MemberDTO("핑구",4,'여',"서울시 핑구"));
        mv.addObject("memberList",memberList);

        // key = value 형식으로 저장 가능하지만, key 를 작성하지 않을 시
        // 인스턴스의 타입 = 클래스명이 곧 키 값이 된다.
        mv.addObject(criteria);

        Map<String,MemberDTO> memberMap = new HashMap<>();
        memberMap.put("1",new MemberDTO("하츄핑",4,'여',"서울시 노진구"));
        memberMap.put("2",new MemberDTO("시진핑",76,'여',"베이징 사천구"));
        memberMap.put("3",new MemberDTO("티니핑",8,'남',"서울시 광진구"));
        memberMap.put("4",new MemberDTO("핑구",4,'여',"서울시 핑구"));

        mv.addObject("memberMap",memberMap);

        mv.setViewName("lecture/etc");
        return mv;
    }

    @GetMapping("fragment")
    public ModelAndView fragment(ModelAndView mv){

        mv.addObject("test","value");
        mv.addObject("test2","value2");


        // view 의 이름을 설정 prefix resources/templates 이 붙고 뒤에는 .html 이 붙는다.
        // 이런 뷰네임을 설정했다는 것은 해당 디렉토리에 이러한 파일을 만들어라는 것
        mv.setViewName("lecture/fragment");

        return mv;
    }
}
