package com.test.java.map;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.MapDAO;
import com.test.java.model.MapDTO;

@WebServlet("/map/add.do")
public class Add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Add.java
        
        String lat = req.getParameter("lat");
        String lng = req.getParameter("lng");
        
        MapDTO dto = new MapDTO();
        dto.setLat(lat);
        dto.setLng(lng);
        
        MapDAO dao = new MapDAO();
        dao.add(dto);
        
        //add.do > ex04.do
        resp.sendRedirect("/api/map/ex04.do");
    }
}
