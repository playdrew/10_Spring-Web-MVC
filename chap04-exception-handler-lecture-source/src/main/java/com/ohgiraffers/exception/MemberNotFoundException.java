package com.ohgiraffers.exception;

public class MemberNotFoundException extends Exception {
    public MemberNotFoundException(String s) {
        // 부모클래스의 생성자 호출하면서 매개변수선언
        super(s);
    }
}
