package com.test.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.AjaxDAO;
import com.test.java.model.CatDTO;

@WebServlet("/ex11data.do")
public class Ex11Data extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex11Data.java
        String mode = req.getParameter("mode");
        
        AjaxDAO dao = new AjaxDAO();
        
        if (mode.equals("add")) {
            
            String num = req.getParameter("num"); //"01" > "images/catty01.png"
            String n = req.getParameter("n"); //"1" > "cat1"
            
            CatDTO dto = new CatDTO();
            dto.setSrc(num);
            dto.setCatid(n);
            
            int result = dao.add(dto);
            
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    {
                        "result": "%d"
                    }
                    """.formatted(result));
            writer.close();
            
        } else if (mode.equals("edit")) {
            
            String n = req.getParameter("n");
            String x = req.getParameter("x");
            String y = req.getParameter("y");
            
            CatDTO dto = new CatDTO();
            dto.setCatid(n);
            dto.setX(x);
            dto.setY(y);
            
            int result = dao.edit(dto);
            
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            
            PrintWriter writer = resp.getWriter();
            writer.print("""
                    {
                        "result": "%d"
                    }
                    """.formatted(result));
            writer.close();
            
        }

    }

}










