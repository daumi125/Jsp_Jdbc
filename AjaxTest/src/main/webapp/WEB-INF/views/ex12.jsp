<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
    <style>
        #list th:nth-child(1) { width: 50px; }
        #list th:nth-child(2) { width: 70px; }
        #list th:nth-child(3) { width: 50px; }
        #list th:nth-child(4) { width: 70px; }
        #list th:nth-child(5) { width: 150px; }
        #list th:nth-child(6) { width: auto; }
        #list td { text-align: center; }
        
        h1 {
            display: flex;
            justify-content: space-between;
        }
        h1 > span {
            font-size: 20px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <!-- ex12.jsp -->
    <!--  
        SSR(Server Side Rendering)   vs CSR(Client Side Rendering)
                
        MPA(Multi Pages Application) vs SPA(Single Page Application)
    -->
    <h1>주소록 <span data-sidebar-button="sidebar">⚙️</span></h1>
    
    <table id="list">
        <thead>
            <tr>
                <th>번호</th>
                <th>이름</th>
                <th>나이</th>
                <th>성별</th>
                <th>전화</th>
                <th>주소</th>
            </tr>
        </thead>
        <tbody>
        
        </tbody>
        <!-- 
        <tr>
            <td>1</td>
            <td>홍길동</td>
            <td>20</td>
            <td>남자</td>
            <td>010-1234-5678</td>
            <td>서울시 강남구 역삼동 한독빌딩</td>
        </tr> 
        -->
    </table>
    
    <hr>
    
    <div data-sidebar-window="sidebar" 
         data-sidebar-title="주소록 추가하기"
         data-sidebar-direction="right">
        <form id="form1">
        <div>
            <label>이름: </label>
            <input type="text" name="name" id="name">
        </div>
        <div>
            <label>나이: </label>
            <input type="number" name="age" id="age">
        </div>
        <div>
            <label>성별: </label>
            <select name="gender" id="gender">
                <option value="m">남자</option>
                <option value="f">여자</option>
            </select>
        </div>
        <div>
            <label>전화: </label>
            <input type="text" name="tel" id="tel">
        </div>
        <div>
            <label>주소: </label>
            <input type="text" name="address" id="address">
        </div>
        <div><input type="button" value="추가하기" id="btn1"></div>
        </form> 
    </div>
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        //자바스크립트 데이터(스칼라)
        const num = 100;
        const color = '노랑';
    
        //자바스크립트 데이터(객체 > 자료구조)
        const obj = {
            name: '홍길동',
            age: 20,
            gender: 'm'
        };
        
        //location.href = 'ex100.do?num=' + num;
        //location.href = 'ex100.do?color=' + num;
        //location.href = 'ex100.do?obj=' + obj; //ex100.do?obj=[object%20Object]
        //location.href = 'ex100.do?name=' + obj.name + '&age=' + obj.age + '&gender=' + obj.gender;
        
        /*
        
            ajax 데이터 전송 형식
            1. Query String
                - data: 'name=홍길동&age=20&gender=m'
            
            2. 직렬화(Serialize)
                - 객체를 한줄의 문자열로 바꾸는 작업
                - data: $('form').serialize()
            
            3. 객체 직접 대입
                - ***
            
        */
    
        $('#btn1').click(()=>{
            
            //alert($('#form1').serialize());
            
            $.ajax({
                type: 'POST',
                url: '/ajax/ex12data.do',
                
                //1.
                //data: 'name=' + $('#name').val() + '&age=' + $('#age').val() + '&gender=' + $('#gender').val() + '&address=' + $('#address').val(),
                
                //2.
                //data: $('#form1').serialize(),
                
                //3.
                data: {
                    name: $('#name').val(),
                    age: $('#age').val(),
                    gender: $('#gender').val(),
                    tel: $('#tel').val(),
                    address: $('#address').val()
                },
                
                dataType: 'json',
                success: function(result) {
                    
                    if (result.result == 1) {
                        
                        //alert('추가 성공!!');
                        
                        list(); //목록 갱신
                        
                        $('#name').val('');
                        $('#age').val('');
                        $('#gender').val('m');
                        $('#tel').val('');
                        $('#address').val('');
                        
                    } else {
                        alert('추가 실패;;');
                    }
                    
                },
                error: function(a,b,c) {
                    console.log(a,b,c);
                }
            });
            
        });
            
        function list() {
            
            $.ajax({
                type: 'GET',
                url: '/ajax/ex12data.do',
                dataType: 'json',
                success: function(result) {
                    
                    $('#list tbody').html('');
                    
                    result.forEach(item => {
                        $('#list tbody').append(`
                            <tr>
                                <td>\${item.seq}</td>
                                <td>\${item.name}</td>
                                <td>\${item.age}</td>
                                <td>\${item.gender}</td>
                                <td>\${item.tel}</td>
                                <td>\${item.address}</td>
                            </tr>
                        `);
                    });
                    
                },
                error: function(a,b,c) {
                    console.log(a,b,c);
                }
            });
            
        }
        
        list();
    
    </script>
</body>
</html>























