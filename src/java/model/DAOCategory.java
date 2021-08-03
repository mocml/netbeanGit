/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 01/06/2021    1.0            Vanhv   
 */
package model;

import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanhv
 */
public class DAOCategory {

    Connection conn = null;
    DBConnect dbConn;

    public DAOCategory(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public int insertCategory(Category obj) {
        int n = 0;
        String sql = "insert into Category (cateName,status) values(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getCateName());
            pre.setInt(2, obj.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
//ERROR

    public int updateCategory(Category obj) {
        int n = 0;
        String sql = "update Category set cateName=?, status =?"
                + " where cateID = ?";//new "where thi loi " where thi khong
        try {
            PreparedStatement pre = dbConn.con.prepareStatement(sql);
            pre.setString(1, obj.getCateName());
            pre.setInt(2, obj.getStatus());
            pre.setInt(3, obj.getCateID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCategory(int cateid) {
        int n = 0;
        String sql = "delete from Category where cateid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> arr = new ArrayList<Category>();
        String sql = "select * from Category";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Category cate = new Category(rs.getInt(1),
                        rs.getString(2), rs.getInt(3));
                arr.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public static void main(String[] args) {
        DBConnect dbcon = new DBConnect();
        DAOCategory dao = new DAOCategory(dbcon);
//         int insert = dao.insertCategory(new Category("cate 1", 0));
//         if(insert >0){
//             System.out.println("inserted");
//         }
//         //
//        int update = dao.updateCategory(new Category(1, "van22", 1));
//        if (update > 0) {
//            System.out.println("updated");
//        }
        if(dao.deleteCategory(1004)>0){
            System.out.println("deleted");
        }
    }
}
