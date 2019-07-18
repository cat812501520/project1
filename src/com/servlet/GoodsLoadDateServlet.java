package com.servlet;


import com.dao.GoodsInfoDao;
import com.entity.GoodsInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GoodsLoadDateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        int a =0;
        try {
            int gid = Integer.parseInt(req.getParameter("gid"));
            a =Integer.parseInt(req.getParameter("a"));
            if(gid == 0 || "".equals(gid)){
                throw new RuntimeException("商品编号不能为空");
            }
            GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
            GoodsInfo queryGood = new GoodsInfo(gid);
            List<GoodsInfo> list = goodsInfoDao.findByGoodsInfo(queryGood);
            if(list==null && list.size()<1){
                throw new RuntimeException("该商品找不到");
            }
            req.setAttribute("goods", list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }finally {
            if("".equals(errMsg)){
                if(a==1) {
                    req.getRequestDispatcher("good_update.jsp").forward(req, resp);
                }else {
                    req.getRequestDispatcher("good_view.jsp").forward(req, resp);
                }
            }else{
                PrintWriter out = resp.getWriter();
                out.println("<script type='text/javascript'>alert('数据加载失败：'"+errMsg+");history.back();</script>");
            }
        }
    }
}
