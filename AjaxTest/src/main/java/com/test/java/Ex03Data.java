package com.test.java;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.AjaxDAO;
import com.test.java.model.SurveyDTO;

@WebServlet("/ex03data.do")
public class Ex03Data extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex03Data.java
        AjaxDAO dao = new AjaxDAO();
        List<SurveyDTO> list = dao.listSurvey();
        
        //req.setAttribute("list", list);
        req.setAttribute("dto", list.get(0));
        

        req.getRequestDispatcher("/WEB-INF/views/ex03data.jsp").forward(req, resp);
    }
}
