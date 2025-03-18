package com.test.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.java.model.AjaxDAO;
import com.test.java.model.ZipcodeDTO;

@WebServlet("/ex10data.do")
public class Ex10Data extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex10Data.java
        req.setCharacterEncoding("UTF-8");
        String dong = req.getParameter("dong");
        
        AjaxDAO dao = new AjaxDAO();
        ArrayList<ZipcodeDTO> list = dao.listZipCode(dong);
        
        //ArrayList<ZipCodeDTO> > JSONArray(JSONObject)
        
        JSONArray arr = new JSONArray();
        
        for (ZipcodeDTO dto : list) {
            
            JSONObject obj = new JSONObject();
            
            obj.put("seq", dto.getSeq());
            obj.put("zipcode", dto.getZipcode());
            obj.put("sido", dto.getSido());
            obj.put("gugun", dto.getGugun());
            obj.put("dong", dto.getDong());
            obj.put("bunji", dto.getBunji());
            
            arr.add(obj);           
        }
        
        //System.out.println(arr);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = resp.getWriter();
        writer.print(arr);
        writer.close();
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //{ "name": "hong" }
        
        JSONArray arr = new JSONArray();
        
        JSONObject obj = new JSONObject();
        obj.put("name", "홍길동");
        obj.put("age", 20);
        
        JSONObject obj2 = new JSONObject();
        obj2.put("name", "아무개");
        obj2.put("age", 22);
        
        //System.out.println(obj.toJSONString());
        //System.out.println();
        //System.out.println(obj.toString());
        
        arr.add(obj);
        arr.add(obj2);
        
        //[{"name":"홍길동","age":20},{"name":"아무개","age":22}]

        System.out.println(arr.toString());
        
    }

}
























