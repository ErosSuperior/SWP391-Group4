/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.service;

import DAO.CategoryDAO;
import DAO.ServicesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.category;
import model.service;

/**
 *
 * @author truon
 */
public class ServiceDetailController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String service_id = request.getParameter("service_id");
        
        System.out.println(service_id +"-----------------------------");
        int service_idd = 0;
        if (service_id != null) {
            service_idd = Integer.parseInt(service_id);
        }
        service services = new ServicesDAO().getServiceById(service_idd);
        CategoryDAO cateDAO = new CategoryDAO();
        List<category> listCate = cateDAO.getAllCategory();
        request.setAttribute("listCate", listCate);
   //     List<comment_servicedetail> comment = new CommentDAO().getAllComment();
//        List<comment_load> listloadcomment = new CommentDAO().getAllLoadComment();
        
       // request.setAttribute("comment", comment);
//        request.setAttribute("listloadcomment", listloadcomment);
        request.setAttribute("service", services);
        request.getRequestDispatcher("service_detail.jsp").forward(request, response);
    }

    //    service services = new ServicesDAO().getServiceById(2);
    // List<service> service = new ArrayList<>();
    //   CategoryDAO categoryDAO = new CategoryDAO();
    // List<category> listService = categoryDAO.getAllCategory();
    //   request.setAttribute("listService", listService);
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
