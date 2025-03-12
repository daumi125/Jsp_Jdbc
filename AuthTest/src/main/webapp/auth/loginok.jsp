<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.util.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
//1. 데이터 가져오기(id, pw)
//2. DB 작업 > select..
//3. 결과
//  3.1 맞다 > 인증 티켓 발급(*****)
//  3.2 틀리다. > 경고

//1.
String id = request.getParameter("id");
String pw = request.getParameter("pw");

//2.
DBUtil util = new DBUtil();
Connection conn = util.open();

//String sql = "select count(*) as cnt from tblUser where id = ? and pw = ?";
String sql = "select * from tblUser where id = ? and pw = ?";
PreparedStatement stat = conn.prepareStatement(sql);

stat.setString(1,id);
stat.setString(2,pw);

ResultSet rs = stat.executeQuery();

//3.
boolean isAuth = false;

if (rs.next()) {
    //존재O > 로그인 성공
    isAuth = true;
    
    //인증 티켓 발급 > 로그인!!
    session.setAttribute("auth", id);

    //개인 정보 > 일부 세션 저장
    session.setAttribute("name", rs.getString("name"));
    session.setAttribute("lv", rs.getInt("lv"));
    
    
    
    //시작 페이지
    response.sendRedirect("/auth/index.jsp");
    
} else {
    //존재X > 로그인 실패
    isAuth = false;
    
    out.print("<script>alert('failed');history.back();</script>");
    out.close();
}

rs.close();
stat.close();
conn.close();

%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	<style>
	
	</style>
</head>
<body>
	<!--  -->
    
    
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>

    </script>
</body>
</html>