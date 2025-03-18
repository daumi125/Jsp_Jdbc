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
	<!-- ex04 -->
	<h1>Ajax</h1>
	
	<!-- 버튼 클릭 > 서버의 데이터를 가져와서 화면에 출력하기 -->
	<div>
	   <input type="button" value="설문 제목 가져오기" id="btn1">
	   <hr>
	   <pre class="message">&nbsp;</pre>
	</div>
	<hr>
	<div>
	   다른 콘텐츠 : <input type="text">
	</div>
    
    
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
    $('#btn1').click(()=>{
    	//비동기 자바스크립트 통신
    	//1. 순수 자바스크립트 > XMLHttpRequest 구현
    	//2. 각종 라이브러리
    	// 2.1 jQuery
    	// 2.2 axio 요즘 라이브러리
    	//3. 순수 자바스크립트 > fetch API(XMLHttpRequest 다음 세대)
    	
    	//ajax 객체 역할 > 눈에 안보이는 브라우저
    	//               > 눈에 안보이는 전화기(무전기) ***
    	//               > 눈에 안보이는 통신 도구(서버와 통신)
    	//               > 눈에 안보이는 == 랜더링 안함
    	// 브라우저(x) > ajax 객체는 HTML, CSS, JavaScript
    	const ajax = new XMLHttpRequest(); //0 개통전
    	
    	//ReadyState > 변경될 때 발생
    	//-0:초기값
    	ajax.onreadystatechange = function(){
    		//$('.message').append('<div>이벤트 발생 ' + ajax.readyState + '</div>')
    		
    		if(ajax.readyState == 4 && ajax.status == 200){
    			$('.message').append('<div>데이터 수신 완료</div>');
    			$('.message').append('<div>' + ajax.responseText + '</div>'); //페이지가 자식으로 다 들어감
    			//console.log(ajax.responseText);
    			
    		}else if(ajax.readyState == 4 && ajax.status != 200) {
    			$('.message').append('<div>서버에 오류가 발생했습니다.</div>');
    		}
    	}
    	
    	//ajax 객체가 통신을 하게 될 서버측 정보 설정
    	//<form method="GET" action="/ajax/ex01.do">
    	ajax.open('GET', '/ajax/ex04data.do'); //연결(x) > 설정임 : submit버튼 클릭 전 //1: 셋팅 > 돌려받는 값 : 소스
    	
    	ajax.send(); //submit 버튼 클릭 역할 > ajax 객체가 위에서 설정한 서버에게 요청 발생 > 통신 요청 발생!! //2. 통화버튼(전송)
    	
    	//3. 데이터 전달 순간(중)
    	
    	//4. 데이터 다 받은 순간(수신 직후)
    	
    })
    </script>
</body>
</html>