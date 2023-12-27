package com.example;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class MyTomcat {
    public static final int port = 1314;

    public static final HashMap<String, MyHttpServlet> servletMapping = new HashMap<String, MyHttpServlet>();
    public static final HashMap<String, String> urlmapping = new HashMap<String, String>();

    public static void main(String[] args) {
        MyTomcat myTomcat = new MyTomcat();
        myTomcat.init();
        myTomcat.run();
    }

    @SuppressWarnings("unchecked")
    public void init() {
        try {
            // 获取 web.xml 目录地址
            String path = MyTomcat.class.getResource("/").getPath();
            // 读取 web.xml 文件
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(path + "web.xml"));
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for(Element element:elements) {
                if("servlet".equalsIgnoreCase(element.getName())) {
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    System.out.println(servletName.getText() + "==>" + servletClass.getText());
                    // 通过反射实例化
                    servletMapping.put(servletName.getText(),
                    (MyHttpServlet) Class.forName(servletClass.getText().trim()).newInstance());
                } else if ("servlet-mapping".equalsIgnoreCase(element.getName())) {
                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    System.out.println(servletName.getText() + "==>" + urlPattern.getText());
                    urlmapping.put(urlPattern.getText(), servletName.getText());
                } 
            }
        } catch(DocumentException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch(InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        ServerSocket serverSocket= null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("====服务启动====");
            while(!serverSocket.isClosed()){
                Socket socket=serverSocket.accept();
                RequestHandler requestHandler=new RequestHandler(socket);
                new Thread(requestHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}