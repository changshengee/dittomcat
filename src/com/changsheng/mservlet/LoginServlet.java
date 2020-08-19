package com.changsheng.mservlet;

import com.changsheng.diytomcat.BaseServlet;
import com.changsheng.diytomcat.Request;
import com.changsheng.diytomcat.Response;

import java.io.IOException;

public class LoginServlet extends BaseServlet {
    /**
     * 处理 GET 请求
     *
     * @param request  请求
     * @param response 响应
     */
    @Override
    protected void doGet(Request request, Response response) {
        try {
            response.write("{\"msg\":\"success\"}", 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理 POST 请求
     *
     * @param request  请求
     * @param response 响应
     */
    @Override
    protected void doPost(Request request, Response response) {

    }
}
