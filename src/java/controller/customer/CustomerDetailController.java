/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dao.CustomerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CustomerHistory;
import model.User;

@WebServlet(name = "CustomerDetailController", urlPatterns = {"/customerdetail"})
public class CustomerDetailController extends HttpServlet {

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
            out.println("<title>Servlet CustomerDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerDetailController at " + request.getContextPath() + "</h1>");
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
        // Lấy tham số user_id từ request (ID của khách hàng cần xem chi tiết)
        String user_id = request.getParameter("user_id");

// Khởi tạo đối tượng DAO để thao tác với dữ liệu khách hàng
        CustomerDAO d = new CustomerDAO();

// Lấy thông tin chi tiết của khách hàng dựa trên user_id
        User cus = d.getCustomerDetail(user_id);

// Lấy danh sách lịch sử giao dịch hoặc hoạt động của khách hàng
        List<CustomerHistory> listhistory = d.getCustomerHistoryByUserId(user_id);

// Đưa danh sách lịch sử và thông tin khách hàng vào request để truyền sang JSP
        request.setAttribute("listhistory", listhistory);
        request.setAttribute("cus", cus);

// Chuyển hướng sang trang quản lý chi tiết khách hàng
        request.getRequestDispatcher("landing/manager/CustomerDetailManager.jsp").forward(request, response);

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
        // Lấy thông tin từ request (các trường dữ liệu cần cập nhật)
        User account = (User) request.getSession().getAttribute("account");

        // Nếu chưa đăng nhập, chuyển hướng về trang chủ
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return; // Dừng việc thực hiện tiếp các lệnh bên dưới
        }

        // Khởi tạo đối tượng DAO để thao tác với cơ sở dữ liệu
        CustomerDAO d = new CustomerDAO();

        String name = request.getParameter("name");       // Tên khách hàng
        String gender = request.getParameter("gender");   // Giới tính
        String phone = request.getParameter("phone");     // Số điện thoại
        String address = request.getParameter("address"); // Địa chỉ
        String user_id = request.getParameter("user_id"); // ID khách hàng

        String status = request.getParameter("status");

        boolean checkstatus = false;
        boolean checkupdate = false;
        if (status != null && !status.isEmpty()) {
            checkstatus = d.updateStatusCustomer(user_id, status);
        } else {
            checkupdate = d.updateCustomer(user_id, name, gender, address, phone);
        }

       // Kiểm tra kết quả cập nhật, điều hướng trang phù hợp
        if (checkupdate || checkstatus) {
            response.sendRedirect("customerdetail?user_id=" + user_id); // Chuyển hướng về trang chi tiết khách hàng
        } else {
            response.sendRedirect("error"); // Chuyển hướng đến trang lỗi nếu cập nhật thất bại
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
