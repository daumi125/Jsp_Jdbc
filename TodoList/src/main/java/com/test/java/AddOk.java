package com.test.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.TodoDAO;

@WebServlet("/addok.do")
public class AddOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//AddOk.java
		//1. 데이터 가져오기
		//2. DB 작업 > insert
		//3. 호출하기
		
		//1.
		req.setCharacterEncoding("UTF-8");
		String todo = req.getParameter("todo");
		
		//2.
		TodoDAO dao = new TodoDAO();
		int result = dao.add(todo); //성공(1), 실패(0)
		
		//3.
		req.setAttribute("result", result);
		req.getRequestDispatcher("/WEB-INF/views/addok.jsp").forward(req, resp);
	}

}










