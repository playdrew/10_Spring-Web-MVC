package com.ohgiraffers.crud.menu.model.dto;

// 하나의 메뉴는 한개의 카테고리
// 하나의 카테고리는 여러개의 메뉴

import lombok.*;

// 메뉴는 카테고리 코드를 가지고 있어요.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuAndCategoryDTO {

    private int code;
    private String name;
    private int price;
    private String orderableStatus;
    private CategoryDTO categoryDTO;
}
