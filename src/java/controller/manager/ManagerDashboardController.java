/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import SendEmail.SendEmailRegister;
import SendEmail.SendEmail;
import dao.ReservationDAO;
import dao.UserDAO;
import init.ReservationInit;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Reservation;
import model.SearchResponse;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ManagerDashboardController", urlPatterns = {"/ManagerDashboardController", "/dashboardReservationStatuschange"})
public class ManagerDashboardController extends HttpServlet {

    ReservationInit resInit = new ReservationInit();
    ReservationDAO resDao = new ReservationDAO();
    UserDAO uDao = new UserDAO();

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
            out.println("<title>Servlet ManagerDashboardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerDashboardController at " + request.getContextPath() + "</h1>");
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
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        String url = request.getServletPath();
        switch (url) {
            case "/ManagerDashboardController":
                try {
                handleManagerDashboard(request, response);
            } catch (Exception ex) {
                Logger.getLogger(ManagerDashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case "/dashboardReservationStatuschange": {
                try {
                    handleChangeStatusReservation(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(ManagerDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

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
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        String url = request.getServletPath();
        switch (url) {
            case "/dashboardReservationStatuschange": {
                try {
                    handleChangeStatusReservation(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(ManagerDashboardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

        }
    }

    private void handleManagerDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;
        int netRevenue = resDao.calculateNetRevenue();
        int staffCount = uDao.countUser("", 3, -1);
        int numReservation = resDao.countAllReservations("", 3, 1);
        int numPending = resDao.countAllReservations("", 2, 0);
        int numCanceled = resDao.countAllReservations("", 4, -1);
        String reservationId = request.getParameter("nameOrId");
        SearchResponse<Reservation> searchResponse;
        try {

            searchResponse = resInit.getAllReservation(pageNo, pageSize, reservationId, 1, 0);
            request.setAttribute("allblogs", searchResponse.getContent());
            request.setAttribute("totalElements", searchResponse.getTotalElements());
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("netrevenue", netRevenue);
            request.setAttribute("numberofres", numReservation);
            request.setAttribute("pendingcount", numPending);
            request.setAttribute("cancelcount", numCanceled);
            request.setAttribute("staffcount", staffCount);

            request.getRequestDispatcher("/landing/manager/ManagerDashboard.jsp").forward(request, response);
        } catch (Exception ex) {
        }
    }

    private void handleChangeStatusReservation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String reservationId = request.getParameter("resId");
        String newStatus = request.getParameter("resStatus");
        System.out.println(reservationId);
        System.out.println(newStatus);
        try {
            int oldStatus = resDao.checkReservationStatus(Integer.parseInt(reservationId));
            if (oldStatus == 1) {
                SendEmail sm = new SendEmail();
                int curuser = resDao.getUserIdOnReservationId(Integer.parseInt(reservationId));
                User current = uDao.getUserDetail(curuser);
                String text = "<div style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>"
                        + "<h2 style='color: #007bff;'>Reservation Confirmation</h2>"
                        + "<p>Hello <strong>" + current.getUser_fullname() + "</strong>,</p>"
                        + "<p>Your reservation number <strong>" + reservationId + "</strong> has been confirmed. "
                        + "Please continue with your payment to finalize the process.</p>"
                        + "<p>Thank you for choosing us.</p>"
                        + "<hr style='border: 1px solid #007bff;'>"
                        + "<p style='font-size: 0.9em; color: #666;'>This is an automated message, please do not reply.</p>"
                        + "</div>";

                boolean emailSent = sm.sendEmail(current, text);
                resDao.updateReservationStatus(Integer.parseInt(reservationId), Integer.parseInt(newStatus));

                handleManagerDashboard(request, response);
            } else {
                handleManagerDashboard(request, response);
                response.getWriter().write("notchanged");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("error");
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

    private User checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null || account.getRole_id() != 2) {
            response.sendRedirect(request.getContextPath() + "/nav/error");
            return null;  // Stop further processing
        }

        return account;
    }
    
}
