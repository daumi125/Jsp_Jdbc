package com.test.java.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BookDAO {
	
	public ArrayList<BookDTO> search(String word, String page) {
		
		String clientId = "gWeULGdqcd6T1jSOfOrR";
        String clientSecret = "dzW0pOHVs7";

        String text = null;
        
        try {
            text = URLEncoder.encode(word, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text + "&display=5&start=" + page; 
        //String apiURL = "https://openapi.naver.com/v1/search/book.xml?query="+ text;


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        
        JSONParser parser = new JSONParser();
        
        try {
			
        	JSONObject root = (JSONObject)parser.parse(responseBody);
        	
        	System.out.println(root.get("total"));
        	
        	JSONArray list = (JSONArray)root.get("items");
        	ArrayList<BookDTO> blist = new ArrayList<BookDTO>();
        	
        	//JSONArray > 변환 > ArrayList<BookDTO>
        	for (Object book : list) {
        		
        		//((JSONObject)book).get("key").toString() > BookDTO
        		BookDTO dto = new BookDTO();
        		
        		dto.setTitle(((JSONObject)book).get("title").toString());
        		dto.setLink(((JSONObject)book).get("link").toString());
        		dto.setImage(((JSONObject)book).get("image").toString());
        		dto.setAuthor(((JSONObject)book).get("author").toString());
        		dto.setDiscount(((JSONObject)book).get("discount").toString());
        		dto.setPublisher(((JSONObject)book).get("publisher").toString());
        		dto.setPubdate(((JSONObject)book).get("pubdate").toString());
        		dto.setIsbn(((JSONObject)book).get("isbn").toString());
        		dto.setDescription(((JSONObject)book).get("description").toString());
        		
        		blist.add(dto);
        	}
        	
        	return blist;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return null;		
        
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders){
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


    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private String readBody(InputStream body){
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

	public int getTotal(String word) {
		
		String clientId = "Dq9kLO_8VwS8mKwWMOC8";
        String clientSecret = "QximffsdfK";

        String text = null;
        
        try {
            text = URLEncoder.encode(word, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/book.json?query=" + text; 
        //String apiURL = "https://openapi.naver.com/v1/search/book.xml?query="+ text;


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);
        
        JSONParser parser = new JSONParser();
        
        try {
			
        	JSONObject root = (JSONObject)parser.parse(responseBody);
        	
        	return Integer.parseInt(root.get("total").toString());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}











