/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

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
import model.Reservation;
import model.SearchResponse;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "StaffDashboardController", urlPatterns = {"/StaffDashboardController", "/scheduleslot", "/confirmservice"})
public class StaffDashboardController extends HttpServlet {

    ReservationDAO resDao = new ReservationDAO();
    ReservationInit resInit = new ReservationInit();
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
            out.println("<title>Servlet StaffDashboardController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffDashboardController at " + request.getContextPath() + "</h1>");
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
            case "/StaffDashboardController":
                handleListReservationStaff(request, response);
                break;
            case "/scheduleslot":
                handleScheduleSlot(request, response);
                break;
            case "/confirmservice":
                handleServiceCompleted(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private void handleListReservationStaff(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = (User) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }

        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;

        String reservationId = request.getParameter("nameOrId");
        SearchResponse<Reservation> searchResponse;
        try {
            int currentstaff = account.getUser_id();
            int countservice = resDao.getReservationCountHandledByStaff(currentstaff);
            searchResponse = resInit.getReservationDetailOnStaff(pageNo, pageSize, reservationId, currentstaff, "begin_time", "ASC");
            request.setAttribute("allblogs", searchResponse.getContent());
            request.setAttribute("totalElements", searchResponse.getTotalElements());
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("countservice", countservice);
            request.getRequestDispatcher("/landing/manager/StaffDashBoard.jsp").forward(request, response);
        } catch (Exception ex) {
        }
    }

    private void handleScheduleSlot(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = (User) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }
        String detail_idparam = request.getParameter("detail_id");
        String slotparam = request.getParameter("slot");

        try {
            int detail_id = Integer.parseInt(detail_idparam);
            int slot = Integer.parseInt(slotparam);
            String time;

            switch (slot) {
                case 1:
                    time = "8:00-10:00";
                    break;
                case 2:
                    time = "10:00-12:00";
                    break;
                case 3:
                    time = "12:00-14:00";
                    break;
                case 4:
                    time = "14:00-16:00";
                    break;
                case 5:
                    time = "16:00-18:00";
                    break;
                case 6:
                    time = "18:00-20:00";
                    break;
                default:
                    time = "Error (sorry for the disturbance)";
                    break;
            }

            boolean check = resDao.updateSlot(detail_id, slot);
            int reservation_id = resDao.getReservationIdByDetailId(detail_id);
            if (check) {
                SendEmail sm = new SendEmail();
                int curuser = resDao.getUserIdOnReservationId(reservation_id);
                User current = uDao.getUserDetail(curuser);
                String servicetitle = resDao.getServiceTitleByDetailId(detail_id);
                String text = "<div style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>"
                        + "<h2 style='color: #007bff;'>Service Confirmation</h2>"
                        + "<p>Hello <strong>" + current.getUser_fullname() + "</strong>,</p>"
                        + "<p>This is <strong>" + account.getUser_fullname() + "</strong>,</p>"
                        + "<p>Your reservation number <strong>" + reservation_id + "</strong>, service: "+servicetitle+" will be handle by me on slot:<strong> "+ slot +" : "+time+" </strong>on the day you've choosen. "
                        + "<p>Please arrange your time.</p>"
                        + "<p>Thank you for choosing us.</p>"
                        + "<hr style='border: 1px solid #007bff;'>"
                        + "<p style='font-size: 0.9em; color: #666;'>This is an automated message, please do not reply.</p>"
                        + "</div>";
                boolean emailSent = sm.sendEmail(current, text);
                if(emailSent){
                    handleListReservationStaff(request, response);
                }else{
                    request.getRequestDispatcher("landing/Error.jsp").forward(request, response);
                }
                
            } else {
                handleListReservationStaff(request, response);
            }
        } catch (NumberFormatException e) {
            handleListReservationStaff(request, response);
        }
    }

    private void handleServiceCompleted(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String detail_idparam = request.getParameter("detail_id");
        
        try{
            int detail_id = Integer.parseInt(detail_idparam);
            boolean check = resDao.updateChild(detail_id, 1);
            if(check){
                int reservation_id = resDao.getReservationIdByDetailId(detail_id);
                System.out.println(reservation_id);
                int countservicecompleted = resDao.countChildrenNotNull(reservation_id);
                System.out.println(countservicecompleted);
                if(countservicecompleted == 0){
                    resDao.updateReservationStatus(reservation_id, 3);
                    handleListReservationStaff(request, response);
                }else{
                    handleListReservationStaff(request, response);
                }
            }else{
                handleListReservationStaff(request, response);
            }
            handleListReservationStaff(request, response);
        }catch(NumberFormatException e){
            handleListReservationStaff(request, response);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private User checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null || account.getRole_id() != 3) {
            response.sendRedirect(request.getContextPath() + "/nav/error");
            return null;  // Stop further processing
        }

        return account;
    }
}
