/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 13/06/2021    1.0            Vanhv   
 */
package model;

import entity.Admin;
import entity.Customer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanhv
 */
public class DAOLogin {

    Connection conn = null;
    DBConnect dbConn;

    public DAOLogin(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public Customer checkCustomerAccount(String username, String password) {
        Customer customer = null;
        String sql = "select username,password from Customer where username ='" + username + "' and password='" + password + "' and status =1";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                customer = new Customer(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public Admin checkAdminAccount(String username, String password) {
        Admin admin = null;
        String sql = "select * from admin where username ='" + username + "' and password ='" + password + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                admin = new Admin(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }
//
//    public ArrayList<Customer> getCustomer(String username) {
//        ArrayList<Customer> arr = new ArrayList<Customer>();
//        String sql = "select * from customer where username='" + username + "'";
//        ResultSet rs = dbConn.getData(sql);
//        try {
//            if (rs.next()) {
//                Customer cus = new Customer(rs.getInt(1), rs.getString(2),
//                        rs.getString(3), rs.getString(4),
//                        rs.getString(5), rs.getString(6), rs.getInt(7));
//                arr.add(cus);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return arr;
//    }

    public Admin getAdminObj(String username, String password) {
        Admin admin = null;
        String sql = "select * from admin where username ='" + username + "' and password='" + password + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                admin = new Admin(rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return admin;
    }

    public Customer getCustomerObj(String username, String password) {
        Customer customer = null;
        String sql = "select * from customer where username ='" + username + "' and password='" + password + "' and status =1";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                customer = new Customer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getInt(7));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public static void main(String[] args) {
        DBConnect dbcon = new DBConnect();
        DAOLogin dao = new DAOLogin(dbcon);
//        Customer customer = dao.getCustomerObj("customer1", "11111111");
        //  Admin admin = dao.getAdminObj("vanhv", "987654321");
        System.out.println(dao.getCustomerObj("customer1", "11111111"));
       // System.out.println(dao.getCustomerObj("vanhvs", "11111111"));
//        System.out.println(customer);
    }

}
