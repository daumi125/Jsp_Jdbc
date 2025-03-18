<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
	<style>
	   html, body {
	       overflow:hidden;
	   }
	</style>
</head>
<body>
	<!-- ex07data.jsp -->
	<h1>아이디 중복 검사</h1>
	<form method="POST" action="/ajax/ex07data.do">
	   <table class="vertical">
	       <tr>
	           <th style="width: 100px">아이디</th>
	           <td>
	               <input type="text" name="id" id="id" class="short" required value="${id}">
	               <input type="submit" value="중복 검사">
	           </td>
	       </tr>
	   </table>
	</form>
	<c:if test="${not empty result}">
	    <div>
	    <c:if test="${result == 0}">
	        <div style="color:cornflowerblue;">사용 가능한 아이디입니다.</div>
        </c:if>
        <c:if test="${result == 1}">
            <div style="color:tomato;">이미 사용 중인 아이디입니다.</div>
        </c:if>
	    </div>
    </c:if>
    <hr style="margin:.3rem">
    <div>
        <input type="button" value="사용하기" id="btnUse" disabled>
        <input type="button" value="창닫기" id="btnClose">
    
    </div>
    
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    $('#btnClose').click(()=> {window.close();})
    
    $('#btnUse').click(()=> {
        // 자식창 -> 부모창 접근하기
        //window.opener
        opener.document.getElementById('id').value = '${id}';
    })
    
    //궁금증 > 여기서 이거 안만들고도 순서가 이상함.
    
    <c:if test="${result == 0}">
    $('#btnUse').prop('disabled', false);
    </c:if>
    </script>
</body>
</html>