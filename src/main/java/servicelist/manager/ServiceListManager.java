/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelist.manager;

import DAO.CategoryDAO;
import DAO.ServicesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.category;
import model.listservicemanager;

/**
 *
 * @author truon
 */
public class ServiceListManager extends HttpServlet {

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
            out.println("<title>Servlet ServiceListManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceListManager at " + request.getContextPath() + "</h1>");
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
        final int PAGE_SIZE = 6;
        //      List<listservicemanager> listser = new ServicesDAO().listservicemanager();
        // filter service
        List<category> listcatery = new CategoryDAO().getAllCategory();
        HttpSession session = request.getSession();
        session.setAttribute("listcatery", listcatery);

        // Phan trang
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        ServicesDAO servicesDAO = new ServicesDAO();
        List<listservicemanager> listser = new ServicesDAO().getServiceWithPagging(page, PAGE_SIZE);
        int totalService = servicesDAO.getTotalService();
        int totalPage = totalService / PAGE_SIZE;
        if (totalService % PAGE_SIZE != 0) {
            totalPage += 1;
        }

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listser", listser);
        request.getRequestDispatcher("ServiceListManager.jsp").forward(request, response);

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

        try {
            String id = request.getParameter("id");
            String status = request.getParameter("status");

            if (id != null && status != null && !id.equals("")) {
                int idd = Integer.parseInt(id);
                int statuss = Integer.parseInt(status);
                //    boolean st = Boolean.parseBoolean(request.getParameter("status"));
                        ServicesDAO servicesDAO = new ServicesDAO();
                if (statuss == 0) {
                    servicesDAO.setStatus(1, idd);
                } else {
                    servicesDAO.setStatus(0, idd);
                }
               // response.sendRedirect("servicelistmanager");
            }
           // request.getRequestDispatcher("ServiceListManager.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
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
