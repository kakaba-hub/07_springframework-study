package com.sotogito.webmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/// "/" url 요청시 /WEB-INF/views/,ain.jsp가 보여지도록 forwarding하기
// 스프링 사용하기 전 : 각 url 요청별 처리할 Servlet 클래스 만들어서 처리

/**
 * 스프링 사용 후
 * 1. 요청 처리를 담당할 controller 클래스를 @controller를 총해 빈으로 등록시키기
 * 2. 각 url 요청별 처리할 내용을 메서드로 작성하기
 * 3. 단, url요청과 실행시킬 메서드를 매핑시며주는 @RequestMapping 어노테이션 기술
 */
@Controller
public class MvcController {

//    @RequestMapping(value = "/", method= RequestMethod.GET)
    @RequestMapping(value = {"/","/main.do"}, method= RequestMethod.GET)
    public String welcomePage() {
        System.out.println("MvcController welcome page");
        return "main"; //파일을 문자열로 반환, Bean으로 InternalResourceViewResolver등록 필요
    }

    @RequestMapping(value = "/test1")
    public String registerMember() {
        System.out.println("/tes1");
        return "book/regist";
    }

    @RequestMapping(value = "/test2")
    public String deleteMember() {
        System.out.println("/tes2");
        return "notice/list";
    }


}
