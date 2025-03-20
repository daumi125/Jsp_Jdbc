package com.test.java.test;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

@WebServlet("/test/testconnection.do")
public class ConnectionTest extends HttpServlet {
    
    private void doget() {
        // TODO Auto-generated method stub

    }
    
    
    public static void testConnection() {
        
        /*
         * DB 연결 방식
         * 1. 기존 방식
         *  - 필요 시 > Connection  객체 생성 > 사용 > 소멸
         *  
         * 2. 새로운 방식
         *  - 커넥션 풀(Connection Pool)
         *  - Connection 객체들을 관리하는 시스템
         *
         * 3. 
         * 
         * 
         * 
         * 
         * */
        
        //데이터베이스 연결
        
        try {
            Connection conn = null;
            
            //Context.xml 열길
            Context ctx = new InitialContext();
            Context env = (Context)ctx.lookup("java:comp/env");
            
            //데이터 소스 반환
            DataSource ds = (DataSource)env.lookup("jdbc/pool");
            
            //데이터베이스 연결
            conn = ds.getConnection();
            
            System.out.println(conn.isClosed());
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
