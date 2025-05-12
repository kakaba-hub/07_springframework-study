package com.kyungbae.sbatis.service;

import com.kyungbae.sbatis.dao.MenuMapper;
import com.kyungbae.sbatis.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService{

    private final SqlSessionTemplate sqlSession;

    @Override
    public List<MenuDto> getAllMenu() {
        return sqlSession.getMapper(MenuMapper.class).selectMenuList();
    }

    @Override
    public MenuDto getMenuByCode(int code) {
        return sqlSession.getMapper(MenuMapper.class).selectMenuByCode(code);
    }

    @Override
    public int menuRegister(MenuDto menu) {
        return sqlSession.getMapper(MenuMapper.class).insertMenu(menu);
    }

    @Override
    public int menuModifier(MenuDto menu) {
        return sqlSession.getMapper(MenuMapper.class).updateMenu(menu);
    }

    @Override
    public int menuRemover(String[] codes) {
        return sqlSession.getMapper(MenuMapper.class).deleteMenu(codes);
    }
}
