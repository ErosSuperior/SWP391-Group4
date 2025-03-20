/*
 * ReservationManagerController.java
 */
package controller.manager;

import init.ReservationInit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Reservation;
import model.SearchResponse;
import model.User;

@WebServlet(name = "ReservationManagerController", urlPatterns = {
    "/admin/reservationList",
    "/admin/reservationDetail"
})
public class ReservationManagerController extends HttpServlet {

    private ReservationInit reservationInit = new ReservationInit();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop processing if user is not logged in or not admin
        }

        String url = request.getServletPath();
        switch (url) {
            case "/admin/reservationList":
                try {
                    handleReservationList(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(ReservationManagerController.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing reservation list");
                }
                break;
            case "/admin/reservationDetail":
                handleReservationDetail(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private void handleReservationList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4; // Số lượng reservation trên mỗi trang
        String search = request.getParameter("search"); // Tìm kiếm theo reservation_id
        int status = -1; // Mặc định lấy tất cả trạng thái
        int paymentStatus = -1; // Mặc định lấy tất cả trạng thái thanh toán

        try {
            String statusParam = request.getParameter("status");
            if (statusParam != null && !statusParam.isEmpty()) {
                status = Integer.parseInt(statusParam);
            }
            String paymentStatusParam = request.getParameter("paymentStatus");
            if (paymentStatusParam != null && !paymentStatusParam.isEmpty()) {
                paymentStatus = Integer.parseInt(paymentStatusParam);
            }

            SearchResponse<Reservation> searchResponse = reservationInit.getAllReservation(pageNo, pageSize, search, status, paymentStatus);

            request.setAttribute("allReservations", searchResponse.getContent());
            request.setAttribute("totalElements", searchResponse.getTotalElements());
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("search", search);
            request.setAttribute("status", status);
            request.setAttribute("paymentStatus", paymentStatus);

            request.getRequestDispatcher("/landing/manager/ReservationManager.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            Logger.getLogger(ReservationManagerController.class.getName()).log(Level.SEVERE, "Invalid input", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number, status, or payment status");
        }
    }

    private void handleReservationDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reservationIdParam = request.getParameter("id");
        if (reservationIdParam != null && !reservationIdParam.trim().isEmpty()) {
            try {
                int reservationId = Integer.parseInt(reservationIdParam);
                Reservation reservation = reservationInit.getReservationById(reservationId);
                if (reservation != null) {
                    request.setAttribute("reservation", reservation);
                    request.getRequestDispatcher("/landing/manager/ReservationManagerDetail.jsp").forward(request, response);
                    return;
                } else {
                    response.sendRedirect(request.getContextPath() + "/admin/reservationList?error=ReservationNotFound");
                    return;
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(ReservationManagerController.class.getName()).log(Level.SEVERE, "Invalid reservation ID", e);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/reservationList");
    }

    private User checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null || account.getRole_id() != 2) { // Chỉ admin (role_id = 1) được truy cập
            response.sendRedirect(request.getContextPath() + "/nav/error");
            return null; // Stop further processing
        }
        return account;
    }
}