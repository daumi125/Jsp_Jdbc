package com.test.java.map;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.MapDAO;
import com.test.java.model.MapDTO;

@WebServlet("/map/ex04.do")
public class Ex04 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex04.java
        
        MapDAO dao = new MapDAO();
        ArrayList<MapDTO> list = dao.list();
        
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/map/ex04.jsp").forward(req, resp);
    }

}














