<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="org.jsoup.Jsoup"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

    String url = "https://www.moviechart.co.kr/rank/boxoffice";
    Document doc = Jsoup.connect(url).get();
    Elements list = doc.select(".listTable table tbody tr");
    
list.sort((a,b) -> {
        
        //a,b == <tr>
        
        //2,150,648명                            
        int n1 = Integer.parseInt(a.select(".cumulative").text().replace(",", "").replace("명", "").trim());
        int n2 = Integer.parseInt(b.select(".cumulative").text().replace(",", "").replace("명", "").trim());
        
        return n1 - n2;
    });
    
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK">
    <style>
    
    </style>
</head>
<body>
    <!--  -->
    <h1>영화 순위</h1>
    
    <table>
        <tr>
            <th>포스터</th>
            <th>순위</th>
            <th>영화</th>
            <th>누적관객수</th>
        </tr>
        <% 
            for (Element tr : list) {
                String title = tr.select(".title > a").text();
                String rank = tr.select(".redAc").text();
                String audience = tr.select(".cumulative").text();
                
                Document subdoc = Jsoup.connect("https://www.moviechart.co.kr" + tr.select(".title > a").attr("href")).get();
                
                String src = subdoc.select(".poster > a > img").attr("src");
                //System.out.println(src);
                
        %>
        <tr>
            <td><img src="https://www.moviechart.co.kr<%= src %>"></td>
            <td><%= title %></td>
            <td><%= rank %></td>
            <td><%= audience %></td>
        </tr>
        <% } %>
    </table>
    
    
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>

    </script>
</body>
</html>