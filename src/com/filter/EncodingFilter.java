package com.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String encoding;
    // 初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("EncodingFilter init...");
        // 读取配置文件中配置的编码方式
        encoding = filterConfig.getInitParameter("encoding");
    }

    //执行过滤
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        System.out.println("EncodingFilter doFilter...");
        // 1、设置请求的编码
        servletRequest.setCharacterEncoding(encoding);
        // 2、设置响应的编码
        // 服务器发送给客户的内容的编码方式
        servletResponse.setCharacterEncoding(encoding);
        // 浏览器以什么编码方式执行
        servletResponse.setContentType("text/html;charset=utf-8");
        // 3、继续执行客户端的请求
        // 假如我们访问的url：http://localhost:8080/helloServlet
        // 当Web服务器接收到请求后去web.xml中做地址映射，先看有没有Filter的配置
        // 如果有，就再看Filter的<url-parttern>配置的值是否需要拦截当前请求
        //      如果需要，则通过Web服务器调用doFilter()方法执行代码，
        //      然后在这个方法内再调用filterChain.doFilter继续请求/helloServlet
        //      web服务器去检查servlet-mapping配置的<url-parttern>
        // 如果不需要就直接放行调用filterChain.doFilter继续请求/helloServlet
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //销毁
    @Override
    public void destroy() {
        System.out.println("EncodingFilter destroy...");
    }
}
