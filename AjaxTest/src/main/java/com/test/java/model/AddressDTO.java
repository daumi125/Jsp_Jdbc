package com.test.java.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AddressDTO {
    private String seq;
    private String name;
    private String age;
    private String tel;
    private String address;
    private String gender;
    private String regdate;
}
