package com.test.java;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.TodoDAO;
import com.test.java.model.TodoDTO;

@WebServlet("/main.do")
public class Main extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Main.java
		
		//1. DB 작업 > select
		//2. 결과 > 전달 > 출력(JSP)
		
		//1.
		TodoDAO dao = new TodoDAO();
		
		ArrayList<TodoDTO> list = dao.list();
		
		//2.
		req.setAttribute("list", list);

		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
	}

}










