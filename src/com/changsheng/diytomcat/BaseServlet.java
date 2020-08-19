package com.changsheng.diytomcat;

/**
 * @author changshengee
 */
public abstract class BaseServlet {

    public void servlet(Request request, Response response) {
        boolean post = request.getMethod().equalsIgnoreCase("POST");
        if (post) {
            doPost(request, response);
            return;
        }
        doGet(request, response);
    }

    /**
     * 处理 GET 请求
     *
     * @param request  请求
     * @param response 响应
     */
    protected abstract void doGet(Request request, Response response);

    /**
     * 处理 POST 请求
     *
     * @param request  请求
     * @param response 响应
     */
    protected abstract void doPost(Request request, Response response);
}
