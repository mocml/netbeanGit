/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 08/07/2021    1.0            Vanhv   
 */
package model;

import entity.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vanhv
 */
public class DAOAdmin {

    Connection conn = null;
    DBConnect dbConn;

    public DAOAdmin(DBConnect dbconn) {
        conn = dbconn.con;
        this.dbConn = dbconn;
    }

    public int addAdmin(Admin obj) {
        int n = 0;
        String preSql = "insert into admin(username, password) "
                + " values (?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(preSql);
            pre.setString(1, obj.getUsername());
            pre.setString(2, obj.getPassword());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateAdmin(Admin obj) {
        int n = 0;
        String sql = "update admin set password = ? where username = ? ";

        try {
            PreparedStatement pre = dbConn.con.prepareStatement(sql);
            pre.setString(1, obj.getPassword());
            pre.setString(2, obj.getUsername());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DBConnect dbconn = new DBConnect();
        DAOAdmin dao = new DAOAdmin(dbconn);
//        int insert = dao.addAdmin(new Admin("admin1", "123456789"));
//        if (insert > 0) {
//            System.out.println("inserted");
//        }
        int update = dao.updateAdmin(new Admin("admin1", "987654321"));
        if (update > 0) {
            System.out.println("update");
        }
    }
}
