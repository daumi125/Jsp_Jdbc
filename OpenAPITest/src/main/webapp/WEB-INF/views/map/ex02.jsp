<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
    <style>
        #map {
            width: 770px;
            height: 500px;
        }
    </style>
</head>
<body>
    <!-- ex02.jsp -->
    <h1>Kakao Map <small>좌표 이동 / 레벨 변경</small></h1>
    
    <div>
        <div id="map"></div>
    </div>  
    <hr>
    <div>
        <input type="button" value="한독빌딩으로 이동하기" id="btn1">
        <input type="button" value="상암경기장으로 이동하기" id="btn2">
        <input type="button" value="역삼역으로 이동하기" id="btn3">
    </div>
    <hr>
    <div>
        <input type="button" value="확대하기" id="btn4">
        <input type="button" value="축소하기" id="btn5">
    </div>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=909d6d857bd3d609a81d02972cddd225"></script>
    <script>
    
        const container = document.getElementById('map');
        
        const options = {
            center: new kakao.maps.LatLng(37.499314, 127.033192),
            level: 3
        };
    
        const map = new kakao.maps.Map(container, options);
        
        //map.setDraggable(false);
        //map.setZoomable(false);
        
        $('#btn1').click(()=>{
            const pos = new kakao.maps.LatLng(37.499314, 127.033192);
            //map.setCenter(pos);
            map.panTo(pos);
        });
        
        $('#btn2').click(()=>{
            const pos = new kakao.maps.LatLng(37.568222, 126.897273);
            //map.setCenter(pos);
            map.panTo(pos);
        });
        
        $('#btn3').click(()=>{
            const pos = new kakao.maps.LatLng(37.500113, 127.035396);
            //map.setCenter(pos);
            map.panTo(pos);
        });
        
        $('#btn4').click(()=>{
            //map.setLevel(1);
        	map.setLevel(map.getLevel()-1);
        });
        
        $('#btn5').click(()=>{
            //map.setLevel(14);
        	map.setLevel(map.getLevel()+1);
        });
        
    </script>
</body>
</html>














