<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.java.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//1. 데이터 가져오기
//1.5 권한 확인
//2. DB작업 > 메모 삭제하기(delete)
//3. 실행 결과 처리

//1.
request.setCharacterEncoding("UTF-8");

String seq = request.getParameter("seq");
String pw = request.getParameter("pw");

//1.5
DBUtil util = new DBUtil();
Connection conn = null;
PreparedStatement stat = null;

conn = util.open();
String sql = "select count(*) as cnt from tblMemo where seq = ? and pw = ?";
stat = conn.prepareStatement(sql);

stat.setString(1, seq);
stat.setString(2, pw);

ResultSet rs = stat.executeQuery();

int cnt = 0;

if(rs.next()){
    cnt = rs.getInt("cnt");// 1 or 0
}

rs.close();
stat.close();

if(cnt == 1){
    //2. 
    sql = "delete from tblMemo where seq = ?";
    stat = conn.prepareStatement(sql);
    
    stat.setString(1, seq);
    
    int result = stat.executeUpdate();
    stat.close();
    conn.close();
    
    if(result == 1){
        response.sendRedirect("/memo/list.jsp");
    }else {
        out.println("<script>alert('failed'); history.back();</script>");
        out.close();
    }
}else {
    out.println("<script>alert('wrong password'); history.back();</script>");
    out.close();
}


%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Memo</title>
    <%@ include file="/inc/asset.jsp" %>
    <style>
    
    </style>
</head>
<body>
    <%@ include file="/inc/header.jsp" %>
    
    <h1>Memo <small>삭제하기</small></h1>
    
    <script>
    
    </script>
</body>
</html>








