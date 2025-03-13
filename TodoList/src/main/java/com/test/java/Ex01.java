package com.test.java;

public class Ex01 {
    public static void main(String[] args) {
        Student s1 = new Student();
        
        s1.setName("홍길동");
        System.out.println(s1.getName());
        
        System.out.println(s1);
        
        Student s2 = new Student("아무개", 20, "남자");
        System.out.println(s2);
    }
}
