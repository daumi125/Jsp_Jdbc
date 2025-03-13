package com.test.java.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//역할
//- 레코드 1줄을 담기 위한 상자

@Getter
@Setter
@ToString
public class TodoDTO {
    private String seq;
    private String todo;
    private String state;
    private String regdate;
}
