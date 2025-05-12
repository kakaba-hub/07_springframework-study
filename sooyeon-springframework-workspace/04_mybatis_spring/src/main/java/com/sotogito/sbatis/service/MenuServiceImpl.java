package com.sotogito.sbatis.service;

import com.sotogito.sbatis.dao.MenuMapper;
import com.sotogito.sbatis.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    private final SqlSessionTemplate sqlSession;

    @Override
    public List<MenuDto> getMenuAll() {
        return sqlSession.getMapper(MenuMapper.class).selectMenuList();
    }

    @Override
    public MenuDto getMenuByMenuCode(int menuCode) {
        return sqlSession.getMapper(MenuMapper.class).selectMenuByMenuCode(menuCode);
    }

    @Override
    public int registerMenu(MenuDto menuDto) {
        try {
            return sqlSession.getMapper(MenuMapper.class).insertMenu(menuDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int modifyMenu(MenuDto menuDto) {
        return sqlSession.getMapper(MenuMapper.class).updateMenu(menuDto);
    }

    @Override
    public int removeMenuByMenuCode(String[] menuCodes) {
        try {
            return sqlSession.getMapper(MenuMapper.class).deleteMenusByMenuCode(menuCodes);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("주문된 상품으로 삭제할 수 없습니다");
        }
    }
}
