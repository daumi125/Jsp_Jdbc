<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<!-- ex07.jsp -->
	<h1>회원 가입</h1>
	<table class="vertical">
	   <tr>
	       <th>이름</th>
	       <td><input type="text"></td>
	   </tr>
	   <tr>
           <th>아이디</th>
           <td>
            <div class="group">
	            <input type="text" id="id" class="short"  readonly>
	            <input type="button" value="중복검사" id="btnCheck">
            </div>
           </td>
       </tr>
       <tr>
           <th>암호</th>
           <td><input type="password"></td>
       </tr>
	</table>
	
	<div>
	   <input type="submit" vlaue="가입하기">
	</div>
    
    
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    $('#btnCheck').click(()=> {
    	//팝업
    	window.open('/ajax/ex07data.do', 'idcheck', 'width=400 height=300');
    })
    </script>
</body>
</html>




