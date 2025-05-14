package com.kyungbae.ajax.controller;

import com.kyungbae.ajax.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class AjaxController {

    /*
       ## @ResponseBody
       1. 컨트롤러 메소드의 반환값을 HTTP 본문(body)에 담아서 전송하도록 지정하는 어노테이션
        => 반환값을 뷰경로로 해석하지 않고, 클라이언트가 원하는 데이터 형식으로 변환해서 전달해야될 때 사용
     */
    @ResponseBody // return 되는 String 값을 자동으로 포워딩하는것을 방지
    @GetMapping(value = "/ajaxTest1.do", produces = "text/html; charset=utf-8")
    public String ajaxTest1(String id, String pw){
        log.debug("param id:{}, pwd:{}", id, pw);
        String searchName = "홍길동"; // 조회된 회원의 이름을 응답데이터로 돌려주기
        return searchName;
    }

    /*
    @ResponseBody
    @PostMapping(value = "/ajaxTest2.do", produces = "text/html; charset=utf-8")
    public String ajaxTest2(String id, String pw){
        log.debug("param id:{}, pwd:{}", id, pw);
        String searchName="강찬밥";
        return searchName;
    }
     */

    // Dto로 받았을때 test
    @ResponseBody
    @PostMapping(value = "/ajaxTest2.do", produces = "text/html; charset=utf-8")
    public String ajaxTest2(UserDto user){
        log.debug("user: {}", user);
        String searchName="강찬밥";
        return searchName;
    }

    // Jackson 라이브러리 : JSON문자열 <==자동변환==> Java객체

    @ResponseBody
    @PostMapping(value = "/ajaxTest3.do", produces = "text/html; charset=utf-8")
    public String ajaxTest3(@RequestBody UserDto user) { // DTO 대신 map 객체로 담을수도 있다
        log.debug("user: {}", user);
        String searchName= "김심심";
        return searchName;
    }

    @ResponseBody
    @GetMapping(value = "ajaxTest4.do")
    public UserDto ajaxTest4(int no){
        log.debug("param no: {}", no);
        UserDto searchedUser = new UserDto("id01", "pw01", "최팔팔", 30);
        return searchedUser; // jsp로 전송할때 Java 객체 => JSON 문자열로 Jackson이 자동 parsing 해줌
        // Java객체(DTO) => JSON문자열 '{"id":"id01", "pw":"pw01", "name":"최팔팔", "age":30}'
    }

    @ResponseBody
    @GetMapping(value = "ajaxTest5.do")
    public List<UserDto> ajaxTest5(){
        List<UserDto> selectedUsers = List.of(
                new UserDto("id01", "pw01", "가가가", 10),
                new UserDto("id02", "pw02", "가가나", 20),
                new UserDto("id03", "pw03", "가가다", 30),
                new UserDto("id04", "pw04", "가가라", 40)
        );
        return selectedUsers;
    }
}
