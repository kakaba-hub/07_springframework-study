package com.ssg.app.controller;

import com.ssg.app.dto.BoardDto;
import com.ssg.app.dto.UserDto;
import com.ssg.app.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list.page")
    public void boardListPage(@RequestParam(value = "page", defaultValue = "1") int page, Model model){
        log.debug("사용자가 요청한 페이지: {}", page);

        // 게시글 목록페이지에 필요한 데이터
        // 1) 사용자가 요청한 페이지를 출력시킬 게시글 목록 데이터 (db조회)
        // 2) 사용자가 요청한 페이지에 출력시킬 페이징바의 정보
        Map<String, Object> map = boardService.getBoardAndPaging(page);

        model.addAttribute("list", map.get("list"));
        model.addAttribute("page", map.get("page"));
        model.addAttribute("totalPage", map.get("totalPage"));
        model.addAttribute("beginPage", map.get("beginPage"));
        model.addAttribute("endPage", map.get("endPage"));
    }

    @GetMapping("/regist.page")
    public void boardRegistPage(){}

    @PostMapping("/regist.do")
    public String boardRegist(BoardDto board, HttpSession session, List<MultipartFile> uploadFiles, RedirectAttributes redirectAttributes){
        int userNo = ((UserDto)session.getAttribute("loginUser")).getUserNo();
        board.setBoardWriter(String.valueOf(userNo));

        int result = boardService.registBoard(board, uploadFiles);
        redirectAttributes.addFlashAttribute("message", result > 0 ? "등록 성공" : "등록 실패");

        return "redirect:/board/list.page";
    }

    @GetMapping("/detail.page")
    public void boardDetailPage(int no, Model model){

        Map<String, Object> map = boardService.getBoardDetail(no);
        model.addAttribute("board", map.get("board"));
        model.addAttribute("list", map.get("list"));

    }

}
