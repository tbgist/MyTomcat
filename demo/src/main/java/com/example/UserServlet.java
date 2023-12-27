package com.example;

import java.io.IOException;
import java.io.OutputStream;

public class UserServlet extends MyHttpServlet {
    @Override
    public void doGet(Request request, Response response) {
        try {
            OutputStream outputStream = response.outputStream;
            String result = Response.responseBody + "user handle successful";
            outputStream.write(result.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void doPost(Request request, Response response) {
        doGet(request, response);
    }

    public void init() throws Exception {}

    public void destory() {}
}