<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.test.java.data.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//1. 데이터 가져오기(seq) > edit.jsp?seq=10
//2. DB 작업 > 수정할 메모를 가져오기(select...where)
//3. 화면 출력

//1.
String seq = request.getParameter("seq");

//2.
DBUtil util = new DBUtil();
Connection conn = util.open();

String sql = "select * from tblMemo where seq = ?";

PreparedStatement stat = conn.prepareStatement(sql);
stat.setString(1, seq);

ResultSet rs = stat.executeQuery();
String name = "";
String memo = "";

if(rs.next()){
    name = rs.getString("name");
    memo = rs.getString("memo");
}

rs.close();
stat.close();
conn.close();

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
    
    <h1>Memo <small>수정하기</small></h1>
    
    <form method="POST" action="/memo/editok.jsp">
        <table>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" value=<%= name %> required></td>
            </tr>   
            <tr>
                <th>암호</th>
                <td><input type="password" name="pw" required></td>
            </tr>   
            <tr>
                <th>메모</th>
                <td>
                    <textarea name="memo" class="full" required><%= memo %></textarea>
                </td>
            </tr>   
        </table>
        
        <div>
        <button type="submit" class="edit">수정하기</button>
        <button type="button" class="back" onclick="location.href='/memo/list.jsp';">돌아가기</button>
    </div>
    
    <input type="hidden" name="seq" value="<%= seq %>">

    </form>
    
    
    
    <script>

    </script>
</body>
</html>