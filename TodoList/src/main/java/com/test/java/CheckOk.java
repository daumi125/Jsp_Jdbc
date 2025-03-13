package com.test.java;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.java.model.TodoDAO;
import com.test.java.model.TodoDTO;

@WebServlet("/checkok.do")
public class CheckOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//CheckOk.java
		//1. 데이터 가져오기(seq, state)
		//2. DB 작업 > update
		//3. JSP 호출
		
		//1.
		String seq = req.getParameter("seq");
		String state = req.getParameter("state");
		
		//2.
		TodoDAO dao = new TodoDAO();
		TodoDTO dto = new TodoDTO(); //Data Transfer Object > 인스턴스 > 수정O
		//TodoVO vo = new TodoVO();  //Valud Object > 리터럴 > 수정X, 읽기 전용O
		dto.setSeq(seq);
		dto.setState(state);
		
		int result = dao.check(dto); //1, 0
		
		//3.
		req.setAttribute("result", result);
		req.getRequestDispatcher("/WEB-INF/views/checkok.jsp").forward(req, resp);
	}

}













