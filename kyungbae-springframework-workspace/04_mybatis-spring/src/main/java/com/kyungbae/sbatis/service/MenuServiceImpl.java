package com.kyungbae.sbatis.service;

import com.kyungbae.sbatis.dao.MenuMapper;
import com.kyungbae.sbatis.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 트랜잭션 테스트 (두개의 메뉴 일괄등록 - 둘 중에 하나의 메뉴 등록시 실패되면 rollback)
    /*
    @Override
    public int registTwoMenu() {

        // 테스트용 메뉴 2개 세팅
        MenuDto menu1 = MenuDto.builder()
                .menuName("김장김치")
                .menuPrice(1000)
                .categoryCode(4)
                .orderableStatus("Y")
                .build(); // insert 정상작동할 메뉴
        MenuDto menu2 = new MenuDto(); // 실패할 메뉴

        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result1 = menuMapper.insertMenu(menu1); // 새로운 트랜잭션 발생
        int result2 = menuMapper.insertMenu(menu2); // 새로운 트랜잭션 발생

        return result1 * result2;
        // mybatis-spring 에서는 기본적으로 각 쿼리실행 때마다 새로운 트랜잭션을 만들어서 각 트랜잭션으로 관리함
        // 별도의 예외가 발생되지 않으면 commit 을 진행시킴
    }
     */

    // 트랜잭션 처리 방법 1. 선언적 트랜잭션 @Transactional
    /*
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registTwoMenu() {

        MenuDto menu1 = MenuDto.builder()
                .menuName("어노테이션김치")
                .menuPrice(1200)
                .categoryCode(4)
                .orderableStatus("Y")
                .build();
        MenuDto menu2 = new MenuDto();

        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result1 = menuMapper.insertMenu(menu1); // 최초로 트랜잭션 발생
        int result2 = menuMapper.insertMenu(menu2); // 기존에 존재하는 트랜잭션에 같이 묶임

        // RuntimeException 관련 예외가 발생하면 트랜잭션 rollback (Default)
        // 예외 설정 변경할 시 @Transactional(rollbackFor = 예외) 로 변경 가능

        return result1 * result2;

    }
     */

    // 트랜잭션 처리방법 2. AOP Advice 등록
    @Override
    public int registTwoMenu() {

        MenuDto menu1 = MenuDto.builder()
                .menuName("AOP김치")
                .menuPrice(2000)
                .categoryCode(4)
                .orderableStatus("Y")
                .build();
        MenuDto menu2 = new MenuDto();

        MenuMapper menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result1 = menuMapper.insertMenu(menu1);
        int result2 = menuMapper.insertMenu(menu2);

        return result1 * result2;

    }
}
