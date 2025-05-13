package com.ino.ajax.controller;

import com.ino.ajax.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class AjaxController {

    @ResponseBody
    @GetMapping(value = "/ajaxTest1.do", produces = "text/html; charset=utf-8")
    public String ajaxTest(String id, String pwd){
        log.debug("param id: {} pwd: {}", id, pwd);
        String searchedName = "홍길동";
        return searchedName;
    }

//    @ResponseBody
//    @PostMapping(value = "/ajaxTest2.do", produces = "text/html; charset=utf-8")
//    public String ajaxTest2(String id, String pwd){
//        log.debug("param id: {} pwd: {}", id, pwd);
//        String searchedName = "홍길동";
//        return searchedName; // 틀린 예시, 이렇게 구현시 jsp로 포워딩 할 것임
//    }

    @ResponseBody
    @PostMapping(value = "/ajaxTest2.do", produces = "text/html; charset=utf-8")
    public String ajaxTest2(UserDto user){
        log.debug("user : {}", user);
        String searchedName = "홍길동";
        return searchedName; // 틀린 예시, 이렇게 구현시 jsp로 포워딩 할 것임
    }

    @ResponseBody
    @PostMapping(value = "/ajaxTest3.do", produces = "text/html; charset=utf-8")
    public String ajaxTest3(@RequestBody UserDto user){
        log.debug("user: {}" + user);
        String searchedName = "kim";
        return searchedName;
    }

    @ResponseBody
    @PostMapping(value = "/ajaxTest4.do", produces = "text/html; charset=utf-8")
    public String ajaxTest3(@RequestBody UserDto user){
        log.debug("user: {}" + user);
        String searchedName = "kim";
        return searchedName;
    }


}
