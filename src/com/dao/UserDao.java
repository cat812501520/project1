package com.dao;

import com.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
//用户修改操作
    public int updateUser(User user){
        StringBuffer sf = new StringBuffer();
        sf.append(" update user set ");
        sf.append("    password = ? ");
        sf.append("    ,sex = ? ");
        sf.append("    ,hobbys = ? ");
        sf.append("    ,phone = ? ");
        sf.append("    ,email = ? ");
        sf.append("    ,address = ? ");
        sf.append("    ,flag = ? ");
        sf.append(" where username = ? ");

        List<Object> paramList = new ArrayList<>();
        paramList.add(user.getPassword());
        paramList.add(user.getSex());
        paramList.add(user.getHobbys());
        paramList.add(user.getPhone());
        paramList.add(user.getEmail());
        paramList.add(user.getAddress());
        paramList.add(user.getFlag());
        paramList.add(user.getUsername());
        return BaseDao.executeUpdate(sf.toString(), paramList);
    }

    public List<User> findByUser(User user){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pra = null;
        List<User> list = new ArrayList<>();
        try {
            // 1、获得连接对象
            conn = BaseDao.getConnection();
            // 2、获得预编译语句集并执行SQL语句
            StringBuffer sf = new StringBuffer();
            List<Object> paramList = new ArrayList<>();
            sf.append(" select * from user where 1=1 ");
            if(user!=null) {

                // 根据username查询
                if (user.getUsername() != null) {
                    sf.append(" and username = ? ");
                    paramList.add(user.getUsername());
                }
            }
            System.out.println("sql:"+sf.toString());
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
                User entity = new User();
                entity.setUid(rs.getInt("uid"));
                entity.setUsername(rs.getString("username"));
                entity.setPassword(rs.getString("password"));
                entity.setSex(rs.getString("sex"));
                entity.setHobbys(rs.getString("hobbys"));
                entity.setPhone(rs.getString("phone"));
                entity.setEmail(rs.getString("email"));
                entity.setAddress(rs.getString("address"));
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

    public int insertUser(User user){
        String sql = "INSERT INTO USER (username,PASSWORD,sex,hobbys,phone,email,address) " +
                "VALUES(?,?,?,?,?,?,?);";

        List<Object> paramList = new ArrayList<>();
        paramList.add(user.getUsername());
        paramList.add(user.getPassword());
        paramList.add(user.getSex());
        paramList.add(user.getHobbys());
        paramList.add(user.getPhone());
        paramList.add(user.getEmail());
        paramList.add(user.getAddress());


        return BaseDao.executeUpdate(sql, paramList);
    }

}

