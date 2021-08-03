/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.BillDetail;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOProduct {
    
    Connection conn;
    DBConnect dbConn;
    
    public DAOProduct(DBConnect dbcon) {
        conn = dbcon.con;
        this.dbConn = dbcon;
    }
    
    public int insertProduct(Product pro) {
        int n = 0;
        String sql = "insert into Product"
                + "(pid,pname,quantity,price,image,description,status,cateID) "
                + "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = dbConn.con.prepareStatement(sql);
            pre.setString(1, pro.getPid());
            pre.setString(2, pro.getPname());
            pre.setInt(3, pro.getQuantity());
            pre.setDouble(4, pro.getPrice());
            pre.setString(5, pro.getImage());
            pre.setString(6, pro.getDescription());
            pre.setInt(7, pro.getStatus());
            pre.setInt(8, pro.getCateID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public ArrayList<Product> getAllProductWithoutStatus() {
        ArrayList<Product> arr = new ArrayList<>();
        String sql = "select * from Product";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Product pro = new Product(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8));
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> arr = new ArrayList<>();
        String sql = "select * from Product where status =1";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Product pro = new Product(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8));
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public ArrayList<Product> getProductByID(String cateID) {
        ArrayList<Product> arr = new ArrayList<>();
        String sql = "select * from Product where cateid = '" + cateID + "' and status =1";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Product pro = new Product(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8));
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public ArrayList<Product> getProductByIdWithoutStatus(String cateID) {
        ArrayList<Product> arr = new ArrayList<>();
        String sql = "select * from Product where cateid = '" + cateID + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Product pro = new Product(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8));
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public String getCateName(int cateID) {
        String name = null;
        String sql = "select cateName from Category where cateID=?";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }
    
    public ArrayList<Product> getProductDetail(String pid) {
        ArrayList<Product> arr = new ArrayList<>();
        String sql = "select pid,pname,price,image from Product where pid ='" + pid + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                Product pro = new Product(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
                arr.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    public Product getProduct(String pid) {
        Product product = null;
        String sql = "select * from Product where pid ='" + pid + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                product = new Product(pid, rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getString(5),
                        rs.getString(6), rs.getInt(7), rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
    
    public int deleteProduct(String pid) {
        int n = 0;
        String sql = "delete from product where pid = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public ArrayList<Product> getMaxQuantity() {
        ArrayList<Product> arr = new ArrayList<>();
        String sql = "select pid, quantity from Product where status = 1";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                arr.add(new Product(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
    
    public int getQuantity(String pid) {
        int quantity = 0;
        String sql = "select quantity from Product where pid='" + pid + "'";
        ResultSet rs = dbConn.getData(sql);
        try {
            if (rs.next()) {
                quantity = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity;
    }
    
    public void updateQuantity(BillDetail bd) {
        String sql = "update Product set quantity = ? where pid =?";
        try {
            PreparedStatement pre = dbConn.con.prepareStatement(sql);
            pre.setInt(1, bd.getQuantity());
            pre.setString(2, bd.getPid());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStatusDisable() {
        String sql = "update Product set status = 0 where quantity=0";
        try {
            PreparedStatement pre = dbConn.con.prepareStatement(sql);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStatusEnable() {
        String sql = "update Product set status = 1 where quantity >0";
        try {
            PreparedStatement pre = dbConn.con.prepareStatement(sql);
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int updateProduct(Product pro) {
        int n = 0;
        //
        String preSql = "update Product set pname=?,"
                + "quantity=?,price=?,image=?,description=?,"
                + "status=?, "
                + "cateID=? where pid=?";
        try {
            // PreparedStatement pre = conn.prepareStatement(preSql);
            PreparedStatement pre = dbConn.con.prepareStatement(preSql);
            //index of ? start 1
            //DataType is datatype of ?
            // pre.setDataType(index of ?, value);
            pre.setString(1, pro.getPname());
            pre.setInt(2, pro.getQuantity());
            pre.setDouble(3, pro.getPrice());
            pre.setString(4, pro.getImage());
            pre.setString(5, pro.getDescription());
            pre.setInt(6, pro.getStatus());
            pre.setInt(7, pro.getCateID());
            pre.setString(8, pro.getPid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public void displayAllProduct() {
        String sql = "select * from Product";
        try {
            //Statement state=conn.createStatement(); default
            //TYPE_FORWARD_ONLY: default --> pointer top --> down only
            //TYPE_SCROLL_SENSITIVE: scroll; thread safe
            //CONCUR_READ_ONLY: default, can't change ResultSet
            //CONCUR_UPDATABLE: can change resultset --> database
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            //ResultSet rs=dbConn.getData(sql);
            while (rs.next()) {
//                rs.getDataType(index, fieldName);
//                dataType is dataType of filedName
                String pid = rs.getString("pid");//rs.getString(1)
                String pname = rs.getString(2);//rs.getString("pname")
                int quantity = rs.getInt("quantity");// rs.getInt(3)
                double price = rs.getDouble(4);
                String image = rs.getString(5),
                        description = rs.getString(6);
                int status = rs.getInt(7), cateID = rs.getInt(8);
                Product pro = new Product(pid, pname, quantity, price, image, description, status, cateID);
                System.out.println(pro);
                
            }
            //PreparedStatement pre=conn.prepareStatement(sql, 0, 0);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet searchByName(String name) {
        String sql = "select * from Product where pname='" + name + "'";
        return dbConn.getData(sql);
    }
    
    public void searchByPrice(double from, double to) {
        
    }

//    public int deleteProduct(String id) {
//        int n = 0;
//        //step1: check Bill: has pid? (select)
//        // step 1yes: change status 1-0
//        //n0: delete
//        return n;
//    }
    public static void main(String[] args) {
        DBConnect dbcon = new DBConnect();
        DAOProduct dao = new DAOProduct(dbcon);
        //int insert = dao.updateProduct(new Product("c1", "Coca", 5, 12345, "image.jpg", "sold", 1, 5));
        //        int del = dao.deleteProduct("323");
        //        if (del > 0) {
        //            System.out.println("inserted");
        //        }
        //        dao.displayAllProduct();
        //        for (Product pro : dao.getAllProduct()) {
        //            System.out.println(pro);
        //        }
        //ArrayList<Product
        //System.out.println(dao.getProductByID(2));
        //    dao.updateQuantity(new BillDetail("1", 10000));
//        System.out.println(dao.getQuantity("4"));
//        dao.updateStatus();
        for (Product p : dao.getMaxQuantity()) {
            if (p.getPid().equals("3")) {
                System.out.println(p.getQuantity());
            }
        }
    }
    
}
