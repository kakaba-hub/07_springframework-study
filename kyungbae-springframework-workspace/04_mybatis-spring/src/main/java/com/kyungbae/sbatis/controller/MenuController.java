package com.kyungbae.sbatis.controller;

import com.kyungbae.sbatis.dto.MenuDto;
import com.kyungbae.sbatis.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/menu")
@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/list.page")
    public void menuListPage(Model model){
        model.addAttribute("list", menuService.getAllMenu());
    }

    @GetMapping("/regist.page")
    public void menuRegisterPage(){}

    @PostMapping("/regist.do")
    public String menuRegister(MenuDto menu, RedirectAttributes redirectAttributes){
        int result = menuService.menuRegister(menu);

        String message = null;
        if (result > 0) {
            message = menu.getMenuCode() + "번 메뉴로 " + menu.getMenuName() + "이(가) 성공적으로 등록되었습니다.";
        } else {
            message = "등록에 실패하였습니다.";
        }
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/menu/list.page";
    }

    @GetMapping("/detail.page")
    public void menuDetailPage(int code, Model model){
        model.addAttribute("menu", menuService.getMenuByCode(code));
    }

    @GetMapping("/modify.page")
    public void menuModifyPage(int code, Model model){
        model.addAttribute("menu", menuService.getMenuByCode(code));
    }

    @PostMapping("/modify.do")
    public String menuModifier(MenuDto menu, RedirectAttributes redirectAttributes){
        int result = menuService.menuModifier(menu);

        String message = null;
        if (result > 0) {
            message = "성공";
        } else {
            message = "실패";
        }
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/menu/detail.page?code=" + menu.getMenuCode();
    }

    @PostMapping("/remove.do")
    public String menuRemover(String[] code, RedirectAttributes redirectAttributes){
        int result = menuService.menuRemover(code);

        String message = null;
        if (result == code.length) {
            message = result + "개의 메뉴가 성공적으로 삭제되었습니다.";
        } else {
            message = "메뉴 삭제 실패";
        }
        redirectAttributes.addFlashAttribute("message", message);

        return "redirect:/menu/list.page";
    }

    @GetMapping("/transaction.do")
    public void transactionTest() {
        int result = menuService.registTwoMenu();
    }

}
