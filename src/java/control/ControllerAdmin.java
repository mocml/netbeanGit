/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 16/07/2021    1.0            Vanhv   
 */
package control;

import entity.BillDetail;
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
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author vanhv
 */
public class ControllerAdmin extends HttpServlet {

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
        DAOBill daoBill = new DAOBill(db);
        DAOBillDetail daoDetail = new DAOBillDetail(db);
        DAOProduct daoPro = new DAOProduct(db);

        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();

            String service = request.getParameter("service");

            if (service == null) {
                service = "orderManager";
            }
            if (service.equals("orderManager")) {
                request.setAttribute("listAllBill", daoBill.getAllBill());
                // request.getRequestDispatcher("OrderManager.jsp").forward(request, response);
                request.getRequestDispatcher("ControllerMyAccount?service=info").forward(request, response);
            }
            if (service.equals("updateOrder")) {

                String oid = request.getParameter("oid");
                int status = Integer.valueOf(request.getParameter("stt"));
                daoBill.updateStatusByOid(status, oid);
                ArrayList<BillDetail> listDetail = daoDetail.getQuantityOrder(oid);
                for (BillDetail b : listDetail) {
                    String pid = b.getPid();
                    int quantityUpdate = daoPro.getQuantity(pid) - b.getQuantity();
                    daoPro.updateQuantity(new BillDetail(pid, quantityUpdate));
                    daoPro.updateStatusDisable();
                    // daoPro.updateStatusEnable();
                }
                request.getRequestDispatcher("ControllerAdmin?service=orderManager").forward(request, response);
            }
            if (service.equals("customerConfirm")) {
                String oid = request.getParameter("oid");
                int status = Integer.valueOf(request.getParameter("stt"));
                daoBill.updateStatusByOid(status, oid);
                request.getRequestDispatcher("ControllerMyAccount?service=info").forward(request, response);
            }
            if (service.equals("accountManager")) {
                out.print("accountManager");
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
