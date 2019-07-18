package com.filter;

import com.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);
        servletResponse.setContentType("text/html;charset=utf-8");

        Boolean flag =true;
        HttpServletRequest req =(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;
        User user =(User) req.getSession().getAttribute("user");
        String requestURI = req.getRequestURI();
        flag = flag && !requestURI.startsWith("/login.jsp");
        flag = flag && !requestURI.startsWith("/index.jsp");
        flag = flag && !requestURI.startsWith("/loginServlet");
        flag = flag && !requestURI.startsWith("/register.jsp");
        flag = flag && !requestURI.startsWith("/RegisterServlet");
        if(flag){
            if(user==null){
                PrintWriter out = resp.getWriter();
                out.println("<script type='text/javascript'>alert('请先登录或注册');location.href='index.jsp';</script>");
            }else {
                filterChain.doFilter(req,resp);
            }
        }else {
            filterChain.doFilter(req,resp);
        }
    }

    //销毁
    @Override
    public void destroy() {
        System.out.println("EncodingFilter destroy...");
    }
}
