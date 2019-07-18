package com.servlet;

import com.entity.GoodsInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class UploadFile {
    public static GoodsInfo uploadFile(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
        GoodsInfo goodsInfo = new GoodsInfo();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if(isMultipart){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> fileItemList = upload.parseRequest(req);
            if(fileItemList!=null && fileItemList.size()>0){
                for(FileItem fileItem:fileItemList){
                    if(fileItem.isFormField()){
                        if("gid".equals(fileItem.getFieldName())) {
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
