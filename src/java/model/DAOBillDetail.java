/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 16/07/2021    1.0            Vanhv   
 */
package model;

import entity.BillDetail;
import entity.Cart;
import entity.Product;
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
public class DAOBillDetail {

    Connection conn = null;
    DBConnect dbConn;

    public DAOBillDetail(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public void insertBillDetail(BillDetail obj) {
        String sql = "insert into BillDetail(pid,oID,quantity,price,total) "
                + "values(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getPid());
            pre.setString(2, obj.getoID());
            pre.setInt(3, obj.getQuantity());
            pre.setDouble(4, obj.getPrice());
            pre.setDouble(5, obj.getTotal());

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<BillDetail> getQuantityOrder(String oid) {
        ArrayList<BillDetail> arr = new ArrayList<>();
        String sql = "select pid , quantity from BillDetail where oid ='" + oid + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                int quantity = rs.getInt(2);
                BillDetail bd = new BillDetail(pid, quantity);
                arr.add(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    //get by join 3 table


    public ArrayList<BillDetail> getAllBillDetailByOid(String oid) {
        ArrayList<BillDetail> list = new ArrayList<>();
        String sql = "select * from BillDetail where oid ='" + oid + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                BillDetail bd = new BillDetail(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getDouble(5));
                list.add(bd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deleteBillDetail(String oid) {
        String sql = "delete from BillDetail where oid =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, oid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        DBConnect db = new DBConnect();
        DAOBillDetail dao = new DAOBillDetail(db);
        DAOProduct daoPro = new DAOProduct(db);
//        ArrayList<Cart> list = dao.getCartByOid("Pi4TDQU");
//        for (Cart c : list) {
//            System.out.println(c);
//        }
//        dao.deleteBillDetail("PzbvgBs");
    }
}
