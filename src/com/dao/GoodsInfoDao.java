package com.dao;

import com.entity.GoodsInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsInfoDao {

    //商品修改操作
    public int updateGoodsInfo(GoodsInfo goodsInfo){

        StringBuffer sf = new StringBuffer();
        sf.append(" update goodsInfo set ");
        sf.append("    goodsInfo_name = ? ");
        sf.append("    ,goodsInfo_pic = ? ");
        sf.append("    ,goodsInfo_price = ? ");
        sf.append("    ,goodsInfo_description = ? ");
        sf.append("    ,goods_stock = ? ");
        sf.append(" where gid = ? ");
        //goodsInfo_name,goodsInfo_pic,goodsInfo_price,goodsInfo_description,goods_stock,flag
        List<Object> paramList = new ArrayList<>();
        paramList.add(goodsInfo.getGoodsName());
        paramList.add(goodsInfo.getGoodsPic());
        paramList.add(goodsInfo.getGoodsPrice());
        paramList.add(goodsInfo.getGoodsDescription());
        paramList.add(goodsInfo.getGoodsStock());
        paramList.add(goodsInfo.getGid());
        Connection conn = null;
        PreparedStatement pra = null;
        ResultSet rs = null;
        return BaseDao.executeUpdate(sf.toString(),paramList);
    }

    public List<GoodsInfo> findByGoodsInfo(GoodsInfo goodsInfo){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pra = null;
        List<GoodsInfo> list = new ArrayList<>();
        try {
            // 1、获得连接对象
            conn = BaseDao.getConnection();
            // 2、获得预编译语句集并执行SQL语句
            StringBuffer sf = new StringBuffer();
            List<Object> paramList = new ArrayList<>();
            sf.append(" select * from goodsInfo where 1=1 ");
            if(goodsInfo!=null) {
                // 根据id查询
                if (goodsInfo.getGid() > 0) {
                    sf.append(" and gid=? ");
                    paramList.add(goodsInfo.getGid());
                }
                // 根据username查询
                if (goodsInfo.getGoodsName() != null) {
                    sf.append(" and goodsInfo_name = ? ");
                    paramList.add("%"+goodsInfo.getGoodsName()+"%");
                }
            }
            // 2.1 获得预编译语句集
            pra = conn.prepareStatement(sf.toString());
            // 2.2 设置占位符的值(要优化的部分二）
            if(paramList!=null && paramList.size()>0){
                for(int i=0;i<paramList.size();i++){
                    pra.setObject(i+1, paramList.get(i));
                }
            }
            // 2.3 执行sql语句
            rs = pra.executeQuery();//不要传入sql
            // 2.4 遍历结果集
            // 根据主键查询，所以只会有0或1条记录
            while(rs.next()){
                GoodsInfo entity = new GoodsInfo();
                entity.setGid(rs.getInt("gid"));
                entity.setGoodsName(rs.getString("goodsInfo_name"));
                entity.setGoodsPic(rs.getString("goodsInfo_pic"));
                entity.setGoodsPrice(rs.getDouble("goodsInfo_price"));
                entity.setGoodsDescription(rs.getString("goodsInfo_description"));
                entity.setGoodsStock(rs.getInt("goods_stock"));
                entity.setFlag(rs.getString("flag"));
                list.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 3、关闭资源
            BaseDao.closeAll(conn, pra, rs);
        }
        return list;
    }

    public int insertGoodsInfo(GoodsInfo goodsInfo){
        String sql = "INSERT INTO GoodsInfo (goodsInfo_name,goodsInfo_pic,goodsInfo_price,goodsInfo_description,goods_stock,flag) " +
                "VALUES(?,?,?,?,?,?);";

        List<Object> paramList = new ArrayList<>();
        paramList.add(goodsInfo.getGoodsName());
        paramList.add(goodsInfo.getGoodsPic());
        paramList.add(goodsInfo.getGoodsPrice());
        paramList.add(goodsInfo.getGoodsDescription());
        paramList.add(goodsInfo.getGoodsStock());
        paramList.add(goodsInfo.getFlag());
        return BaseDao.executeUpdate(sql, paramList);
    }

    public int deleteGoodsInfo(GoodsInfo goodsInfo){
        String sql = "DELETE FROM GoodsInfo WHERE gid = ? ";
        List<Object> paramList = new ArrayList<>();
        paramList.add(goodsInfo.getGid());
        return BaseDao.executeUpdate(sql, paramList);
    }
}
