package com.test.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.util.DBUtil;

//서블릿(X)
//서블릿 > 업무 위임 > 일반 클래스(O)

//Data Access Object > 데이터 작업 전문가 or 담당자 > JDBC
public class TodoDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	private DBUtil util;
	
	public TodoDAO() {
		
		try {
			
			this.util = new DBUtil();
			this.conn = util.open();
			this.stat = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//할일 목록을 달라고 요청
	public ArrayList<TodoDTO> list() {
		
		try {
			
			String sql = "select * from tblTodo order by state asc, seq desc";
			
			rs = stat.executeQuery(sql);
			
			//ResultSet > (변환) > 누구나 다 알 수 있는 자료형?
			//- TodoDTO: 레코드 1줄
			//- ArrayList<TodoDTO>: 레코드 N줄 == 테이블 == ResultSet
			
			//rs > (복사) > list
			ArrayList<TodoDTO> list = new ArrayList<>();
			
			while (rs.next()) {
				
				//레코드 1줄 > TodoDTO 1개
				TodoDTO dto = new TodoDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setTodo(rs.getString("todo"));
				dto.setState(rs.getString("state"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
				
			}
			
			rs.close();
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	//AddOk 서블릿이 할일(todo)을 줄테니 insert 해주세요.
	public int add(String todo) {
		
		try {
			
			String sql = "insert into tblTodo (seq, todo, state, regdate) values (seqTodo.nextVal, ?, default, default)";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, todo);
			
			return pstat.executeUpdate(); //1, 0
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	//CheckOk가 seq+state 줄테니 update 해주세요.
	public int check(TodoDTO dto) {

		try {
			
			String sql = "update tblTodo set state = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);			
			pstat.setString(1, dto.getState());
			pstat.setString(2, dto.getSeq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public int del(String seq) {
		
		try {
			
			String sql = "delete from tblTodo where seq = ?";
			
			pstat = conn.prepareStatement(sql);			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
}












