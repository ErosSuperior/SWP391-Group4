/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package DashBoardController;

import dao.DashboardDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ReservationStatistics;
import model.CategoryRevenue;
import model.FeedbackStatistics;
import model.ReservationStatistic;
import model.UserFull;

@WebServlet(name = "AdminDashboard", urlPatterns = {"/admindashboard"})
public class AdminDashboard extends HttpServlet {

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
            out.println("<title>Servlet AdminDashboard</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDashboard at " + request.getContextPath() + "</h1>");
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

        DashboardDAO d = new DashboardDAO();
        String selectedYearRaw = request.getParameter("selectedYear");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        int selectedYear;

        if (selectedYearRaw == null || selectedYearRaw.isEmpty()) {
            selectedYear = 0; // Không có năm được chọn, dùng năm hiện tại
        } else {
            try {
                selectedYear = Integer.parseInt(selectedYearRaw); // Chuyển chuỗi thành số
            } catch (NumberFormatException e) {
                selectedYear = 0; // Nếu có lỗi khi chuyển đổi, dùng năm mặc định
            }
        }

        // Nếu người dùng chưa chọn năm, mặc định lấy năm hiện tại
        if (selectedYear == 0) {
            selectedYear = LocalDate.now().getYear();
        }

// Lấy năm hiện tại
        int currentYear = LocalDate.now().getYear();
        List<Integer> years = new ArrayList<>();

// Tạo danh sách các năm từ năm hiện tại đến 3 năm trước
        for (int year = currentYear; year >= currentYear - 3; year--) {
            years.add(year);
        }

// Lấy thống kê đặt chỗ theo năm được chọn
        List<ReservationStatistics> statistics = d.getReservationStatistics(selectedYear);

// Gửi danh sách năm, năm hiện tại, năm đã chọn và thống kê đặt chỗ đến request
        request.setAttribute("years", years);
        request.setAttribute("currentYear", currentYear);
        request.setAttribute("selectedYear", selectedYear);
        request.setAttribute("statistics", statistics);

// Lấy doanh thu theo danh mục trong khoảng thời gian startDate - endDate
        List<CategoryRevenue> revenuebycategory = d.getRevenueByCategory(startDate, endDate);
        request.setAttribute("revenuebycategory", revenuebycategory);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

// Lấy danh sách khách hàng mới trong khoảng thời gian startDate - endDate
        List<UserFull> newlycustomer = d.getNewlyCustomer(startDate, endDate);

// Đếm số lượng khách hàng đăng ký mới trong khoảng thời gian startDate - endDate
        int countnewlycustomer = d.getNewlyRegisteredCustomerCount(startDate, endDate);
        request.setAttribute("newlycustomer", newlycustomer);
        request.setAttribute("countnewlycustomer", countnewlycustomer);

// Lấy xếp hạng đánh giá trung bình trong khoảng thời gian startDate - endDate
        double avgrateStar = d.getAverageRating(startDate, endDate);

// Lấy danh sách thống kê phản hồi từ khách hàng
        List<FeedbackStatistics> listfb = d.getFeedbackStatistics(startDate, endDate);
        request.setAttribute("listfb", listfb);
        request.setAttribute("avgrateStar", avgrateStar);

// Lấy thống kê đặt chỗ trong khoảng thời gian startDate - endDate
        ReservationStatistic statistic = d.getReservationStatistics(startDate, endDate);
        request.setAttribute("statistic", statistic);

// Chuyển hướng đến trang dashboard của admin
        request.getRequestDispatcher("/landing/AdminDashboard.jsp").forward(request, response);
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
