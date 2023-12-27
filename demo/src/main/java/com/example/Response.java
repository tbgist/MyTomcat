package com.example;

import java.io.OutputStream;

public class Response {
    public OutputStream outputStream;

    public static final String responseBody = "HTTP/1.1 200"+"\r\n"+"Content-Type: text/html"+"\r\n"+"\r\n";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
