package com.changsheng.diytomcat;

import java.io.IOException;

/**
 * @author changshengee
 */
public class Startup {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Server server = new Server();
        server.start();
    }
}
