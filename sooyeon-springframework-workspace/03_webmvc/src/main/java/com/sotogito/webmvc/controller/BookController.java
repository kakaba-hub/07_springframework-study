package com.sotogito.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;

@RequestMapping("/book") /// 해당 컨트롤러에서는 /book어쩌구 url의 요청만 처리하겠다.
@Controller
public class BookController {

    @RequestMapping(value = "/list.do", method = RequestMethod.GET) // http://localhost:8080/webmvc/book/list.do
    public String bookList() {
        return "book/list"; /// 포워딩할 뷰 경로 반환
        /**
         * 1. servlet-context 에 경로 bean생성
         * 2. main.jsp에 뷰 코드 및 이동 url 작성
         * 3. get 요청에 응답할 list.jsp 작성
         * 3. 컨트롤러에서 list.jsp 넘겨요청 처리 retrun
         */
    }

//    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    @GetMapping("/detail.do")
    public void bookDetail() {
        System.out.println("상세페이지로 포워딩");

        /**
         * 만일 포워딩한 뷰의 경로가 url 매핑값의 경로와 이리할 경우 void 메서드로 해도 됨
         * value = "/book/detail.do" 와 return "book/detail"
         */
//        return "book/detail";
    }

    @GetMapping( "/modifyForm.do")
    public String bookModifyForm() {
        return "book/modify";
    }

    @PostMapping("/modify.do")
    public String modifyBookList() {
        /**
         * 수정을 post하는 url은 modify.do이고 처리후 띄우는 jsp는 redirect
         */
        return "redirect:/book/detail.do?no=x";
    }



}
