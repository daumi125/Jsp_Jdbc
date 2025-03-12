package com.test.java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class EX04 {
    public static void main(String[] args) {
        //크롤링
//        m1();
//        m2();
        m3();
    }
    
    private static void m3() {
        // 동적 콘텐츠 크롤링 + SSR(***) > list3
        // > Jsoup 라는 도구는 정적 HTML Parser입니다.
        // - Jsoup은 HTML을 분석하고 탐색하는 기능이 있다.
        // - Jsoup은 JS를 실행할 수 없다.
        
        try {
            Document doc =  Jsoup.connect("http://localhost:8080/crawling/ex04.jsp").get();
            
            Elements list = doc.select("#list3 > li");
            
            for(Element li : list) {
                System.out.println(li.firstElementChild().text());
                System.out.println(li.lastElementChild().text());
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void m2() {
        // 동적 콘텐츠 크롤링 + CSR(***) > list2
        // > Jsoup 라는 도구는 정적 HTML Parser입니다.
        // - Jsoup은 HTML을 분석하고 탐색하는 기능이 있다.
        // - Jsoup은 JS를 실행할 수 없다.
        
        try {
            Document doc =  Jsoup.connect("http://localhost:8080/crawling/ex04.jsp").get();
            
            Elements list = doc.select("#list2 > li");
            
            for(Element li : list) {
                System.out.println(li.firstElementChild().text());
                System.out.println(li.lastElementChild().text());
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void m1() {
        // 정적 콘텐츠 크롤링 > list1
        
        try {
            Document doc =  Jsoup.connect("http://localhost:8080/crawling/ex04.jsp").get();
            
            Elements list = doc.select("#list1 > li");
            
            for(Element li : list) {
                System.out.println(li.firstElementChild().text());
                System.out.println(li.lastElementChild().text());
            }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
