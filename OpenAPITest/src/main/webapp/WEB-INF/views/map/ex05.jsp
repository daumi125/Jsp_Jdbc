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
    <!-- ex05.jsp -->
    <h1>Kakao Map <small>마커 추가하기</small></h1>
    
    <div>
        <div id="map"></div>
    </div>
    <form method="POST" action="/api/map/add.do">
        <div>
            <input type="submit" value="마커 추가하기">
        </div>    
    <input type="hidden" name="lat">
    <input type="hidden" name="lng">
    </form>
    
    
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
        
        //마커 추가하기
        let m = null;
        
        kakao.maps.event.addListener(map, 'click', (evt)=> {
        	if(m != null){
        		m.setMap(null);
        	}
        	
        	const path = '/api/marker/pointing.png'
        	const size = new kakao.maps.Size(64,64)
        	const op = { offset: new kakao.maps.Point(0,25)}
        	const img = new kakao.maps.MarkerImage(path, size, op)
        	
        	m = new kakao.maps.Marker({
        		position: evt.latLng,
        		image: img
        	});
        	m.setMap(map);
        	
        	//클릭 좌표 > 히든 태그 대입
        	$('input[name=lat]').val(evt.latLng.getLat());
        	$('input[name=lng]').val(evt.latLng.getLng());
        });
        
    </script>
</body>
</html>






