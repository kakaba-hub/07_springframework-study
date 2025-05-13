package com.kyungbae.sbatis.service;

import com.kyungbae.sbatis.dto.MenuDto;

import java.util.List;

public interface MenuService {

    // 전체메뉴목록조회서비스
    List<MenuDto> getAllMenu();

    // 메뉴상세조회서비스
    MenuDto getMenuByCode(int code);

    // 메뉴등록서비스
    int menuRegister(MenuDto menu);

    // 메뉴수정서비스
    int menuModifier(MenuDto menu);

    // 메뉴일괄삭제서비스
    int menuRemover(String[] codes);

    // 트랜잭션테스트용
    int registTwoMenu();

}
