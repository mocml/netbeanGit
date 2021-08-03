/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 09/07/2021    1.0            Vanhv   
 */
package control;

import entity.Bill;
import entity.Cart;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOBill;
import model.DAOBillDetail;
import model.DAOCart;
import model.DAOCustomer;
import model.DAOLogin;
import model.DBConnect;

/**
 *
 * @author vanhv
 */
public class ControllerMyAccount extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer(db);
        DAOCart daoC = new DAOCart(db);
        DAOLogin daologin = new DAOLogin(db);
        DAOCustomer daoCus = new DAOCustomer(db);
        DAOBill daoBill = new DAOBill(db);

        String service = request.getParameter("service");
        HttpSession session = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {

            if (service == null) {
                request.getRequestDispatcher("ControllerProduct").forward(request, response);
            }
            if (service.equals("Update")) {
               
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String username = request.getParameter("username");
                int gender = Integer.valueOf(request.getParameter("gender"));

                Customer customer = new Customer(name, phone, address, username, gender);

                if (dao.updateCustomerWithoutUsername(customer) > 0) {
                    //needed fix
                    request.setAttribute("user", customer);
                    Customer customers = daoCus.getCustomerbyUserName(username);
                    ArrayList<Bill> listBill = daoBill.getAllBillbyUserName(customers.getCid());
                    request.setAttribute("listBill", listBill);
                    request.getRequestDispatcher("MyAccount.jsp?sp=customer").forward(request, response);
                }
            }
            if (service.equals("info")) {
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("password");
                if (daologin.checkCustomerAccount(username, password) != null) {
                    Customer customer = daoCus.getCustomerbyUserName(username);
                    ArrayList<Bill> listBill = daoBill.getAllBillbyUserName(customer.getCid());
                    request.setAttribute("listBill", listBill);
                    request.setAttribute("user", daologin.getCustomerObj(username, password));
                    request.getRequestDispatcher("MyAccount.jsp?sp=customer").forward(request, response);
                }
                if (daologin.checkAdminAccount(username, password) != null) {
                    request.setAttribute("listAllBill", daoBill.getAllBill());
                    request.setAttribute("user", daologin.getAdminObj(username, password));
                    request.getRequestDispatcher("MyAccount.jsp?sp=admin").forward(request, response);
                }
            }
            if (service.equals("detail")) {
              
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("password");
                String oid = request.getParameter("oid");
                ArrayList<Cart> listCart = daoC.getCartByOid(oid);
                request.setAttribute("listCart", listCart);
                if (daologin.checkCustomerAccount(username, password) != null) {
                    request.setAttribute("user", daologin.getCustomerObj(username, password));
                    request.getRequestDispatcher("CustomerBillDetail.jsp").forward(request, response);
                }
                if (daologin.checkAdminAccount(username, password) != null) {
                    request.setAttribute("listAllBill", daoBill.getAllBill());
                    request.setAttribute("user", daologin.getAdminObj(username, password));
                    request.getRequestDispatcher("AdminBillDetail.jsp").forward(request, response);
                }
            }
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
