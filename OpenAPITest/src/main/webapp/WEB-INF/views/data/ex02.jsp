<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
    <style>
        table td:nth-child(1) { width: 120px; text-align: center; }
        table td:nth-child(1) img { width: 110px; }
        table td:nth-child(2) { width: auto; }
        table td:nth-child(2) div { margin: 5px; }  
    </style>
</head>
<body>
    <!-- ex02.jsp -->
    <h1 class="main">Naver Book <small>Search</small></h1>
    
    <form action="/api/ex02.do" method="GET">
    <div class="message">
        <div class="group">
            <label>검색어</label>
            <input type="text" name="word" class="long" required value="${word}">
            <input type="submit" value="검색하기">
            <input type="button" value="초기화" onclick="location.href='/api/ex02.do';">
        </div>
    </div>
    <input type="hidden" name="page" value="${page}">
    </form>
    
    <c:if test="${empty word}">
    <div>검색 중이 아닙니다.</div>
    </c:if>
    
    <c:if test="${not empty word}">
    <div>'${word}'(으)로 검색 중입니다.</div>
    </c:if>
    
    <table>
        <c:forEach items="${list}" var="dto">
        <tr>
            <td><img src="${dto.image}"></td>
            <td>
                <div>${dto.title}</div>
                <div>${dto.author}</div>
                <div>${dto.discount}원</div>
                <div>${dto.publisher}</div>
            </td>
        </tr>
        </c:forEach>
    </table>    
    
    <c:if test="${not empty word}">
    <div>
        <input type="button" value="이전 책" onclick="move(-1);">
        <span>${(page + 4) / 5} 페이지</span>
        <input type="button" value="다음 책" onclick="move(1);">
    </div>
    </c:if>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        function move(n) {
            
            if (n > 0) {
                //다음 책
                
                //581 > 581
                //581 > 582
                //581 > 583
                //581 > 584
                //581 > 585
                
                //586 > 586
                //586 > 587
                //586 > 588
                //586 > 589
                //586 > 590
                
                //589 - (589 % 586) + 1
                
                if (parseInt($('input[name=page]').val()) == ${total - (total mod page) + 1}) {
                    alert('마지막 페이지입니다.');
                    return;
                }
                
                $('input[name=page]').val(parseInt($('input[name=page]').val()) + 5);
            } else {
                //이전 책
                
                if (parseInt($('input[name=page]').val()) == 1) {
                    alert('첫번째 페이지입니다.');
                    return;
                }
                
                $('input[name=page]').val(parseInt($('input[name=page]').val()) - 5);
            }
            
            $('form').submit();
            
        }
    
    </script>
</body>
</html>






