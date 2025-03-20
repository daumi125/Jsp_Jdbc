<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
    <style>
        html, body {
            overflow: hidden;
        }
    
        #place {
            width: 128px;
            height: 128px;
            border: 1px solid black;
            background: gold;
            margin-top: 10px;
            border-radius: 3px;
            padding: 5px;
            font-size: 0;
        }
    </style>
</head>
<body>
    <!-- ex11.jsp -->
    <h1>고양이 관리</h1>
    
    <div>
        <input type="button" value="고양이 추가하기" id="btnAdd">
        <div id="place">
            <c:forEach items="${list}" var="dto">
            <img src="/ajax/images/catty${dto.src}.png" id="cat${dto.catid}" style="position: relative; left: ${dto.x}px; top: ${dto.y}px;">
            </c:forEach>
        </div>
    </div>
    
    <!-- 동적으로 추가할 고양이 담는 컨테이너 -->
    <div id="list"></div>
        
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        let n = ${n}; //고양이 ID
        let num = null; //고양이 파일번호
        let flag = false; // 상자안에 꺼내지 않은 고양이 유무
    
        $('#btnAdd').click(()=>{
        	
        	if(flag) return;
            
            num = getNum();
            
            /*
            $('#list').append('<img src="/ajax/images/catty01.png">').click();
            
            $('<img src="/ajax/images/catty01.png">')
                .click(()=>{
                    alert();
                })
                .appendTo('#list');
            */
            
            $('<img src="/ajax/images/catty' + num + '.png" id="cat' + n + '">')
                .draggable({
                    stop: function(event, ui) {
                        //alert();
                        //alert(ui.position.left);
                        //alert(ui.position.top);
                        
                        //ajax > 좌표 서버 전송 > update
                        //- catid + x + y
                        //alert(this.id);  > cat10 > 10
                        
                        $.ajax({
                            type: 'POST',
                            url: '/ajax/ex11data.do',
                            data: 'mode=edit&n=' 
                                    + this.id.replace('cat', '') 
                                    + '&x=' + ui.position.left 
                                    + '&y=' + ui.position.top,
                            dataType: 'json',
                            success: function(result) {
                                
                                if (result.result == "0") {
                                    alert('고양이 정보를 서버에 반영하지 못했습니다.');
                                }
                                
                            },
                            error: function(a,b,c) {
                                console.log(a,b,c);
                            }
                        });
                        flag = false;
                    }//stop
                })
                .css('top', ((n-1) * -128) + 'px')
                .appendTo('#place');
            
            
            //서버에게 방금 추가한 고양이 정보 전달
            $.ajax({
                type: 'POST',
                url: '/ajax/ex11data.do',
                data: 'mode=add&num=' + num + '&n=' + n,
                dataType: 'json',
                success: function(result) {
                    
                    if (result.result == "0") {
                        alert('고양이 정보를 서버에 반영하지 못했습니다.');
                    }
                            
                },
                error: function(a,b,c) {
                    console.log(a,b,c);
                }
            });
            
            n++;
            flag = true; //상자안에 고양이 생김
            
        });//btnAdd.click
        
        function getNum() {
            //catty01.png ~ catty21.png
            //1~21 > 0~20 + 1
            return String(parseInt(Math.random() * 21) + 1).padStart(2, '0'); 
        }
        
        
        $('#place img').draggable({
            stop: function(event, ui) {
                $.ajax({
                    type: 'POST',
                    url: '/ajax/ex11data.do',
                    data: 'mode=edit&n=' 
                            + this.id.replace('cat', '') 
                            + '&x=' + ui.position.left 
                            + '&y=' + ui.position.top,
                    dataType: 'json',
                    success: function(result) {
                        
                        if (result.result == "0") {
                            alert('고양이 정보를 서버에 반영하지 못했습니다.');
                        }
                        
                    },
                    error: function(a,b,c) {
                        console.log(a,b,c);
                    }
                });
            }
        });
    
    </script>
</body>
</html>















