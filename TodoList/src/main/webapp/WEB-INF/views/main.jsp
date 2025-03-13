<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
    <style>
        #tbl1 td:nth-child(1) { width: 30px; text-align: center; vertical-align: middle; font-size: 0; }
        #tbl1 td:nth-child(2) { cursor: pointer; }
        #tbl1 td:nth-child(2) > span { font-size: 12px; color: #999; }
        
        #tbl2 td:nth-child(1) { width: 363px; }
        #tbl2 td:nth-child(2) {}
        
        .checked { text-decoration: line-through; }
        .checked > span { text-decoration: none; }
        
    </style>
</head>
<body class="narrow">
    <!-- main.jsp -->
    <h1 class="main">Todo List</h1>
    
    <table id="tbl1">
        <c:forEach items="${list}" var="item">
        <%-- 
        <tr>
            <td><input type="checkbox" data-seq="${item.seq}" <c:if test="${item.state == 'y'}">checked</c:if>></td>
            <td>${item.todo} <span>${item.regdate}</span></td>
        </tr> 
        --%>
        
        <c:if test="${item.state == 'n'}">
        <tr>
            <td><input type="checkbox" data-seq="${item.seq}"></td>
            <td>${item.todo} <span>${item.regdate}</span></td>
        </tr>
        </c:if>
        
        <c:if test="${item.state == 'y'}">
        <tr>
            <td><input type="checkbox" data-seq="${item.seq}" checked></td>
            <td class="checked">${item.todo} <span>${item.regdate}</span></td>
        </tr>   
        </c:if> 
        
        </c:forEach>
    </table>
    
    <hr>
    
    <form method="POST" action="/todo/addok.do">
    <table id="tbl2">
        <tr>
            <td><input type="text" name="todo" required class="long"></td>
            <td><input type="submit" value="등록하기"></td>
        </tr>
    </table>
    </form>
    
    
    <form id="form2" method="POST" action="/todo/checkok.do">
        <input type="hidden" name="seq">
        <input type="hidden" name="state">
    </form>
    
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        //완료 체크
        $('#tbl1 input[type=checkbox]').change(()=>{
            //alert(event.target.checked);
            //alert(event.target.dataset['seq']);
            
            //주로 사용하는 방법
            location.href = '/todo/checkok.do?seq=' + event.target.dataset['seq'] + '&state=' + (event.target.checked ? 'y' : 'n');
            
            
            //언제? POST로 전송해야 할때
            //$('#form2 input[name=seq]').val(event.target.dataset['seq']);
            //$('#form2 input[name=state]').val(event.target.checked ? 'y' : 'n');
            //$('#form2').submit();
            
        });
        
        $('#tbl1 tr').click(()=>{
            
            //alert(event.ctrlKey);
            
            //alert(event.target.nodeName);
            //alert(event.currentTarget.nodeName);
            
            const cb = event.currentTarget.firstElementChild.firstElementChild;
            
            if (!event.ctrlKey) {               
                            
                location.href = '/todo/checkok.do?seq=' + cb.dataset['seq'] + '&state=' + (cb.checked ? 'n' : 'y');
                
            } else {
                
                location.href = '/todo/delok.do?seq=' + cb.dataset['seq'];
                
            }
            
        });
    
    </script>
</body>
</html>










