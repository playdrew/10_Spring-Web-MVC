package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/* comment. myBatis 전용 어노테이션으로 Repository 의
*           더 구체적인 기능을 가진 어노테이션이다.*/
// Repository 는 데이터베이스에서 가장 마지막으로 접근할때 사용하는데 Mapper 는 xml 파일을 읽기 위한 좀 더 구체화된 기능이라고 보면 됩니다.

@Mapper
public interface MenuMapper {

    // findAllMenus 쿼리문을 매핑하는 id
    List<MenuDTO> findAllMenus();

    List<CategoryDTO> findAllCategory();


    // 데이터베이스의 dml 에 들어왔어요
    void registNewMenu(MenuDTO newMenu);

    List<MenuDTO> findMenu(String menuName);
}
