/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 16/07/2021    1.0            Vanhv   
 */
package model;

import entity.Cart;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanhv
 */
public class DAOCart {

    Connection conn = null;
    DBConnect dbConn;

    public DAOCart(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }
    public ArrayList<Cart> getCartByOid(String oid) {
        ArrayList<Cart> list = new ArrayList<>();
        String sql = "select BillDetail.pid,Bill.dateCreate,Product.pname,BillDetail.quantity,BillDetail.price,BillDetail.total from (BillDetail "
                + " inner join Bill on Bill.oID = BillDetail.oID "
                + " inner join Product on Product.pid = BillDetail.pID"
                + " and BillDetail.oID in (select oid from bill where oid='" + oid + "')"
                + ") ";
        ResultSet rs = dbConn.getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String date = rs.getString(2);
                String name = rs.getString(3);
                String quantity = rs.getString(4);
                double price = rs.getDouble(5);
                double total = rs.getDouble(6);
                Cart cart = new Cart(pid, date, name, quantity, price, total);
                list.add(cart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public String randomOid() {
        String character = "ABCDEFGHIKLMNOPQRSTUVWXYZ123456789abcdefghiklmnopqrstuvwxyz";
        String random = "";
        int length = 7;
        Random rand = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = character.charAt(rand.nextInt(character.length()));
        }
        for (int i = 0; i < text.length; i++) {
            random += text[i];
        }
        return random;
    }
}
