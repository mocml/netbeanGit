/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 08/07/2021    1.0            Vanhv   
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOLogin;
import model.DBConnect;

/**
 *
 * @author vanhv
 */
public class Login extends HttpServlet {

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
        DAOLogin daologin = new DAOLogin(db);
        String login = request.getParameter("login");
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {
            if (login == null) {
                login = "login";
            }
            if (login.equals("login")) {
                session.removeAttribute("username");
                session.removeAttribute("password");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String remember = request.getParameter("remember");
                if (remember == null) {
                    session.setMaxInactiveInterval(300);
                }
//                out.print("Welcome : "+username +", your password is "+password);
                if (daologin.checkCustomerAccount(username, password) != null) {
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    System.out.println(username);
                    System.out.println(password);
                    request.getRequestDispatcher("ControllerProduct").forward(request, response);
                } else if (daologin.checkAdminAccount(username, password) != null) {
                    session.setAttribute("username", username);
                    session.setAttribute("password", password);
                    System.out.println(username);
                    System.out.println(password);
                    request.getRequestDispatcher("ControllerProduct?service=productManager").forward(request, response);
                } else {
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
            }
            if (login.equals("register")) {
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
            if (login.equals("logOut")) {
                session.invalidate();
                request.getRequestDispatcher("ControllerProduct").forward(request, response);
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
