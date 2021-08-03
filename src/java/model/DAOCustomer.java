/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 01/06/2021    1.0            Vanhv   
 */
package model;

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
public class DAOCustomer {

    Connection conn = null;
    DBConnect dbConn;

    public DAOCustomer(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public int insertCustomer(Customer customer) {
        int n = 0;
        String sql = "insert into Customer"
                + "(cname, cphone,cAddress,username,password,status) "
                + "values(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCname());
            pre.setString(2, customer.getCphone());
            pre.setString(3, customer.getcAddress());
            pre.setString(4, customer.getUsername());
            pre.setString(5, customer.getPassword());
            pre.setInt(6, customer.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }


    public Customer getCustomerbyUserName(String username) {
        Customer customer = null;
        String sql = "select * from Customer where username ='" + username + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                customer = new Customer(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public boolean checkExistAdminAccount(String username) {
        String sql = "select * from admin where username = '" + username + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean checkExistCustommerAccount(String username) {
        String sql = "select * from customer where username = '" + username + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Customer dupdicateUsername(String username) {
        Customer customer = null;
        String sql = "select * from Customer where username ='" + username + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                customer = new Customer(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public int updateCustomer(Customer customer) {
        int n = 0;
        String sql = "update Customer set cname=?, cphone=?,cAddress= ?,"
                + "password = ?,status =? where username = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCname());
            pre.setString(2, customer.getCphone());
            pre.setString(3, customer.getcAddress());
            pre.setString(4, customer.getPassword());
            pre.setInt(5, customer.getStatus());
            pre.setString(6, customer.getUsername());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomerWithoutUsername(Customer customer) {
        int n = 0;
        String sql = "update Customer set cname=?, cphone=?,cAddress= ?,"
                + "status =? where username = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCname());
            pre.setString(2, customer.getCphone());
            pre.setString(3, customer.getcAddress());
            pre.setInt(4, customer.getStatus());
            pre.setString(5, customer.getUsername());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteCustomer(int cid) {
        int n = 0;
        String sql = "delete from customer where cid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

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

    public static void main(String[] args) {

        DBConnect dbcon = new DBConnect();
        DAOCustomer dao = new DAOCustomer(dbcon);
//        int insert = dao.insertCustomer(new Customer("HA VAN VAN 5", "035",
//                "HAIPHONG5", "customer2", "55555555", 0));
//        if (insert > 0) {
//            System.out.println(" insert");
//        }
//        int update = dao.updateCustomer(new Customer("HA VAN VAN 3", "0331",
//                "HAIPHONG3", "customer", "24242424", 1));
//        if (update > 0) {
//            System.out.println(" updated");
//        }
        System.out.println(dao.getCustomerbyUserName("vanhvs"));
    }

}
