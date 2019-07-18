package com.servlet;


import com.dao.GoodsInfoDao;
import com.entity.GoodsInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;



public class GoodsInsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg = "";
        int rows = 0;
        try {
            GoodsInfo goodsInfo = UploadFile.uploadFile(req, resp);
            if(goodsInfo.getGoodsName()==null || "".equals(goodsInfo.getGoodsName())){
                throw new RuntimeException("商品名称不能为空");
            }
            GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
            rows = goodsInfoDao.insertGoodsInfo(goodsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if(rows>0){
            // 表示修改成功
            out.println("<script type='text/javascript'>alert('保存成功');location.href='goodsQueryServlet';</script>");
        }else{
            // 修改失败
            out.println("<script type='text/javascript'>alert('保存失败："+errMsg+"');history.back();</script>");
        }
    }
}

