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
    <!-- ex01.jsp -->
    <h1>Kakao Map</h1>
    
    <div>
        <div id="map"></div>
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
        
        
        
    </script>
</body>
</html>






