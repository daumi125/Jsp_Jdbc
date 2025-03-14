package com.test.java.map;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/map/ex01.do")
public class Ex01 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex01.java
        String no = req.getParameter("no"); //ex01.do?no=01

        req.getRequestDispatcher("/WEB-INF/views/map/ex" + no + ".jsp").forward(req, resp);
    }

}





