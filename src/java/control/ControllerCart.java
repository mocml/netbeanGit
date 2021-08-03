/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 15/07/2021    1.0            Vanhv   
 */
package control;

import entity.Bill;
import entity.BillDetail;
import entity.Cart;
import entity.Customer;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOCart;
import model.DAOCustomer;
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author vanhv
 */
public class ControllerCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DBConnect db = new DBConnect();
        DAOCart daoCart = new DAOCart(db);
        DAOCustomer daoCus = new DAOCustomer(db);
        DAOBillDetail daoBillDetail = new DAOBillDetail(db);
        DAOBill daoBill = new DAOBill(db);
        DAOProduct daoPro = new DAOProduct(db);

        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");

            if (service == null) {
                service = "showcart";
            }
            if (service.equals("addtocart")) {

                String pid = request.getParameter("pid");
                Object value = session.getAttribute(pid);

                if (value == null) {
                    session.setAttribute(pid, "1");
                    request.setAttribute("maxQuantity", daoPro.getMaxQuantity());
                    request.getRequestDispatcher("ControllerProduct?service=displayAll").forward(request, response);
                } else {
                    int quantity = Integer.parseInt(value.toString());
                    ArrayList<Product> arr = daoPro.getMaxQuantity();
                    for (Product p : arr) {
                        if (pid.equals(p.getPid())) {
                            if (quantity < p.getQuantity()) {
                                session.setAttribute(pid, quantity + 1);
                            } else {
                                session.setAttribute(pid, quantity);
                            }
                        }
                    }
                    request.setAttribute("maxQuantity", daoPro.getMaxQuantity());
                    request.getRequestDispatcher("ControllerProduct?service=displayAll").forward(request, response);
                }
            }
            if (service.equals("showcart")) {

                ArrayList<Cart> listCart = new ArrayList<>();
                Enumeration em = session.getAttributeNames();
                //SET AND GET DATE 
                DateFormat format = new SimpleDateFormat();
                Date currentDate = new Date();
                String getDate = format.format(currentDate);

                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    if (id.equalsIgnoreCase("password") || id.equalsIgnoreCase("user")
                            || id.equalsIgnoreCase("username") || id.equalsIgnoreCase("listCart")) {
                        continue;
                    }
                    String quantity = session.getAttribute(id).toString();
                    String sql = "select pname,price from Product where pid='" + id + "'";
                    ResultSet rs = db.getData(sql);
                    if (rs.next()) {
                        String name = rs.getString(1);
                        double price = rs.getDouble(2);
                        double total = Double.valueOf(quantity) * price;
                        listCart.add(new Cart(id, getDate, name, quantity, price, total));
                    }
                }

                session.setAttribute("listCart", listCart);
                request.setAttribute("maxQuantity", daoPro.getMaxQuantity());
                request.getRequestDispatcher("ShowCart.jsp").forward(request, response);
            }
            if (service.equals("update")) {

                Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    if (id.equalsIgnoreCase("password") || id.equalsIgnoreCase("user")
                            || id.equalsIgnoreCase("username") || id.equalsIgnoreCase("listCart")) {
                        continue;
                    }
                    String getIdbyName = "p" + id;
                    String quantity = request.getParameter(getIdbyName);
                    ArrayList<Product> arr = daoPro.getMaxQuantity();
                    for (Product p : arr) {
                        if (id.equals(p.getPid())) {
                            if (Integer.valueOf(quantity) <= p.getQuantity()) {
                                session.setAttribute(id, quantity);
                            }
                        }
                    }
                }
                request.setAttribute("maxQuantity", daoPro.getMaxQuantity());
                request.getRequestDispatcher("ControllerCart?service=showcart").forward(request, response);
            }
            if (service.equals("remove")) {
                String id = request.getParameter("id");
                session.removeAttribute(id);
                request.setAttribute("maxQuantity", daoPro.getMaxQuantity());
                request.getRequestDispatcher("ControllerCart?service=showcart").forward(request, response);
            }
            if (service.equals("checkout")) {

                //checkLogin or not
                String username = (String) session.getAttribute("username");

                if (username == null) {
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                } else {
                    Customer customer = daoCus.getCustomerbyUserName(username);
                    //NEW RANDOM OID
                    String oId = daoCart.randomOid();
                    //Get cartList from showCart
                    ArrayList<Cart> listCart = (ArrayList<Cart>) session.getAttribute("listCart");
                    //Check cart ?isEmpty insert : buy
                    if (!listCart.isEmpty()) {
                        //SET get date
                        DateFormat format = new SimpleDateFormat();
                        Date currentDate = new Date();
                        String getDate = format.format(currentDate);
                        //Get Grantotal
                        double grandTotal = 0;
                        for (Cart c : listCart) {
                            grandTotal += c.getTotal();
                        }
                        //New Customer , Bill 
                        Bill bill = new Bill(oId, getDate, customer.getCname(), customer.getCphone(), customer.getcAddress(), grandTotal, 0, customer.getCid());
                        //Insert to bill
                        daoBill.insertBill(bill);
                        //New BillDetail and insert
                        for (Cart c : listCart) {
                            String pid = c.getpId();
                            int quantity = Integer.valueOf(c.getcQuantity());
                            double price = c.getPrice();
                            double total = c.getTotal();
                            BillDetail billDetail = new BillDetail(pid, oId, quantity, price, total);
                            daoBillDetail.insertBillDetail(billDetail);
                        }
                        //delete session by id
                        Enumeration em = session.getAttributeNames();
                        while (em.hasMoreElements()) {
                            String id = em.nextElement().toString();
                            if (id.equalsIgnoreCase("password") || id.equalsIgnoreCase("user")
                                    || id.equalsIgnoreCase("username")) {
                                continue;
                            }
                            session.removeAttribute(id);
                        }
                        //request to info customer
                        request.getRequestDispatcher("ControllerMyAccount?service=info").forward(request, response);
                    } else {
                        request.getRequestDispatcher("ControllerProduct?service=displayAll").forward(request, response);
                    }
                }
            }
            if (service.equals("cancel")) {

                String oid = request.getParameter("oid");
                daoBillDetail.deleteBillDetail(oid);
                daoBill.deleteBill(oid);
                request.getRequestDispatcher("ControllerMyAccount?service=info").forward(request, response);
            }
            if (service.equals("removeAll")) {
                Enumeration em = session.getAttributeNames();
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    if (id.equalsIgnoreCase("password") || id.equalsIgnoreCase("user")
                            || id.equalsIgnoreCase("username") || id.equalsIgnoreCase("listCart")) {
                        continue;
                    }
                    session.removeAttribute(id);

                }
                request.setAttribute("maxQuantity", daoPro.getMaxQuantity());
                request.getRequestDispatcher("ControllerCart?service=showcart").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
