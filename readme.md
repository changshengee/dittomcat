# DiyTomcat

## 浏览器发送请求到服务器响应过程

1.浏览器发出HTTP请求，Tomcat中的web服务器负责接收解析，并创建请求和相应对象。(request response)
2.若无servlet 映射，则可直接访问解析的资源，把资源封装到response并返回到Web服务器，Web服务器将信息拆解成Http Response返回给浏览器显示。
3.若有servlet 映射，则去web.xml查询对应的servlet路劲，并将请求、响应传输给对应的servlet对象，处理完逻辑后，把信息封装到response返回给web
服务器拆解，然后响应给浏览器显示。
4.弱即无资源，业务servlet映射，返回404。

## 基本对象

- Response 请求
- Request 响应
- BaseServlet 总Servlet
- Server 服务器
- BootStrap 最初始版本WEB


