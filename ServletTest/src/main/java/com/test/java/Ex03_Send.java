package com.test.java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex03_Send extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter(); 
        
//        writer.write("<!DOCTYPE html>\r\n"
//                + "<html lang=\"ko\">\r\n"
//                + "<head>\r\n"
//                + "    <meta charset=\"UTF-8\">\r\n"
//                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
//                + "    <link rel=\"stylesheet\" href=\"css/jquery-ui.css\">\r\n"
//                + "    <title>Document</title>\r\n"
//                + "</head>\r\n"
//                + "<body>\r\n"
//                + "    <!--  -->\r\n"
//                + "    <h1>보내기</h1>\r\n"
//                + "    <script>\r\n"
//                + "        \r\n"
//                + "    </script>\r\n"
//                + "</body>\r\n"
//                + "</html>");
        
        //Get으로 보내서
        //receive에서 http://localhost:8090/servlet/ex03_receive.do?name=%EC%95%84%EB%AC%B4%EA%B0%9C&age=23 로 나타남
        
        writer.write("""
                
                <html>
                <head>
                    <meta charset = "UTF-8">
                </head>
                <body>
                    <h1>보내기</h1>
                    <form method="POST" action="/servlet/ex03_receive.do">
                    <table border="1" width="300">
                        <tr>
                            <td>이름</td>
                            <td><input type="text" name="name"></td>
                        </tr>  
                        <tr>
                            <td>나이</td>
                            <td><input type="number" name="age" size="10"></td>
                        </tr> 
                    </table>
                    <div style="margin-top:10px">
                        <button>보내기</button>
                    </div>
                    </form>
                </body>
                </html>
                """);
        
        writer.close();
    }
}
