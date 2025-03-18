package com.test.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.AjaxDAO;

@WebServlet("/ex07data.do")
public class Ex07Data extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex07Data.java
        //- 처음 팝업이 뜰 때
        

        req.getRequestDispatcher("/WEB-INF/views/ex07data.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //- 아이디 중복 검사 버튼을 클릭했을 때
        
        String id = req.getParameter("id");
        
        AjaxDAO dao = new AjaxDAO();
        //0(사용 가능), 1(이미 사용중 > 사용 불가능)
        int result = dao.checkId(id);
        
        req.setAttribute("result", result);
        req.setAttribute("id", id);
        req.getRequestDispatcher("/WEB-INF/views/ex07data.jsp").forward(req, resp);
    }
}
