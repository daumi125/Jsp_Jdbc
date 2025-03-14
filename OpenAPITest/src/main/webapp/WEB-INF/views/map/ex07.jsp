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
            width: 1170px;
            height: 500px;
        }
    </style>
</head>
<body class="wide">
    <!-- ex07.jsp -->
    <h1>Kakao Map <small>경로 찾기</small></h1>
    
    <div>
        <div id="map"></div>
    </div>  
    
    <hr>
    
    <div>
        <input type="button" value="위치 초기화" id="btnReset">
        <input type="button" value="경로 찾기" id="btnSearch">
        <input type="button" value="다시 찾기" onclick="location.href='/api/map/ex07.do';">         
    </div>
    
    <form method="GET" action="/api/map/ex07.do">
        <input type="hidden" name="startLat">
        <input type="hidden" name="startLng">
        <input type="hidden" name="endLat">
        <input type="hidden" name="endLng">
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
        
        
        let startMarker = null; //출발지
        let endMarker = null;   //종착지
        
        kakao.maps.event.addListener(map, 'click', (evt)=>{
            
            //if (startMarker != null) {
            //  startMarker.setMap(null);
            //}
            if (startMarker == null) {
                const path = '/api/marker/navigator.png';
                const size = new kakao.maps.Size(64, 64);
                const op = { offset: new kakao.maps.Point(32, 64) }
                const img = new kakao.maps.MarkerImage(path, size, op);
                
                startMarker = new kakao.maps.Marker({
                    position: evt.latLng,
                    image: img
                });
                
                startMarker.setMap(map);
                
                //위치 > 히든 태그
                $('input[name=startLat]').val(evt.latLng.getLat());
                $('input[name=startLng]').val(evt.latLng.getLng());
                
            } else if (startMarker != null && endMarker == null) {
                const path = '/api/marker/bus-stop.png';
                const size = new kakao.maps.Size(64, 64);
                const op = { offset: new kakao.maps.Point(32, 64) }
                const img = new kakao.maps.MarkerImage(path, size, op);
                
                endMarker = new kakao.maps.Marker({
                    position: evt.latLng,
                    image: img
                });
                
                endMarker.setMap(map);
                
                //위치 > 히든 태그
                $('input[name=endLat]').val(evt.latLng.getLat());
                $('input[name=endLng]').val(evt.latLng.getLng());
            }
            
        })
        
        $('#btnReset').click(()=>{
            
            if (startMarker != null) startMarker.setMap(null);
            if (endMarker != null) endMarker.setMap(null);
            startMarker = null;
            endMarker = null;
            
            $('input[name=startLat]').val('');
            $('input[name=startLng]').val('');
            $('input[name=endLat]').val('');
            $('input[name=endLng]').val('');
            
        });
        
        $('#btnSearch').click(()=>{
            
            if ($('input[name=startLat]').val() == '') {
                alert('출발지를 지정안했습니다.');
                return;
            }
            
            if ($('input[name=endLat]').val() == '') {
                alert('종착지를 지정안했습니다.');
                return;
            }
            
            $('form').submit();
            
        });
        
        
        
        <c:if test="${not empty routeData}">
        var routeData = ${routeData};
        
        function drawPath(result) {
            var linePath = [];
            var bounds = new kakao.maps.LatLngBounds();

            result.routes[0].sections.forEach(function(section) {
                section.roads.forEach(function(road) {
                    for (var i = 0; i < road.vertexes.length; i += 2) {
                        var point = new kakao.maps.LatLng(road.vertexes[i+1], road.vertexes[i]);
                        linePath.push(point);
                        bounds.extend(point);
                    }
                });
            });

            var polyline = new kakao.maps.Polyline({
                path: linePath,
                strokeWeight: 5,
                strokeColor: 'blue',
                strokeOpacity: .7,
                strokeStyle: 'solid'
            });
            
            /*
                solid: 실선
                dashed: 점선          
                dotted: 점선 (dashed와 비슷하지만 점의 간격이 더 좁습니다)            
                dashdot: 점과 선이 번갈아 나타나는 스타일         
                longdashdot: 점과 긴 선이 번갈아 나타나는 스타일           
                longdash: 긴 선으로만 구성된 점선
            */

            polyline.setMap(map);
            //map.setBounds(bounds);
            
            
            
            
            
            
            
            
            
            //시작/도착 마커 표시
            
            const path = '/api/marker/navigator.png';
            const size = new kakao.maps.Size(64, 64);
            const op = { offset: new kakao.maps.Point(32, 64) }
            const img = new kakao.maps.MarkerImage(path, size, op);
            
            var startMarker = new kakao.maps.Marker({
                position: linePath[0],
                map: map,
                image: img
            });
            
            
            const path2 = '/api/marker/bus-stop.png';
            const size2 = new kakao.maps.Size(64, 64);
            const op2 = { offset: new kakao.maps.Point(32, 64) }
            const img2 = new kakao.maps.MarkerImage(path2, size2, op2);

            // 도착지 마커
            var endMarker = new kakao.maps.Marker({
                position: linePath[linePath.length - 1],
                map: map,
                image: img2
            });
            
            
            
            
            /*
            var infowindow = new kakao.maps.InfoWindow({
                content: '<div>출발지</div>'
            });

            infowindow.open(map, startMarker);
            */

            

            function animatePath() {
                var index = 0;
                var intervalId = setInterval(function() {
                    if (index >= linePath.length) {
                        clearInterval(intervalId);
                        return;
                    }
                    map.panTo(linePath[index]);
                    index++;
                }, 100); // 100ms마다 이동
            }

            animatePath();

            
            
        }

        drawPath(routeData);
        </c:if>
        
        
    </script>
</body>
</html>















