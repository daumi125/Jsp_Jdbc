package com.test.java.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/map/ex07.do")
public class Ex07 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex07.java
        
        //처음? 검색?
        String startLat = req.getParameter("startLat");
        String startLng = req.getParameter("startLng");
        String endLat = req.getParameter("endLat");
        String endLng = req.getParameter("endLng");
                
        if (startLat != null && startLng != null && endLat != null && endLng != null) {
            //System.out.println("검색!!");
            
            //37.1726896,127.82379
            String start = startLng + "," + startLat;
            String end = endLng + "," + endLat;
            String key = "086f1d2b3cf21c10a49bd893f1ac2503";
            
            //DISTANCE: 최단 거리 > 도보 or 자전거
            //TIME: 최단 시간 > 차량 or 대중교통
            //RECOMMEND: 최단 거리 + 최단 시간
            String url = "https://apis-navi.kakaomobility.com/v1/directions?origin=" + start + "&destination=" + end + "&priority=TIME";
            
            URL url2 = new URL(url);    
            HttpURLConnection conn = (HttpURLConnection)url2.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "KakaoAK " + key);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder sb = new StringBuilder();
            
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            
            reader.close();
            conn.disconnect();
            
            //System.out.println(sb.toString());
            req.setAttribute("routeData", sb.toString());
            
        }

        req.getRequestDispatcher("/WEB-INF/views/map/ex07.jsp").forward(req, resp);
    }

}





















