/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reservation;

import DAO.ReservationDAO;
import SendEmail.SendEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.MyReservation;
import model.user;

/**
 *
 * @author win
 */
public class SubmitReservationServlet extends HttpServlet {

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
            out.println("<title>Servlet SubmitReservationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitReservationServlet at " + request.getContextPath() + "</h1>");
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
        int status = 2;
        new ReservationDAO().changeStatus(res_id, status);
        ReservationDAO reserDAO = new ReservationDAO();
        HttpSession session = request.getSession();
        int user_id = 0;
        if (session.getAttribute("account") != null || session.getAttribute("admin") != null) {
            user user = (user) session.getAttribute("admin");
            if (session.getAttribute("account") != null) {
                user = (user) session.getAttribute("account");

            }
            user_id = user.getUser_id();
            List<MyReservation> reserList = reserDAO.getAllReservationByUserID(user_id);
            request.setAttribute("reserList", reserList);
            SendEmail sm = new SendEmail();
            sm.sendEmail(user, "Your booking schedule has been sent.\n"
                    + "The total amount for the whole service is :"+reserDAO.getReservationByID(res_id).getTotal_price()+"$.\n"
                    + "You can pay at the clinic after the services are completed.\n"
                    + "Thank you for trusting our clinic.");
            request.getRequestDispatcher("my_reservation.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
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
