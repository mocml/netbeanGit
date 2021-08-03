/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 16/07/2021    1.0            Vanhv   
 */
package model;

import entity.Bill;
import entity.Customer;
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
public class DAOBill {

    Connection conn = null;
    DBConnect dbConn;

    public DAOBill(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public void insertBill(Bill obj) {
        String sql = "insert into Bill"
                + "(oID ,dateCreate,cname,cphone,cAddress,total,status,cid) "
                + "values(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, obj.getoID());
            pre.setString(2, obj.getDateCreate());
            pre.setString(3, obj.getCname());
            pre.setString(4, obj.getCphone());
            pre.setString(5, obj.getcAddress());
            pre.setDouble(6, obj.getTotal());
            pre.setInt(7, obj.getStatus());
            pre.setInt(8, obj.getCid());

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Bill> getAllBill() {
        ArrayList<Bill> arr = new ArrayList<>();
        String sql = "select * from bill";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Bill bill = new Bill(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getDouble(6), rs.getInt(7), rs.getInt(8));
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public ArrayList<Bill> getAllBillbyUserName(int cid) {
        ArrayList<Bill> arr = new ArrayList<>();
        String sql = "select * from bill where cid=" + cid;
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Bill bill = new Bill(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getDouble(6), rs.getInt(7), rs.getInt(8));
                arr.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
//    public Bill getDateByOid(String oid){
//    
//    }

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> arr = new ArrayList<>();
        String sql = "select * from customer";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Customer cus = new Customer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));
                arr.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public void deleteBill(String oid) {
        String sql = "delete from Bill where oid =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, oid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateStatusByOid(int status, String oid) {
        String sql = "update Bill set status=? where oid =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, oid);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        DBConnect dbcon = new DBConnect();
        DAOBill dao = new DAOBill(dbcon);
//        ArrayList<Bill> list = dao.getAllBillbyUserName(3007);
//        for (Bill b : list) {
//            System.out.println(b);
//        }
        dao.deleteBill("PzbvgBs");
        ArrayList<Bill> list = dao.getAllBill();
        for (Bill b : list) {
            System.out.println(b);
        }
        System.out.println("Size : " + list.size());
        // dao.insertBill(new Bill("qwe", "12/12/2021","haha", "01234", "haa", 12112, 1, 1));
    }
}
