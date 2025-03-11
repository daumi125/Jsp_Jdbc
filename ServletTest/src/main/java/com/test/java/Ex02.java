package com.test.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//1. 서블릿 클래스 선언
//a.javax.servlet.http.HttpServlet > 클래스를 상속한다.
public class Ex02 extends HttpServlet{
    //2. doGet/doPost 메서드 선언
      //a. 매개 변수 작성
          //1. javax.servlet.http.HttpServletRequest
          //2. javax.servlet.http.HttpServletResponse
      //b. 예외 미루기
          //1. java.io.IOException
          //2. javax.servlet.ServeltException
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //3. 동적 HTML 페이지 작성
        //HttpServletResponse 객체의 getWriter() 메서드 호출
        
        Random rnd = new Random();
        int dan = rnd.nextInt(8)+2;
        
        //PrintWriter 객체 == 페이지를 제작하는 도구
        //- writer.printXXX("페이지 내용")
        //동적으로 생선된 HTML 파일 > 어디에 위치? > 모름: 톰캣이 알아서 관리하기 때문임
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<meta charset='UTF-8'>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write(String.format("<h1>%d단</h1>", dan));
        for(int i=1; i<=9; i++) {
            writer.write(String.format("<div>%d x %d = %d</div>", dan, i, dan*i));
        }
        writer.write("</body>");
        writer.write("</html>");
        
        
        writer.close();
    }
}
//실행하면 url
//http://localhost:8090/servlet/servlet/com.test.java.Ex02

//가짜이름 루트까지는 작성해줘야함.
//새로고침도 자동으로 해줌
//수정파일도 자동으로 톰캣이 해줌
//http://localhost:8090/servlet/ex02.do



