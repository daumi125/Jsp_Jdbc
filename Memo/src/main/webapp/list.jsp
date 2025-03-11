<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.java.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//1. DB 작업 > 목록 가져오기(select)
//2. 결과셋 > 화면 출력

DBUtil util = new DBUtil();
Connection conn = util.open();
Statement stat = conn.createStatement();

String sql = "select * from tblMemo order by seq desc";
ResultSet rs = stat.executeQuery(sql);


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
    
    <h1>Memo <small>목록 보기</small></h1>
    
    
    <div id="list">
        <% while(rs.next()) { 
            String memo = rs.getString("memo").replace("\r\n", "<br>");
        
        %>
        <div class="item">
            <div><%= memo %></div>
            <div>
                <span><%= rs.getString("name") %></span> / 
                <span><%= rs.getString("regdate") %></span>
            </div>
            <div>
                <button type="button" class="edit" onclick="location.href='/memo/edit.jsp?seq=<%= rs.getString("seq") %>';">수정하기</button>
                <button type="button" class="del" onclick="location.href='/memo/del.jsp?seq=<%= rs.getString("seq") %>';">삭제하기</button>
            </div>
        </div>
        <% } %>
    </div>
    <div>
    
        <button type="button" class="add"
        onclick="location.href='/memo/add.jsp';">쓰기</button>
    </div>
    
    
    <script>

    </script>
</body>
</html>


<%
rs.close();
stat.close();
conn.close();

%>

