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

import com.test.java.model.AddressDTO;
import com.test.java.model.AjaxDAO;

@WebServlet("/ex12data.do")
public class Ex12Data extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Ex12Data.java
        req.setCharacterEncoding("UTF-8");
        
        String name     = req.getParameter("name");
        String age      = req.getParameter("age");
        String gender   = req.getParameter("gender");
        String address  = req.getParameter("address");
        String tel      = req.getParameter("tel");
        
        AjaxDAO dao = new AjaxDAO();
        
        AddressDTO dto = new AddressDTO();
        dto.setName(name);
        dto.setAge(age);
        dto.setGender(gender);
        dto.setTel(tel);
        dto.setAddress(address);
        
        int result = dao.addAddress(dto);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = resp.getWriter();
        writer.print("""
                {
                    "result": %d
                }
                """.formatted(result));
        writer.close();
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        AjaxDAO dao = new AjaxDAO();
        
        ArrayList<AddressDTO> list = dao.listAddress();
        
        JSONArray arr = new JSONArray();
        
        for (AddressDTO dto : list) {
            
            JSONObject obj = new JSONObject();
            
            obj.put("seq", dto.getSeq());
            obj.put("name", dto.getName());
            obj.put("age", dto.getAge());
            obj.put("gender", dto.getGender());
            obj.put("tel", dto.getTel());
            obj.put("address", dto.getAddress());
            obj.put("regdate", dto.getRegdate());
            
            arr.add(obj);
        }
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = resp.getWriter();
        writer.print(arr);
        writer.close();
        
    }

}


















