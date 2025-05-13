package com.kyungbae.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage() {
        return "main"; // /WEB-INF/views/main.jsp 경로로 포워딩 : 페이지의 html 문서를 response 하고 web 에서 해당 문서를 렌더링 함
    }
}
