package com.test.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class EX05 {
    public static void main(String[] args) {
        //Ex05_PreparedStatement.java
        
        //PreparedStatement
        //-매개변수 지원
        
        //Statement vs PreparedStatement
        //1. Statement > 정적 SQL
        //2. PreparedStatement > 동적 SQL
        //3. CallableStatement > 프로시저 전용
        
        //insert
        String name = "하마";
        String age = "30";
        String gender = "m";
        String tel = "010";
        String address = "서울시 길동's 하우스";
        
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        PreparedStatement pstat = null;
        
        try {
            conn = util.open();
            
            //1. Statement
//            stat = conn.createStatement();
//            String sql = String.format("insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);
//            
//            int result = stat.executeUpdate(sql);
//            System.out.println(result);
//            
//            stat.close();
            
            //2. PreparedStatement
            //- ? : 오라클의 매개변수 리터럴
            String sql = "insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, ?, ?, ?, ?, ?, default)";
            pstat = conn.prepareStatement(sql);
            
            //쿼리 이스케이프까지 해줌
            pstat.setString(1, name);
            pstat.setString(2, age);
            pstat.setString(3, gender);
            pstat.setString(4, tel);
            pstat.setString(5, address);
            
            int result = pstat.executeUpdate();
            //java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1
            System.out.println(result);
            
            
            conn.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
