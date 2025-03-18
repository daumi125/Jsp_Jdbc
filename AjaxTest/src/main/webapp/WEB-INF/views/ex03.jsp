<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
    <style>
        #item1 { background: tomato; }
        #item2 { background: gold; }
        #item3 { background: cornflowerblue; }
        #item4 { background: yellowgreen; }
        
        .item {
            margin: 10px;
            padding: 5px;
            text-align: right;
            padding-right: 15px;
            color: #FFF;
            width: 150px;
        }
        
        .item > span {
            font-size: 12px;
            background: #FFF;
            border-radius: 50%;
            padding: 5px;
            margin-left: 5px;
        }
        
    </style>
</head>
<body>
    <!-- ex03.jsp -->
    <h1>설문 조사</h1>
    
    <table class="content">
        <tr>
            <th></th>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td></td>
        </tr>
        <tr>
            <td></td>
        </tr>
    </table>
    <hr>
    <div>
        <div class="item" id="item1"></div>
        <div class="item" id="item2"></div>
        <div class="item" id="item3"></div>
        <div class="item" id="item4"></div>
    </div>
    <iframe src="/ajax/ex03data.do" style="display: none;"></iframe>
    <hr>
    <h2>추가 콘텐츠</h2>
    <div>
        <div><input type="text"></div>
        <div><input type="text"></div>
        <div><input type="text"></div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        /* 
        $('#item1').css('width', '${dto.cnt1 * 30 + 50}px');
        $('#item2').css('width', '${dto.cnt2 * 30 + 50}px');
        $('#item3').css('width', '${dto.cnt3 * 30 + 50}px');
        $('#item4').css('width', '${dto.cnt4 * 30 + 50}px'); 
        */
        
        /* setTimeout(() => {
            location.reload(); //F5
        }, 5000); */
    
    </script>
</body>
</html>
















