package com.test.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ex03.do")
public class Ex03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex03.java
        //- View 역할 + Model 역할 > 완전 분리
        

        req.getRequestDispatcher("/WEB-INF/views/ex03.jsp").forward(req, resp);
    }
}
