package com.test.java;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Ex05 {
    public static void main(String[] args) {
        //Ex05.java
        
        //CSR or SSR 크롤링
        //- Selenium, HTMLUnit 등..
        
        //Selenium
        //- 크롤링 도구(X)
        //- 자동화 테스트 도구(사람 대신 브라우징을 하는 프로그램)
        
//        m1();
        m2();
        
        
    }

    private static void m2() {
        // 자동화 테스트
        //- 사이트 완성 > 테스트
        String url = "http://lms1.sist.co.kr/worknet/SLogin.asp";
        
        String webDriverID = "webdriver.chrome.driver";
        String path = "C:\\class\\dev\\chromedriver.exe"; //크롬 브라우저
        System.setProperty(webDriverID, path);
        
        WebDriver driver = new ChromeDriver();
        
        driver.get(url);
        //strLoginID
        //strLoginPwd
        //#content div.login-btn > input
        
        driver.findElement(By.id("strLoginID")).sendKeys("정다음");
        driver.findElement(By.id("strLoginPwd")).sendKeys("0152");
        driver.findElement(By.cssSelector("#content div.login-btn > input")).click();
        
        //페이지 전환 > 잠시 쉬었다 가기
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
//        #content div.popbtmbtn_section > div > a:nth-child(9)
        driver.findElement(By.cssSelector("#content div.popbtmbtn_section > div > a:nth-child(9)")).click();
        
      //saveBt
        driver.findElement(By.id("saveBt")).click();
        
        //#modalContent > div > form > div > div > table > tbody > tr:nth-child(1) > td > input[type=text]
        
        driver.findElement(By.cssSelector("#modalContent > div > form > div > div > table > tbody > tr:nth-child(1) > td > input[type=text]")).sendKeys("자동화된 문의사항입니다.");
        
    }

    private static void m1() {
        // 셀레니움 설치
        // 1. 셀레니움 드라이버(*.jar)
        // 2. 브라우저(크롬) 드라이버(*.exe)
        
        //https://googlechromelabs.github.io/chrome-for-testing/#stable
        
        String webDriverID = "webdriver.chrome.driver";
        
        String path = "C:\\class\\dev\\chromedriver.exe"; //크롬 브라우저
        System.setProperty(webDriverID, path);
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        
        WebDriver driver = new ChromeDriver(options);
        String url = "http://localhost:8090/crawling/ex04.jsp";
        driver.get(url);
        
        WebElement h2 = driver.findElement(By.id("h2"));
        
        System.out.println(h2.getText());
        
        WebElement txt1 = driver.findElement(By.id("txt1"));
        txt1.sendKeys("홍길동입니다.");
        
        WebElement btn1 = driver.findElement(By.id("btn1"));
        btn1.click();
        
//        WebElement list2 = driver.findElement(By.id("list2"));
        List<WebElement> list = driver.findElements(By.cssSelector("#list2 > li"));
        
        System.out.println(list.size());
        
        for(WebElement li : list) {
            System.out.println(li.findElement(By.cssSelector("span:first-child")).getText());
            System.out.println(li.findElement(By.cssSelector("span:last-child")).getText());
            System.out.println();
        }
        
        driver.close();
    }
}
