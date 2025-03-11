package com.test.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex03_Receive extends HttpServlet{
    @Override
    //이건 앞에서 보낸 메서드에 따라서 Post/Get이 정해짐
    //if form에서 post로 보내면 오류남 405에러
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //데이터는 스트링으로 넘어옴
        
        //<input type="text" name="name">
        String name = req.getParameter("name");
        
        //<input type="text" name="age">
        String age = req.getParameter("age");
        
        //잘 받았다고 결과 페이지 생성 > 반환
        
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        //1. 데이터를 넣는 방법 -> String.format() : 정적버전
        //2. 자바의 문자열 -> formatted() : 객체 메서드
//        System.out.println("홍길%d동".formatted(10));
        
        writer.write("""
                <html>
                <head>
                    <meta charset = "UTF-8">
                </head>
                <body>
                    <h1>받기</h1>
                    <div>이름 : %s </div>
                    <div>나이 : %s </div>
                </body>
                </html>
                """.formatted(name, age));
        
        writer.close();
        
    }
    
}






