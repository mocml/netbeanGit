/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 08/07/2021    1.0            Vanhv   
 */
package control;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomer;
import model.DBConnect;

/**
 *
 * @author vanhv
 */
public class Register extends HttpServlet {

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
        String service = request.getParameter("register");
        try (PrintWriter out = response.getWriter()) {
            if (service == null) {
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
            if (service.equals("login")) {
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
            if (service.equals("register")) {
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String rePassword = request.getParameter("repassword");
                int gender = Integer.valueOf(request.getParameter("gender"));
                if (password.equals(rePassword) && dao.dupdicateUsername(username) == null  && dao.checkExistAdminAccount(username)) {
                    Customer customer = new Customer(name, phone, address, username, password, gender);
                    if (dao.insertCustomer(customer) > 0) {
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                }

                //   out.print(name + " " + phone + " " + address + " " + username + " " + password + " " + rePassword + " " + gender);
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
