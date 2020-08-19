package com.changsheng.diytomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author changshengee
 */
public class Request {
    /**
     * 请求url
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;

    public Request(InputStream in) throws IOException {
        byte[] bytes = new byte[1024];
        int length = in.read(bytes);
        String requestString = new String(bytes, 0, length);
        String firstLine = requestString.split("\n")[0];
        String[] arr = firstLine.split(" ");
        this.method = arr[0];
        this.url = arr[1];
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
