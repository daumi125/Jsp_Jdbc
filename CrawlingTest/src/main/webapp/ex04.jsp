<%@page import="com.test.java.Item"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%


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
	<!-- 정적 콘텐츠 -->
    <h1>주소록</h1>
    
    <h2>정적 콘텐츠</h2>
    <ul id="list1">
        <li>
            <span>홍길동</span>
            <span>(서울시)</span>
        </li>
        <li>
            <span>아무개</span>
            <span>(인천시)</span>
        </li>
        <li>
            <span>강아지</span>
            <span>(부산시)</span>
        </li>
        <li>
            <span>고양이</span>
            <span>(광주시)</span>
        </li>
        <li>
            <span>송아지</span>
            <span>(제주시)</span>
        </li>
    </ul>
    
    <hr>
    
    <!-- 동적 콘텐츠 : Js코드 -->
    <h2>동적 콘텐츠(CSR, Client Side Render)</h2>
    <ul id="list2">
    </ul>
    <hr>
    
    <!-- 자바 코드 -->
    <h2>동적 콘텐츠(SSR, Server Side Render)</h2>
    <ul id="list3">
    <%for (Item item : Item.generate()){ %>
        <li>
            <span><%= item.getName() %></span>
            <span>(<%= item.getAddress() %>)</span>
        </li>
    <%} %>
    </ul>
    
    <hr>
    
    <h2 id="h2">셀레니움</h2>
    <div>
        <div><input type="text" id="txt1"></div>
        <div><button type="button" id="btn1">버튼1</button></div>
    </div>
    
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    const list = [
    	{
    		name: "사자",
    		address: "부천시"
    	},
    	{
            name: "호랑이",
            address: "강릉시"
        },
        {
            name: "타조",
            address: "성남시"
        },
        {
            name: "뱀",
            address: "안산"
        },
        {
            name: "이구아나",
            address: "대구"
        }	
    ];
    
    $(list).each((index, item) => {
    	$('#list2').append(`
    		<li>
    			 <span>\${item.name}</span>
    			 <span>(\${item.address})</span>
    	    </li>
    	`);
    });
    
    $('#btn1').click(()=>{
    	$('#h2').text('셀레니움: ' + new Date().toLocaleTimeString());
    });
    
    </script>
</body>
</html>