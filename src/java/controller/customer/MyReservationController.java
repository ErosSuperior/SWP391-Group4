/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.ReservationDAO;
import dao.ShopCartDAO;
import init.ReservationInit;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Reservation;
import model.ReservationDetail;
import model.SearchResponse;
import model.Service;
import model.User;

/**
 *
 * @author thang
 */
@WebServlet(name = "MyReservationController", urlPatterns = {"/myReservationController", "/customer/myreservationlist", "/customer/myreservationdetail", "/customer/myreservationinfo",
    "/reservation/reservationserviceedit", "/reservation/reservationserviceinfoedit", "/deleteReservation", "/checkoutReservationConfirmed"})
public class MyReservationController extends HttpServlet {

    ReservationDAO reservationDao = new ReservationDAO();
    ReservationInit reserInit = new ReservationInit();

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
            out.println("<title>Servlet MyReservationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyReservationController at " + request.getContextPath() + "</h1>");
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
        String url = request.getServletPath();
        switch (url) {
            case "/myReservationController":  // place holder for accessing dashboard
                request.getRequestDispatcher("landing/customer/MyReservation.jsp").forward(request, response);
                break;
            case "/customer/myreservationlist":
                handleListReservation(request, response);
                break;
            case "/customer/myreservationdetail":
                handleListReservationDetail(request, response);
                break;
            case "/customer/myreservationinfo":
                handleListReservationInfo(request, response);
                break;
            case "/checkoutReservationConfirmed":
                request.setAttribute("totalFinal", 5);
                request.setAttribute("formattedtotalFinal", 5);
                request.getRequestDispatcher("landing/vnPay.jsp").forward(request, response);

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
        String url = request.getServletPath();
        switch (url) {
            case "/reservation/reservationserviceedit":
                handleEditReservationDetail(request, response);
                break;
            case "/reservation/reservationserviceinfoedit":
                handleEditReservationDetailInfo(request, response);
                break;
            case "/deleteReservation":
                handleCancelReservation(request, response);
                break;
        }
    }

