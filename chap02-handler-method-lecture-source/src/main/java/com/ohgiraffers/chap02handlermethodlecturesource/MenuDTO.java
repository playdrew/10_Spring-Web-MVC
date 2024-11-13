package com.ohgiraffers.chap02handlermethodlecturesource;

// DTO 기본조건
// 기본 생성자, 모든 필드를 초기화하는 생성자
// Getter, Setter , ToString
// alt insert
public class MenuDTO {

    /* comment. form 태그의 name 속성과 필드 이름을 맞춰주어야 한다. */
    private String name;
    private int price;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO(){}

    public MenuDTO(String orderableStatus, int categoryCode, int price, String name) {
        this.orderableStatus = orderableStatus;
        this.categoryCode = categoryCode;
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
