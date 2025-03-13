package com.test.java;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //PrintWriter writer = resp.getWriter();
        //writer.write("<div></div>");
        
        //업무 코드 작성~
        //- DB 작업 > select
        String name = "홍길동";
        req.setAttribute("name", name);
        //resp.sendRedirect("/mvc/hello.jsp");
        
        //서블릿에서는 JSP에서만 제공되는 특별한 객체임
        //pageContext.forward("/mvc/hello.jsp");
        
        //pageContext 역할
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/hello.jsp");
        dispatcher.forward(req, resp);
 
    }
}
