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
    <!-- ex04.jsp -->
    <h1>Kakao Map <small>주변 정보</small></h1>
    
    <div>
        <div id="map"></div>
    </div>  
    <hr>
    <div class="message">&nbsp;</div>
    <hr>
    <div>
        <input type="button" value="마커 추가하기" onclick="location.href='/api/map/ex05.do'">
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
        
        
        //DB의 좌표 > 자바스크립트 배열
        const mlist = [
            /* {
                1,
                33.0234234,
                127.902382374
            } */
        ];
        
        <c:forEach items="${list}" var="dto">
        mlist.push({
            seq: ${dto.seq},
            lat: ${dto.lat},
            lng: ${dto.lng}
        });
        </c:forEach>
        
        mlist.forEach(item => {
            const m = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(item.lat, item.lng)
            });
            m.setMap(map);
        });
        
        
        
        
        //현재 보이는 지도상(영역)의 나타난 마커의 개수 세기
        let count;
        
        function countMarker() {
            
            count = 0;
            
            mlist.forEach(item => {
                if (containMarker(item)) {
                    count++;
                }                   
            });
            
            $('.message').text(`현재 보이는 마커의 개수는 \${count}개입니다.`);
            
        }
        
        function containMarker(item) {
            
            //현재 지도의 범위?
            const sw = map.getBounds().getSouthWest(); //LatLng
            const ne = map.getBounds().getNorthEast();
            
            if (item.lat >= sw.getLat()
                && item.lat <= ne.getLat()
                && item.lng >= sw.getLng()
                && item.lng <= ne.getLng()) {
                
                return true;
            }
            
            return false;           
        }
        
        countMarker();
        
        
        //지도 이벤트(드래그)
        kakao.maps.event.addListener(map, 'dragend', function(evt) {
            countMarker();
        });
        
      //지도 이벤트(드래그)
        kakao.maps.event.addListener(map, 'zoom_changed', function(evt) {
            countMarker();
        });
        
    </script>
</body>
</html>
















