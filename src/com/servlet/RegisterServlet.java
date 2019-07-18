package com.servlet;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int rows =0;
        String errMsg ="";
        try{
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String sex = req.getParameter("sex");
            String[] hobby = req.getParameterValues("hobbys");
            String hobbys=Arrays.toString(hobby);
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
        //        String flag = req.getParameter("flag");
            String msg = (String)req.getAttribute("msg");
            if(username==null||"".equals(username)){
                throw new RuntimeException("用户名不能为空");
            }
            if(password==null||"".equals(password)){
                    throw new RuntimeException("密码不能为空");
            }
            if(sex==null||"".equals(sex)){
                    throw new RuntimeException("性别不能为空");
            }
            if(phone==null||"".equals(phone)){
                    throw new RuntimeException("手机号不能为空");
            }
            if(email==null||"".equals(email)){
                    throw new RuntimeException("邮箱不能为空");
            }
            if(address==null||"".equals(address)){
                    throw new RuntimeException("地址不能为空");
            }
                UserDao userdao = new UserDao();
                User user = new User(username,password,sex,hobbys,phone,email,address);
                List list = userdao.findByUser(user);
                PrintWriter out = resp.getWriter();
                if(list.size()==0) {
                    rows = userdao.insertUser(user);
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                }
            }catch (Exception e){
            e.printStackTrace();
            errMsg = e.getMessage();
            }
            PrintWriter out = resp.getWriter();
            if(rows>0){
                // 表示修改成功
                out.println("<script type='text/javascript'>alert('注册成功');location.href='login.jsp';</script>");
            } else {
                req.setAttribute("msg", "注册失败,用户名已被注册");
                out.println("<script type='text/javascript'>alert('注册失败');history.back();</script>");
            }
    }
}
