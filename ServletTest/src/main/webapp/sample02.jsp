<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- sample02.jsp -->
    <h1>현재 시각</h1>
    <%
        Calendar now = Calendar.getInstance();
    %>
    <div>지금 시각은 
        <span id="time"><%= String.format("%tT", now) %></span>
    입니다.</div>
    
    <% if (now.get(Calendar.SECOND) % 2 == 0) { %>
    <div>안녕하세요. 좋은 아침입니다.</div>
    <% } else { %>
    <div>안녕하세요. 좋은 저녁입니다.</div>
    <% } %>
    
</body>
</html>












