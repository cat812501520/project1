package com.servlet;

import com.dao.GoodsInfoDao;
import com.entity.GoodsInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class GoodUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errMsg ="";
        int rows = 0;
        try {
            GoodsInfo goodsInfo=this.uploadFile(req, resp);
            if(goodsInfo.getGoodsName()==null || "".equals(goodsInfo.getGoodsName())){
                throw new RuntimeException("商品名称不能为空");
            }
            GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
            rows = goodsInfoDao.updateGoodsInfo(goodsInfo);
        } catch (Exception e) {
            e.printStackTrace();
            errMsg = e.getMessage();
        }
        PrintWriter out = resp.getWriter();
        if(rows>0){
            out.println("<script type='text/javascript'>alert('修改成功');location.href='goodsQueryServlet';</script>");
        }else{
            out.println("<script type='text/javascript'>alert('修改失败："+errMsg+"');history.back();</script>");
        }
    }

    public GoodsInfo uploadFile(HttpServletRequest req,HttpServletResponse resp) throws FileUploadException, IOException {
        GoodsInfo goodsInfo = new GoodsInfo();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if(isMultipart){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> fileItemList = upload.parseRequest(req);
            if(fileItemList!=null && fileItemList.size()>0){
                for(FileItem fileItem:fileItemList){
                    if(fileItem.isFormField()){
                        if("gid".equals(fileItem.getFieldName())){
                            goodsInfo.setGid(Integer.parseInt(fileItem.getString()));
                        }else if("goodsName".equals(fileItem.getFieldName())) {
                            goodsInfo.setGoodsName(fileItem.getString("utf-8"));
                        }else if("goodsPic".equals(fileItem.getFieldName())){
                            goodsInfo.setGoodsPic(fileItem.getString());
                        }else if("goodsPrice".equals(fileItem.getFieldName())){
                            goodsInfo.setGoodsPrice(Double.parseDouble(fileItem.getString()));
                        }else if("goodsDescription".equals(fileItem.getFieldName())){
                            goodsInfo.setGoodsDescription(fileItem.getString("utf-8"));
                        }else if("goodsStock".equals(fileItem.getFieldName())){
                            goodsInfo.setGoodsStock(Integer.parseInt(fileItem.getString()));
                        }
                    }else{
                    String fileName = fileItem.getName();
                    if(!"".equals(fileName)&&fileName!=null) {
                        String parentPath = req.getServletContext().getRealPath("/upload");
                        File parentFile = new File(parentPath);
                        if(!parentFile.exists()) parentFile.mkdirs();
                        File newFile = new File(parentFile,fileName);
                        InputStream is = fileItem.getInputStream();
                        OutputStream os = new FileOutputStream(newFile);
                        IOUtils.copy(is, os);
                        os.close();
                        is.close();
                    }else {
                    }
                        goodsInfo.setGoodsPic(fileName);
                    }
                }
            }
        }
        return goodsInfo;
    }


}
