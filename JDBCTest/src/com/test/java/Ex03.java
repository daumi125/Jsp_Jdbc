package com.test.java;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex03 {
    public static void main(String[] args) {
        //Ex03_Statement.java
        
        /*
         * Connection
         * - 연결/종료
         * 
         * Statement
         * - 모든 SQL 실행
         * 
         * Statement 종류
         * 1. Statement
         *      - 기본
         *      
         * 2. PreparedStatement
         *      - Statement 개량 > 매개 변수 처리 특화
         *      
         * 3. CallableStatement
         *      - Statement 개량 > 프로시저 호출 전용
         * 
         * */
        
//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
        m6();
    }

private static void m6() {
        
        //UI > 사용자 데이터 입력 + DB 저장
        Scanner scan = new Scanner(System.in);
        
        System.out.print("이름: "); //varchar2 > String
        String name = scan.nextLine();
        
        System.out.print("나이: "); //number > String
        String age = scan.nextLine();
        
        System.out.print("성별(m,f): ");
        String gender = scan.nextLine();
        
        System.out.print("전화번호: ");
        String tel = scan.nextLine();
        
        System.out.print("주소: ");
        String address = scan.nextLine();
        
     // '' 이스케이프 할 것
        address = address.replace("'", "''"); //길동's > 길동''s
        
        //insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, '홍길동', 23, 'm', '010-1111-1111', '길동''s 집', default)
        
        Connection conn = null;
        Statement stat = null;
        DBUtil util = new DBUtil();
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = String.format("insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);
            
            //insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, '강아지', 3, 'm', '010-1234-5678', '서울시', default)
            System.out.println(sql);
            
            int result = stat.executeUpdate(sql);
            
            System.out.println(result);
            
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }


private static void m5() {
    //반환값이 없는 모든 쿼리
    //- DML > insert, update, delete
    //- DDL
    //- DCL
    DBUtil util = new DBUtil();
    Connection conn = null;
    Statement stat = null;
    
    try {
        
        conn = util.open();
        stat = conn.createStatement();
        
//        String sql = """
//create table tblAddress(
//    seq number primary key,                                 --pk
//    name varchar2(200) not null,                              --이름
//    age number(3) not null check(age between 0 and 120),    --나이
//    gender char(1) not null check(gender in ('m', 'f')),    --성별(m, f)
//    tel varchar2(15) not null,                              --전화번호
//    address varchar2(300) not null,                         --주소
//    regdate date default sysdate not null                   --등록일
//) 
//                """;
        
        String sql = "create sequence seqAddress";
        
        int result = stat.executeUpdate(sql);
        
        System.out.println(result); //1 or 0
        
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    }

private static void m4() {
        
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "delete from tblAddress where seq = 1";
            
            int result = stat.executeUpdate(sql);
            
            System.out.println(result); //1 or 0
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }


private static void m3() {
        // update
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        try {
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "update tblAddress set age = age + 1";
            
            int result = stat.executeUpdate(sql);
            System.out.println(result); 
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

private static void m2() {
        
        //JDBC
        //1. 기본 설정 > SQL 실행 시(executeXXX) + 자동 커밋 호출 > Auto Commit
        //2. 사용자 설정 > 원하는 시점에 commit/rollback 호출
        
        
        Connection conn = null;
        Statement stat = null;
        DBUtil util = new DBUtil();
        try {
            conn = util.open(); 
            stat = conn.createStatement(); //open
            
            if(!conn.isClosed()) {
                String sql = "insert into tbladdress (seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '호호호', 20, 'm', '010-1234-5678', '서울시 강남구 역삼동', default)";
                
                int result = stat.executeUpdate(sql); //+commit
         
                if(result > 0) {
                    System.out.println("추가 성공!!");
                }else {
                    System.out.println("추가 실패!!");
                }
                
                Scanner scan = new Scanner(System.in);
                scan.nextLine();
                
                //commit 실행
                conn.commit();
                
                
                stat.close();
                conn.close();
                
            } else {
                System.out.println("DB 접속 실패");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

private static void m1() {
        //insert
        Connection conn = null;
        Statement stat = null;
        DBUtil util = new DBUtil();
        try {
            //1.
            conn = util.open(); 
            stat = conn.createStatement(); //open
            
            if(!conn.isClosed()) {
                //2.
                //- 자바는 SQL을 모른다 > SQL을 문자열로 취급한다.(의미x)
                
                //java.sql.SQLSyntaxErrorException: ORA-00933: SQL 명령어가 올바르게 종료되지 않았습니다. > sql 명령어 뒤에 세미콜론을 찍으면 안됨
                /*
                 * String sql =
                 * "insert into tbladdress (seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678', '서울시 강남구 역삼동', default);"
                 * ;
                 */
                
                String sql = "insert into tbladdress (seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678', '서울시 강남구 역삼동', default)";
                
                //반환값이 없는 쿼리
                //-int stat.executeUpdate(String sql)
                
                //반환값이 있는 쿼리
                //- ResultSet stat.executeQuery(String sql)
                
                //SQL Developer > Ctrl + Enter > SQL 서버 전송(실행)
                //- result > SQL을 실행 후 적용된 행의 개수를 반환
                int result = stat.executeUpdate(sql);

                //System.out.println(result); //1
                
                if(result > 0) {
                    System.out.println("추가 성공!!");
                }else {
                    System.out.println("추가 실패!!");
                }
                
                //자원 정리
                stat.close();
                conn.close();
                
                
                
                
            } else {
                System.out.println("DB 접속 실패");
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
