package com.lvjc;



import javax.websocket.server.ServerContainer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by lvjc on 2017/6/28.
 */
public class JettyServer {

 /*   public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        WebAppContext webAppContext = new WebAppContext("webapp","/accountBook");

        webAppContext.setDescriptor("./webapp/WEB-INF/web.xml");
        webAppContext.setResourceBase("./webapp");
        webAppContext.setDisplayName("account-book");
        webAppContext.setClassLoader(Thread.currentThread().getContextClassLoader());
        webAppContext.setConfigurationDiscovered(true);
        webAppContext.setParentLoaderPriority(true);


        server.setHandler(webAppContext);


        try {
            //ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webAppContext);
            // Add WebSocket endpoint to javax.websocket layer
            //wscontainer.addEndpoint(MyWebSocket.class);   //这行是如果需要使用websocket就加上，不需要就注释掉这行，mywebsocket是自己写的websocket服务类

            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("server is  start, port is "+port+"............");
        System.out.println(webAppContext.getContextPath());
        System.out.println(webAppContext.getDescriptor());
        System.out.println(webAppContext.getResourceBase());
        System.out.println(webAppContext.getBaseResource());
    }*/
}
