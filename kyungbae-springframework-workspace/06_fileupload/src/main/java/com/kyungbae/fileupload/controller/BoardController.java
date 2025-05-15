package com.kyungbae.fileupload.controller;

import com.kyungbae.fileupload.dto.BoardDto;
import com.kyungbae.fileupload.service.BoardService;
import com.kyungbae.fileupload.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/register1.do")
    public String boardRegister1(BoardDto board, MultipartFile imageFile){
//        log.debug("board: {}", board);
//        log.debug("file: {}", imageFile);
//        log.debug("origin file name: {}", imageFile.getOriginalFilename());
        int result = boardService.registOneFileBoard(board, imageFile);

        if (result > 0) {
            log.debug("게시글 등록 성공");
        } else {
            log.debug("게시글 등록 실패");
        }

        return "redirect:/";
    }

    @PostMapping(value = "/regist2.do")
    public String registBoard2(BoardDto board, List<MultipartFile> imageFiles){
        int result = boardService.registManyFileBoard(board, imageFiles);

        if (result == imageFiles.size()+1) {
            log.debug("게시글 등록 성공");
        } else {
            log.debug("게시글 등록 실패");
        }

        return "redirect:/";
    }

}
