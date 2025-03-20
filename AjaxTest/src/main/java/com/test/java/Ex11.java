package com.test.java;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.AjaxDAO;
import com.test.java.model.CatDTO;

@WebServlet("/ex11.do")
public class Ex11 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex11.java
        
        //DB에서 최대 catid 가져오기
        AjaxDAO dao = new AjaxDAO();
        System.out.println("max: " + dao.getMaxCatId());
        int n = dao.getMaxCatId() + 1; //3
        
        //고양이 목록 가져오기
        ArrayList<CatDTO> list = dao.listCat();
        

        req.setAttribute("n", n);
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/ex11.jsp").forward(req, resp);
    }

}












