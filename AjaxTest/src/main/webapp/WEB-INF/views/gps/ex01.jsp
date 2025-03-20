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
    <h1>현재 위치</h1>
    
    <div>
        <div id="mapLink"></div>
        <div id="locationInfo"></div>
    </div>  
    <hr>
    <div>
        <input type="button" value="가져오기" onclick="getLocation();">
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
        
        
        
        // 위치 정보 가져오기
        function getLocation() {
            if (navigator.geolocation) {
                // 고정밀 위치 정보를 위한 옵션 설정
                const options = {
                    enableHighAccuracy: true, // 고정밀 모드 활성화
                    timeout: 5000, // 5초 타임아웃
                    maximumAge: 0 // 항상 최신 위치 정보 사용
                };

                navigator.geolocation.getCurrentPosition(showPosition, handleError, options);
            } else {
                alert("이 브라우저에서는 위치 정보를 지원하지 않습니다.");
            }
        }

        // 위치 정보 표시 함수
        function showPosition(position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;

            console.log("위도: " + latitude);
            console.log("경도: " + longitude);

            document.getElementById("locationInfo").innerHTML = `위도: \${latitude}, 경도: \${longitude}`;

            // 지도에서 위치 보기 링크 생성
            const mapLink = document.getElementById("mapLink");
            mapLink.href = `https://www.openstreetmap.org?mlat=\${latitude}&mlon=\${longitude}`;
            mapLink.textContent = "지도에서 내 위치 보기";
        }

        // 오류 처리 함수
        function handleError(error) {
            console.error("위치 정보 가져오기 오류: " + error.message);
            alert("위치 정보 가져오기 오류: " + error.message);
        }
        
    </script>
</body>
</html>






