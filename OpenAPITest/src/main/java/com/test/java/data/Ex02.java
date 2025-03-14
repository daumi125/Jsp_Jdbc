package com.test.java.data;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.BookDAO;
import com.test.java.model.BookDTO;

/*
 * public class Ex02 {
 * 
 * public static void main(String[] args) { BookDAO dao = new BookDAO();
 * ArrayList<BookDTO> list = dao.search("오라클");
 * 
 * for(BookDTO dto : list) { System.out.println(dto.getTitle()); } } }
 */

//북마크&상태보존 > get

@WebServlet("/ex02.do")
public class Ex02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex02.java
        //- 그냥 접근
        //System.out.println("그냥 페이지 접근");
        
        //그냥 접근? 검색?
        //-ex02.do
        //-ex02.do?word=자바
        //-ex02.do?word=
        
        String word = req.getParameter("word");
        String page = req.getParameter("page");
        
        if(word != null) {
            BookDAO dao = new BookDAO();
            ArrayList<BookDTO> list = dao.search(word, page);
            
            req.setAttribute("list", list);
            req.setAttribute("word", word);
            req.setAttribute("page", page);
        } else {
            //그냥 > 일x
            req.setAttribute("page", "1");
        }

        req.getRequestDispatcher("/WEB-INF/views/ex02.jsp").forward(req, resp);
    }
    
    //@Override
    /*protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex02.java
        //- 그냥 검색
        
        System.out.println("검색 페이지 접근");
        
        //1. 데이터 가져오기
        req.setCharacterEncoding("UTF-8");
        String word = req.getParameter("word");
        
        //2.Open API 작업 작업 > 느슨한 결합
        BookDAO dao = new BookDAO();
        ArrayList<BookDTO> list = dao.search(word);
        
        //3.JSP호출 + 전달
        req.setAttribute("list", list);
        req.setAttribute("word", word);
        req.getRequestDispatcher("/WEB-INF/views/ex02.jsp").forward(req, resp);
    }*/
}
