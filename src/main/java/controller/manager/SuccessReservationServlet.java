/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import DAO.ReservationDAO;
import DAO.UserDAO;
import SendEmail.SendEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.MyReservation;
import model.reservation;
import model.user;

/**
 *
 * @author win
 */
public class SuccessReservationServlet extends HttpServlet {

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
            out.println("<title>Servlet SuccessReservationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SuccessReservationServlet at " + request.getContextPath() + "</h1>");
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
        int res_id = Integer.parseInt(request.getParameter("res_id"));
        new ReservationDAO().changeStatus(res_id, 4);
        ReservationDAO reserDAO = new ReservationDAO();
        List<MyReservation> reserList = reserDAO.getAllReservationSubmitted();
        reservation reservation = new ReservationDAO().getReservationByID(res_id);
        user user = new UserDAO().getUserById(reservation.getUser_id());
        SendEmail sm = new SendEmail();
            sm.sendEmail(user, "Thank you very much for using our child care center services.\n"
                    + "Any feedback you can send to: "
                    + "http://localhost:8080/project_swp391/feedback?res_id="+res_id+"&&user_id="+user.getUser_id());
        request.setAttribute("reserList", reserList);
        request.getRequestDispatcher("ReservationSubmitted.jsp").forward(request, response);
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
