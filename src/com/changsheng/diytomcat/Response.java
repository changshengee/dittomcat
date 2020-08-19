package com.changsheng.diytomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author changshengee
 */
public class Response {
    private OutputStream out;

    public Response(OutputStream out) {
        this.out = out;
    }

    public void write(String content, int statusCode) throws IOException {
        out.write(("HTTP/1.1 " + statusCode + " OK\n").getBytes());
        out.write(("Content-Type:text/html;Charset=utf-8\n").getBytes());
        out.write(("\n").getBytes());
        out.write(content.getBytes());
    }
}
