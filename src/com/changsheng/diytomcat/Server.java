package com.changsheng.diytomcat;

import cn.hutool.core.util.NetUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * @author changshengee
 */
public class Server {
    /**
     * 资源根目录
     */
    public static String WEB_ROOT = System.getProperty("user.dir") + "\\WebRoot";

    public static String url = "";

    public static HashMap<String, String> storage = new HashMap<>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(WEB_ROOT + "\\WEB-INF\\web.properties"));
            Set set = properties.keySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = properties.getProperty(key);
                storage.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        int port = 18080;
        if (!NetUtil.isUsableLocalPort(port)) {
            System.out.println(port + ":address is already used by another application");
            return;
        }
        System.out.println("Tomcat is starting......\n");
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Request request = new Request(in);
            url=request.getUrl();
            System.out.println("Request url is :" + request.getUrl());
            Response response = new Response(out);

            //分排器
            dispatch(request, response);
            in.close();
            out.close();
            socket.close();
        }


    }

    private void dispatch(Request request, Response response) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        int length = 0;
        byte[] bytes = new byte[1024];
        FileInputStream fileInputStream = null;
        StringBuffer stringBuffer = new StringBuffer();
        if (storage.containsKey(url.replace("/", ""))) {
            String value = storage.get(url.replace("/", ""));
            Class clazz = Class.forName(value);
            BaseServlet servlet = (BaseServlet) clazz.newInstance();
            servlet.servlet(request, response);
        } else {
            File file = new File(WEB_ROOT, request.getUrl());
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                while ((length = fileInputStream.read(bytes)) != -1) {
                    stringBuffer.append(new String(bytes, 0, length));
                }
            } else {
                file = new File(WEB_ROOT, "404.html");
                fileInputStream = new FileInputStream(file);
                while ((length = fileInputStream.read(bytes)) != -1) {
                    stringBuffer.append(new String(bytes, 0, 404));
                }

            }
            response.write(stringBuffer.toString(), 200);
        }
    }
}
