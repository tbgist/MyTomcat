package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Request {
    private String uri;
    private String method;

    public Request(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String[] data = reader.readLine().split(" ");
            this.uri = data[1];
            this.method = data[0];
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return this.method;
    }

    public String getUri() {
        return this.uri;
    }
}