    public void handleListReservation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if user is logged in
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            // Redirect to login page if not logged in
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }

        String dayParam = request.getParameter("day");
        String monthParam = request.getParameter("month");
        String yearParam = request.getParameter("year");

        String sortDir = "ASC";
        String sortBy = "created_date";

        // Retrieve request parameters
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;
        String nameOrId = request.getParameter("nameOrId");

        int day = -1;
        int month = -1;
        int year = -1;

        if (dayParam != null && !dayParam.isEmpty()) {
            day = Integer.parseInt(dayParam);
        }
        if (monthParam != null && !monthParam.isEmpty()) {
            month = Integer.parseInt(monthParam);
        }
        if (yearParam != null && !yearParam.isEmpty()) {
            year = Integer.parseInt(yearParam);
        }
        
        String sortDirparam = request.getParameter("sortdir");
        if (sortDirparam != null && !sortDirparam.isEmpty()) {
            sortDir = sortDirparam;
        }

        String sortByparam = request.getParameter("sortvalue");
        if (sortByparam != null && !sortByparam.isEmpty()) {
            sortBy = sortByparam;
        }

        SearchResponse<Reservation> searchResponse = reserInit.getReservation(pageNo, pageSize, nameOrId, account.getUser_id(), day, month, year, sortBy, sortDir);
        // Set attributes for JSP
        request.setAttribute("reservations", searchResponse.getContent());
        request.setAttribute("selectedDay", day);
        request.setAttribute("selectedMonth", month);
        request.setAttribute("selectedYear", year);
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/customer/MyReservation.jsp").forward(request, response);

    }

    public void handleListReservationDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = (User) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        String fix = request.getParameter("fix");

        ShopCartDAO d = new ShopCartDAO();

        int reservationID = Integer.parseInt(request.getParameter("reservation_id"));
        int numofreservation = reservationDao.countServiceInReservation(reservationID);
        if (numofreservation == 0) {
            reservationDao.updateReservationStatus(reservationID, 4);
            handleListReservation(request, response);
            return;
        }
        List<ReservationDetail> listreservation = d.getReservationDetail(reservationID);

        request.setAttribute("fix", fix);
        request.setAttribute("numofreservation", numofreservation);
        request.setAttribute("listreservation", listreservation);
        request.setAttribute("reservation_id", reservationID);
        request.getRequestDispatcher("/landing/customer/ReservationInfo.jsp").forward(request, response);
    }

    public void handleListReservationInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = (User) request.getSession().getAttribute("account"); // Lấy session để kiểm tra xem đăng nhập chưa
        if (account == null) { // Nếu chưa đăng nhập
            response.sendRedirect(request.getContextPath() + "/home"); // Đẩy về trang home
            return; // Dừng thực hiện các lệnh tiếp theo
        }
        // Tiếp tục nếu đã đăng nhập

        String fix = request.getParameter("fix");
        int reservation_id = Integer.parseInt(request.getParameter("reservation_id"));

        String note = reservationDao.getReservationNote(reservation_id);
        ShopCartDAO d = new ShopCartDAO(); // Tạo đối tượng để sử dụng hàm của nó
        int totalservice = reservationDao.totalService(reservation_id); // Gọi hàm để tính tổng service khách chọn
        int payment_status = reservationDao.getPaymentStatus(reservation_id);
        if (totalservice <= 0) { // Nếu không có service
            handleListReservation(request, response); // trả về trang MyReservation
            return;
        }
        User u = reservationDao.getReceiverInfo(reservation_id);
        List<ReservationDetail> listreservation = d.getReservationDetail(reservation_id); // Lấy dánh sách service
        request.setAttribute("receiver", u);
        request.setAttribute("note", note);
        request.setAttribute("fix", fix);
        request.setAttribute("reservation_id", reservation_id);
        request.setAttribute("payment_status", payment_status);
        request.setAttribute("listreservation", listreservation); // Lưu vào resquest để đẩy lên JSP
        request.setAttribute("totalservice", totalservice); // Lưu tổng số service vào để đẩy lên jsp
        request.getRequestDispatcher("/landing/customer/ReservationInfoDetail.jsp").forward(request, response); //Tham chiếu tới trang Checkout.jsp
    }

    public void handleEditReservationDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service_id = request.getParameter("service_id"); // Lấy dữ liệu từ JSP gửi về 
        String quantity = request.getParameter("quantity"); // Lấy dữ liệu từ JSP gửi về 
        String reservation_detail_id = request.getParameter("detail_id");
        String delete_id = request.getParameter("delete_id"); // Lấy dữ liệu từ JSP gửi về 
        String reservation_id = request.getParameter("reservation_id");

        User account = (User) request.getSession().getAttribute("account");  // Lấy session để kiểm tra xem đăng nhập chưa

        if (delete_id != null && !delete_id.isEmpty()) { // Kiểm tra xem có delete_id gửi về hay k. Nếu có
            boolean nhom = reservationDao.deleteService(delete_id); // Gọi hàm xóa service khỏi giỏ hàng
            if (nhom) { //Xóa thành công
                handleListReservationDetail(request, response);
                return;
            } else {
                response.sendRedirect(request.getContextPath() + "/error"); // Xóa ko thành công chuyển tới trang lỗi
                return;
            }
        }

        boolean result = reservationDao.updateQuantity(quantity, service_id, reservation_detail_id); // Update số lượng

        if (result) { // Update thành công
            response.setContentType("application/json"); // Xác định loại thông báo
            response.getWriter().write("{\"status\":\"1\"}"); // Gửi thông báo về client
        } else {
            response.setContentType("application/json"); // Xác định loại thông báo 
            response.getWriter().write("{\"status\":\"0\"}"); // Gửi thông báo về client
        }
    }

    public void handleEditReservationDetailInfo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");
        String total = request.getParameter("total");
        String note = request.getParameter("note");
        String reservation_id = request.getParameter("reservation_id");

        reservationDao.updateReservation(reservation_id, note, address, phone, email, name, total);

        handleListReservation(request, response);
    }

    protected void handleCancelReservation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deleteId = request.getParameter("delete_id");
        ShopCartDAO d = new ShopCartDAO();
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            if (reservationDao.checkReservationStatus(Integer.parseInt(deleteId)) != 1) {
                // Send a JSON response indicating failure
                out.print("{\"success\": false, \"message\": \"The reservation is already confirmed and cannot be canceled.\"}");
                out.flush();
                return;
            }

            boolean isDeleted = d.deleteAllService(deleteId);
            int numOfReservation = reservationDao.countServiceInReservation(Integer.parseInt(deleteId));

            if (numOfReservation == 0) {
                reservationDao.updateReservationStatus(Integer.parseInt(deleteId), 4);
            }

            // Send a success response
            out.print("{\"success\": true}");
            out.flush();
        } catch (Exception e) {
            out.print("{\"success\": false, \"message\": \"An error occurred while processing the request.\"}");
            out.flush();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
