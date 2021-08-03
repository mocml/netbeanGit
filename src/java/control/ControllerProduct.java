/*
 * Copyright(C) 2021 ,FPT UNIVERSITY
 * 
 * DATE        Version         Author          DESCRIPTION
 * 08/07/2021    1.0            Vanhv   
 */
package control;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAOCategory;
import model.DAOProduct;
import model.DBConnect;

/**
 *
 * @author vanhv
 */
public class ControllerProduct extends HttpServlet {

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
        DAOProduct dao = new DAOProduct(db);
        DAOCategory daoCate = new DAOCategory(db);
        String service = request.getParameter("service");

        try (PrintWriter out = response.getWriter()) {
            if (service == null) {
                service = "displayAll";

            }
            if (service.equals("displayAll")) {

                dao.updateStatusDisable();
              //  dao.updateStatusEnable();
                String cateId = request.getParameter("cate");
                if (cateId == null) {
                    ArrayList<Product> allProduct = dao.getAllProduct();
                    request.setAttribute("listCategory", daoCate.getAllCategory());
                    request.setAttribute("listProduct", allProduct);
                    request.getRequestDispatcher("Home.jsp").forward(request, response);
                } else {
                    ArrayList<Product> allProduct = dao.getProductByID(cateId);
                    request.setAttribute("listCategory", daoCate.getAllCategory());
                    request.setAttribute("listProduct", allProduct);
                    request.getRequestDispatcher("Home.jsp").forward(request, response);
                }
            }
            if (service.equals("productManager")) {

                dao.updateStatusDisable();
             //   dao.updateStatusEnable();
                String cateId = request.getParameter("cate");
                if (cateId == null) {
                    ArrayList<Product> allProduct = dao.getAllProductWithoutStatus();
                    request.setAttribute("listCategory", daoCate.getAllCategory());
                    request.setAttribute("listProduct", allProduct);
                    request.getRequestDispatcher("HomeAdmin.jsp").forward(request, response);
                } else {
                    ArrayList<Product> allProduct = dao.getProductByIdWithoutStatus(cateId);
                    request.setAttribute("listCategory", daoCate.getAllCategory());
                    request.setAttribute("listProduct", allProduct);
                    request.getRequestDispatcher("HomeAdmin.jsp").forward(request, response);
                }
            }
            if (service.equals("update")) {
                String pid = request.getParameter("pid");

                Product product = dao.getProduct(pid);
                request.setAttribute("note", "");
                request.setAttribute("product", product);
                request.setAttribute("listCate", daoCate.getAllCategory());
                request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
            }
            if (service.equals("updated")) {
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                int quantity = Integer.valueOf(request.getParameter("quantity"));
                Double price = Double.valueOf(request.getParameter("price"));
                String image = request.getParameter("image");
                String description = request.getParameter("description");
                int status = Integer.valueOf(request.getParameter("status"));
                int cateid = Integer.valueOf(request.getParameter("cateid"));
                Product product = new Product(pid, pname, quantity, price,
                        image, description, status, cateid);
                if (dao.updateProduct(product) > 0) {
                    String note = "Update Success !!";
                    request.setAttribute("note", note);
                    request.setAttribute("product", product);
                    request.setAttribute("listCate", daoCate.getAllCategory());
                    request.getRequestDispatcher("UpdateProduct.jsp").forward(request, response);
                }
            }
            if (service.equals("delete")) {
                String pid = request.getParameter("pid");
                if (dao.deleteProduct(pid) > 0) {
                    request.getRequestDispatcher("ControllerProduct?service=productManager").forward(request, response);
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
