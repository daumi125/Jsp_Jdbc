package com.test.java.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private String id;
    private String pw;
    private String name;
    private String lv;

}
