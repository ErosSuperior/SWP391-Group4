/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.shop;

import DAO.ServicesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.service;

/**
 *
 * @author win
 */
public class LazyLoadingServices extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LazyLoadingServices</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LazyLoadingServices at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ServicesDAO serviceDAO = new ServicesDAO();
        request.setAttribute("path", "/lazyloadingservices");
        int amount = Integer.parseInt(request.getParameter("exist"));
        List<service> listService = serviceDAO.getNext3Services(amount);
        PrintWriter out = response.getWriter();
        String rateStar = "";
        String Star = "";
        for (service s : listService) {
            for (int i = 0; i < s.getService_rateStar(); i++) {
                rateStar += "<span class=\"fa fa-star checked\"></span>\n";
            }
            for (int i = 0; i < 5 - s.getService_rateStar(); i++) {
                Star += "<span class=\"fa fa-star\"></span>\n";
            }
            out.println("<div class=\"services row p-2 bg-white border rounded mt-2\">\n"
                    + "                                        <div class=\"col-md-3 mt-1\"><img class=\"img-fluid img-responsive rounded product-image\" src=\"./resouce/image/service/" + s.getImage().get(0).getImage_link() + "\"></div>\n"
                    + "                                        <div class=\"col-md-6 mt-1\">\n"
                    + "                                            <h5>" + s.getService_title() + "</h5>\n"
                    + "                                            <div class=\"d-flex flex-row\">\n"
                    + "                                                <div class=\"ratings mr-2\">\n"
                    + rateStar + Star
                    + "                                                </div>\n"
                    + "                                                <br>\n"
                    + "                                            </div>\n"
                    + "                                            <div>" + s.getService_vote() + " vote</div>\n"
                    + "                                            <div>\n"
                    + "                                                " + s.getServices_bi() + "\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                        <div class=\"align-items-center align-content-center col-md-3 border-left mt-1\">\n"
                    + "                                            <div class=\"d-flex flex-row align-items-center\">\n"
                    + "                                                <h4 class=\"mr-1\">" + s.getService_discount() + "</h4><span class=\"strike-text\">" + s.getService_price() + "</span>\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"d-flex flex-column mt-4\"><button onclick=\"window.location.href='/project_swp391/service-detail?service_id="+s.getService_id()+"'\" class=\"btn btn-primary btn-sm\" type=\"button\">Details</button><button onclick=\"addToCardAsync("+s.getService_id()+")\" class=\"btn btn-outline-primary btn-sm mt-2\" type=\"button\">Add to card</button></div>\n"
                    + "\n"
                    + "                                        </div>\n"
                    + "\n"
                    + "                                    </div>");
            rateStar = "";
            Star = "";
        }
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
