package com.example;

public interface MyServlet {
    void init() throws Exception;
    void service(Request request, Response response);
    void destory();
}
