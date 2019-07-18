package com.servlet;

import com.dao.GoodsInfoDao;
import com.entity.GoodsInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GoodsQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
        List<GoodsInfo> goods = goodsInfoDao.findByGoodsInfo(null);
        req.setAttribute("goods", goods);
        req.getRequestDispatcher("good_list.jsp").forward(req, resp);
    }
}
