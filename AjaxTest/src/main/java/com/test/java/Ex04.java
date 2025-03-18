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

@WebServlet("/ex04.do")
public class Ex04 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex04.java
        //1. ex04.do > 그냥 접속
        //2. ex04.do?data=y > 버튼 클릭
        String data = req.getParameter("data");
        
        if(data != null && data.equals("y")) {
            AjaxDAO dao = new AjaxDAO();
            List<SurveyDTO> list = dao.listSurvey();
            
            String question = list.get(0).getQuestion();
            req.setAttribute("question", question);
        }
        
        

        req.getRequestDispatcher("/WEB-INF/views/ex04.jsp").forward(req, resp);
    }
}
