package com.test.java;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Ex04 {
    public static void main(String[] args) {
        //Ex04_Select
//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
//        m6();
//        m7();
//        m8();
        m9();
    }
    
    private static void m9() {
        Scanner scan = new Scanner(System.in);
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            conn = util.open();
            stat = conn.createStatement();
            int count = 0;
            
            String sql = "select * from tblInsa where buseo = '영업부'";
            rs = stat.executeQuery(sql);
            
            while(rs.next()) {
                System.out.println(rs.getString("name"));
                count++;
            }
            
            rs.close();
            
            //인원수?
            System.out.println("인원수: " + count);
            
            //결과셋(rs)의 레코드 수를 알아내기?
            //> 불가능하다.
            // 이유 : 커서가 가리키는 정보 외 다른 정보는 알 수 없음.
            // count변수 만들어서 개입하기
            
            
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void m8() {
        // 요구사항] 영업부 > 직원수와 직원명단을 출력하시오.
        //1. select count(*) from tblInsa where buseo = '영업부'
        //2. select * from tblInsa where buseo = '영업부'
        
        Scanner scan = new Scanner(System.in);
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select count(*) as cnt from tblInsa where buseo = '영업부'";
            rs = stat.executeQuery(sql);
            
            if(rs.next()) {
                System.out.println("인원수: " + rs.getString("cnt"));
            }
            
            rs.close();
            
            sql = "select * from tblInsa where buseo = '영업부'";
            rs = stat.executeQuery(sql);
            
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            rs.close();
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void m7() {
        // 요구사항] 특정 직원에게 보너스를 지급하시오.
        //- tblInsa + tblBonus
        //1. 모든 직원 명단을 출력하기(tblInsa > 직원번호, 이름, 부서, 직위) > m6참고
        //2. 사용자 > 보너스 지급할 직원의 번호 입력(scan.nextLine())
        //3. 사용자 > 보너스 금액을 입력(scan.nextLine())
        //4. 보너스 지급(insert tblBonus)
        //5. 지급된 내역을 출력하시오(select tblBonus > 직원번호, 이름, 부서, 직위, 보너스 금액) > inner join
        
        
        Scanner scan = new Scanner(System.in);
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            conn = util.open();
            stat = conn.createStatement();
            
           //1. 모든 직원 명단을 출력하기(tblInsa > 직원번호, 이름, 부서, 직위) > m6참고
            String sql = "select * from tblInsa";
            rs = stat.executeQuery(sql);

            while(rs.next()) {
                System.out.printf("%s, %s, %s, %s\r\n"
                        ,rs.getString("num")
                        ,rs.getString("name")
                        ,rs.getString("buseo")
                        ,rs.getString("jikwi"));
            }
            rs.close(); //직원 명단 목록 닫기
            
            //2. 사용자 > 보너스 지급할 직원의 번호 입력(scan.nextLine())
            System.out.print("직원 번호: ");
            String num = scan.nextLine();
            
            //3. 사용자 > 보너스 금액을 입력(scan.nextLine())
            System.out.print("보너스 금액: ");
            String bonus = scan.nextLine();
            
            //4. 보너스 지급(insert tblBonus)
            sql = String.format("insert into tblBonus (seq, num, bonus) values (seqBonus.nextVal, '%s', %s)", num, bonus);
            
            int result = stat.executeUpdate(sql);
            
            if(result > 0) {
                //지급 완료
                //5. 지급된 내역을 출력하시오
                // (select tblBonus > 직원번호, 이름, 부서, 직위, 보너스 금액) > inner join
                sql = "select * from tblInsa i inner join tblBonus b on i.num = b.num order by b.seq desc";
                rs = stat.executeQuery(sql);
                
                while(rs.next()) {
                    System.out.printf("%s, %s, %s, %s, %s원\r\n"
                            ,rs.getString("num")
                            ,rs.getString("name")
                            ,rs.getString("buseo")
                            ,rs.getString("jikwi")
                            ,rs.getString("bonus"));
                }   
                rs.close();
            }else {
                //지급 실패
                System.out.println("추가 실패;;");
            }

            stat.close(); //더이상 SQL 실행 할 일이 없으면 닫는다.
            conn.close(); //접속이 필요 없을 때 닫는다.

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

    private static void m6() {
        // insert > select
        // 주소록 데이터 입력 > 주소록 명단 출력하기
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("이름: ");
        String name = scan.nextLine();
        
        System.out.print("나이: ");
        String age = scan.nextLine();
        
        System.out.print("성별(m,f): ");
        String gender = scan.nextLine();
        
        System.out.print("전화번호: ");
        String tel = scan.nextLine();
        
        System.out.print("주소: ");
        String address = scan.nextLine();
        address = address.replace("'", "''");
        
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        DBUtil util = new DBUtil();
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = String.format("insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);
            
            int result = stat.executeUpdate(sql);
            
            /*
             * 만약 여기서 닫아버린다면?
             * stat.close();
             * 명령문 종료 오류가 생김
             * */
            
            if(result > 0) {
                //N행 N열
                sql = "select * from tblAddress order by seq desc";
                rs = stat.executeQuery(sql);
                
                while(rs.next()) {
                    System.out.printf("%s, %s, %s, %s\r\n"
                            ,rs.getString("seq")
                            ,rs.getString("name")
                            ,rs.getString("gender")
                            ,rs.getString("age"));
                }
                
                rs.close();
                
                
            }else {
                System.out.println("추가 실패;;");
            }
            
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    private static void m5() {
        // 부서명 입력 > 직원 명단 출력
        Scanner scan = new Scanner(System.in);
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            //부서명?? 입력?
            // > 미리 부서명 가져와서 알려주기
            String sql = "select distinct buseo from tblInsa order by buseo";
            rs = stat.executeQuery(sql);
            System.out.println("보고 싶은 부서명을 입력하세요.");
            
            while(rs.next()) {
                System.out.println("- " + rs.getString("buseo"));
            }
            
            //자원의 재활용 방법
            //select 하나당 rs 하나씩 필요
            rs.close(); //부서명 목록 닫기
            
            System.out.println("부서명: ");
            String buseo = scan.nextLine();
            
            sql = String.format("select name from tblInsa where buseo = '%s' order by name", buseo);
            
            rs = stat.executeQuery(sql);
            
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
            
            rs.close(); //직원 명단 목록 닫기

            stat.close(); //더이상 SQL 실행 할 일이 없으면 닫는다.
            conn.close(); //접속이 필요 없을 때 닫는다.
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
}

    private static void m4() {
        // 다중값 반환
        // -N행 1열
        
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select name from tblAddress order by name";
            
            //결과셋의 참조 > 결과 테이블 자신이 아님 -> 그냥 화살표임
            //화살표는 하나임 주의 할 것
            rs = stat.executeQuery(sql);
            
            rs.next();
            String name = rs.getString("name");
            System.out.println(name);
            System.out.println(rs.getString("name")); //강아지
            
            rs.next();
            String name1 = rs.getString("name");
            System.out.println(name1);
            
            System.out.println("=================================");
            
            
            while(rs.next()) {
                String name3 = rs.getString("name");
                System.out.println(name3);
            }
            

            rs.close();
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        
        
    }

    private static void m3() {
        // 다중값 반환
        //- 1행 N열
        
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select * from tblAddress where seq=1";
            
            rs = stat.executeQuery(sql);
            if(rs.next()) {
                String name = rs.getString("name");
                String age = rs.getString("age");
                String address = rs.getString("address");
                
                System.out.println(name);
                System.out.println(age);
                
                //컬럼명 오타
                //부적합한 열 이름 오류
                //에러가 자바인지 오라클인지 확인하기
                System.out.println(address);
                
            }else {
                System.out.println("not found");
            }
            
            
            rs.close();
            stat.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    private static void m2() {
      //단일값 반환
        //- 1행 1열
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select name from tblAddress where seq = 1";
            
            rs = stat.executeQuery(sql);
            
            if( rs.next()) { //** 문제 발생 가능!! 
              //java.sql.SQLException: 마지막 행 다음의 결과 집합
                String name = rs.getString("name"); //문제 발생!!
                
                System.out.println(name);
            } else {
                System.out.println("데이터 없음");
            }
            
            rs.close();
            stat.close();
            conn.close();           
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    

    private static void m1() {
        // 단일값 반환
        // - 1행 1열
        DBUtil util = new DBUtil();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        
        try {
            conn = util.open();
            stat = conn.createStatement();
            
            String sql = "select count(*) as cnt from tblAddress";
            
            //결과셋의 참조 객체(rs)
            rs = stat.executeQuery(sql); //BOF
            
            rs.next(); //커서 레코드 1줄 전진
            
            //현재 커서가 가르키고 있는 레코드의 원하는 컬럼 가져오기
            //- rs.getXXX()
            
            //int count = rs.getInt(1); //컬럼 순서
            //int count = rs.getInt("cnt"); // 컬럼명
            String count = rs.getString("cnt");
            
            System.out.println(count);
            
            rs.close();
            stat.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
