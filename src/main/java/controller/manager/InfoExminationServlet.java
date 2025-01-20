/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import DAO.CategoryDAO;
import DAO.ReservationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.InformationReservation;
import model.InformationUserReservation;
import model.ListChildrenReservation;
import model.category;

/**
 *
 * @author win
 */
public class InfoExminationServlet extends HttpServlet {

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
            out.println("<title>Servlet AddExminationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddExminationServlet at " + request.getContextPath() + "</h1>");
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
        CategoryDAO cateDAO = new CategoryDAO();
        List<category> listCate = cateDAO.getAllCategory();

        String rid = request.getParameter("res_id");
        if (rid != null) {
            int res_id = Integer.parseInt(rid);
            InformationUserReservation informationUserReservation = new ReservationDAO().getInformationUserByReserId(res_id);
            // thong tin reservation cua reservation_id
            InformationReservation informationReservation = new ReservationDAO().getInformationByReservationId(res_id);
            // list service cua reservaion_id day
            List<ListChildrenReservation> listServiceOfReservation = new ReservationDAO().getListServiceOfReservationChild(res_id);
            request.setAttribute("informationUserReservation", informationUserReservation);
            request.setAttribute("informationReservation", informationReservation);
            request.setAttribute("listServiceOfReservation", listServiceOfReservation);
        }
        request.setAttribute("listCate", listCate);

        // thong tin nguoi dung
        request.getRequestDispatcher("AddExamination.jsp").forward(request, response);
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
