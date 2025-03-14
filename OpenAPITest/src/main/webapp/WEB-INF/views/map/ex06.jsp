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
    <h1>Kakao Map<small>주소를 통해서 좌표를 얻기</small></h1>
    
    <div>
        <div id="map"></div>
    </div>
    <hr>
    <div>
        <input type="text" id="address" class="full" placeholder="주소를 입력하세요.">
    </div>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=909d6d857bd3d609a81d02972cddd225&libraries=services"></script>
    <script>
    
        const container = document.getElementById('map');
        
        const options = {
            center: new kakao.maps.LatLng(37.499314, 127.033192),
            level: 3
        };
    
        const map = new kakao.maps.Map(container, options);
        
        $('#address').keydown((evt) => {
        	if(evt.keyCode == 13){
        		//alert();
        		search($(event.target).val());
        	}
        })
        
        let m = null;
        
        function search(address){
        	//alert(address);
        	const geocoder = new kakao.maps.services.Geocoder();
        	
        	
        	geocoder.addressSearch(address, function(result, status){
        		//결과값 반환 > 호출
        		//alert(result[0].y + ',' + result[0].x);
        		
        		if(status == kakao.maps.services.Status.OK) {
        			if(m != null) m.setMap(null);
        			const p = new kakao.maps.LatLng(result[0].y, result[0].x);
        			m = new kakao.maps.Marker({
        				position: p
        			});
        			
        			m.setMap(map);
        			map.panTo(p)
        			
        		}else {
        			alert('잘못된 주소를 입력했습니다.');
        			$('#address').select();
        		}
        		
        	});
        }
        
        kakao.maps.event.addListener(map, 'click', (evt)=>{
        	if(m!=null) m.setMap(null);
            m = new kakao.maps.Marker({
                position: evt.latLng
            });
            m.setMap(map);
            
            //해당 좌표의 주소지
            const geocoder = new kakao.maps.services.Geocoder();
            geocoder.coord2Address(evt.latLng.getLng(), evt.latLng.getLat(), function(result, status){
	            if(status = kakao.maps.services.Status.OK){
	            	$('#address').val(result[0].address.address_name);
	            	console.log(result)
	            }else{
	            	alert('해당 위치의 주소를 찾지 못했습니다.')
	            }
            })
        })
        
        
    </script>
</body>
</html>






