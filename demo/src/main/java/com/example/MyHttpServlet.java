package com.example;

public abstract class MyHttpServlet implements MyServlet {
    public void service(Request request, Response response) {
        if("get".equalsIgnoreCase(request.getMethod())) {
            this.doGet(request, response);
        } else {
            this.doPost(request, response);
        }
    }
    public abstract void doGet(Request request, Response response);
    public abstract void doPost(Request request, Response response);
}
