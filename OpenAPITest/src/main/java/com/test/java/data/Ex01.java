package com.test.java.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Ex01 {

    public static void main(String[] args) {
        
        String clientId = "Dq9kLO_8VwS8mKwWMOC8";
        String clientSecret = "QximffsdfK";


        String text = null;
        
        try {
            text = URLEncoder.encode("자바", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text; 
        //String apiURL = "https://openapi.naver.com/v1/search/book.xml?query="+ text;


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


        //결과 출력(XML, JSON)
        //System.out.println(responseBody);
        
        //HTML Parser 설치 > Jsoup
        //JSON Parser 설치 > json-simple
        
        String body = """
                
                {
                    "name": "홍길동",
                    "age": 20,
                    "address": {
                        "sido": "서울시",
                        "gugun": "강남구"
                    }
                }
                
                """;
        
        String body2 = """
                [
                    {
                        "name": "홍길동",
                        "age": 20,
                        "address": {
                            "sido": "서울시",
                            "gugun": "강남구"
                        }
                    },
                    {
                        "name": "아무개",
                        "age": 25,
                        "address": {
                            "sido": "서울시",
                            "gugun": "강동구"
                        }
                    }
                ]
                """;
        
        JSONParser parser = new JSONParser();
        
        try {
            
            /*
             * JSONObject obj = (JSONObject)parser.parse(body);
             * System.out.println(obj.get("name")); System.out.println(obj.get("age"));
             * System.out.println(obj.get("address"));
             * 
             * //하위 객체 가져오기 JSONObject address = (JSONObject)obj.get("address");
             * System.out.println(address.get("sido"));
             * System.out.println(address.get("gugun"));
             */
            
//            JSONArray list = (JSONArray)parser.parse(body2);
//            System.out.println(list.size());
            
            //JSONObject 보장이 안됨.
            //for(Object obj : list) {
                //System.out.println(((JSONObject)obj).get("name"));
                //System.out.println(((JSONObject)obj).get("age"));
            //}
            
            JSONObject root = (JSONObject)parser.parse(responseBody);
            System.out.println(root.get("total") + "권");
            JSONArray list = (JSONArray)root.get("items");
            System.out.println(list.size());
            
            for(Object obj : list) {
                JSONObject book = (JSONObject)obj;
                
                System.out.println(book.get("title"));
                System.out.println(book.get("discount"));
                System.out.println(book.get("author"));
                System.out.println();
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
    
}
