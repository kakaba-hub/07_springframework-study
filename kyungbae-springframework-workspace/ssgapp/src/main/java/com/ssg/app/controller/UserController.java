package com.ssg.app.controller;

import com.ssg.app.dto.UserDto;
import com.ssg.app.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup.page")
    public void signupPage(){}

    @PostMapping("/signup.do")
    public String signup(UserDto user, RedirectAttributes redirectAttributes){
        int result = userService.registUser(user);

        redirectAttributes.addFlashAttribute("message", result > 0 ? "회원가입 성공" : "회원가입 실패");
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/idcheck.do")
    public String idCheck(String checkId){
        int count = userService.getUserCount(checkId);
        return count == 0 ? "NOTUSED" : "USED";
    }

    @PostMapping("/signin.do")
    public String signin(UserDto user, HttpSession session, RedirectAttributes redirectAttributes){
        UserDto selectedUser = userService.getUser(user);
        if (selectedUser != null) {
            session.setAttribute("loginUser", selectedUser);
            redirectAttributes.addFlashAttribute("message", "로그인 성공");
        } else {
            redirectAttributes.addFlashAttribute("message", "로그인 실패");
        }
        return "redirect:/";
    }

    @GetMapping("/signout.do")
    public String signout(HttpSession httpSession){
        httpSession.invalidate(); // httpSession 제거
        return "redirect:/";
    }

    @GetMapping("/myinfo.page")
    public void myinfo(){}

    @ResponseBody
    @PostMapping("/modifyProfile.do")
    public String modifyProfile(@RequestParam("uploadFile") MultipartFile uploadFile, HttpSession httpSession){
        // 현재 로그인되어있는 회원
        UserDto loginUser = (UserDto) httpSession.getAttribute("loginUser");

        int result = userService.modifyUserProfile(loginUser, uploadFile);
        return result > 0 ? "SUCCESS" : "FAIL";

    }
}
