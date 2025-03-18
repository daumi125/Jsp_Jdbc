package com.test.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.AjaxDAO;

@WebServlet("/ex06data.do")
public class Ex06Data extends HttpServlet {
    @Override
    //요청방식이 POST 일 때 주의
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex06Data.java
        
         String txt1 = req.getParameter("txt1");
         System.out.println(txt1);
         
         AjaxDAO dao = new AjaxDAO(); 
         String jikwi = dao.getJikwi(txt1);
         System.out.println(jikwi);
         
         resp.setCharacterEncoding("UTF-8"); 
         PrintWriter writer = resp.getWriter();
         writer.print(jikwi); writer.close();
         
        
        /*
         * String txt1 = req.getParameter("txt1"); System.out.println(txt1);
         */

        
        /*
         * resp.setCharacterEncoding("UTF-8"); PrintWriter writer = resp.getWriter();
         * writer.print("눈이 옵니다."); writer.close();
         */
    }
}








