package com.servlet;

import com.dao.GoodsInfoDao;
import com.entity.GoodsInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GoodsDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        int rows = 0;
        try {
            int gid = Integer.parseInt(req.getParameter("gid"));
            GoodsInfo goodsInfo=new GoodsInfo(gid);
            if(goodsInfo.getGid()==0 || "".equals(goodsInfo.getGid())){
                throw new RuntimeException("商品编号不能为空");
            }
            GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
            rows = goodsInfoDao.deleteGoodsInfo(goodsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if(rows>0){
            out.println("<script type='text/javascript'>alert('删除成功');location.href='goodsQueryServlet';</script>");
        }else{
            out.println("<script type='text/javascript'>alert('删除失败："+errMsg+"');history.back();</script>");
        }
    }
}
