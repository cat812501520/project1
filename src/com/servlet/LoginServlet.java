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
import java.util.List;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDao userdao = new UserDao();
        User testUser = new User(username);
        List<User> testList = userdao.findByUser(testUser);
        PrintWriter out = resp.getWriter();
        if(testList.size()!=0){
            if(testList.get(0).getUsername().equals(username)){
                if(testList.get(0).getPassword().equals(password)){
                    req.setAttribute("msg", "登录成功");
                    HttpSession session = req.getSession();
                    User user = new User(testList.get(0).getUsername(),
                            testList.get(0).getPassword(), testList.get(0).getSex(),
                            testList.get(0).getHobbys(),testList.get(0).getPhone(),
                            testList.get(0).getEmail(),testList.get(0).getAddress());
                    session.setAttribute("user", user);
                    session.setAttribute("msg", "登录成功");
                    out.println("<script type='text/javascript'>alert('登录成功');location.href='goodsQueryServlet';</script>");
                }
            }
        }
        else{
            out.println("<script type='text/javascript'>alert('登录失败');location.href='login.jsp';</script>");
        }
    }
}
