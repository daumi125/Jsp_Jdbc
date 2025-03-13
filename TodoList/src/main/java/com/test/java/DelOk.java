package com.test.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.TodoDAO;

@WebServlet("/delok.do")
public class DelOk extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //DelOk.java
        //1. 데이터 가져오기(seq)
        //2. DB 작업 > delete
        //3. 호출하기
        
        //1.
        String seq = req.getParameter("seq");
        
        //2.
        TodoDAO dao = new TodoDAO();
        int result = dao.del(seq);
        
        //3.
        req.setAttribute("result", result);
        req.getRequestDispatcher("/WEB-INF/views/delok.jsp").forward(req, resp);
    }

}











