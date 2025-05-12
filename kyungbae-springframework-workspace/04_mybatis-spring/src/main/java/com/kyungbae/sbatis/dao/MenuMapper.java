package com.kyungbae.sbatis.dao;

import com.kyungbae.sbatis.dto.MenuDto;

import java.util.List;

public interface MenuMapper {
    List<MenuDto> selectMenuList();
    MenuDto selectMenuByCode(int code);
    int insertMenu(MenuDto menu);
    int updateMenu(MenuDto menu);
    int deleteMenu(String[] codes);
}
