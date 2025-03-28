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
import java.util.UUID;

@WebServlet(name = "CustomerActionController", urlPatterns = {"/customeraction"})
public class CustomerActionController extends HttpServlet {

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
            out.println("<title>Servlet CustomerActionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerActionController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("landing/manager/AddCustomerManager.jsp").forward(request, response);
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
        // Lấy thông tin từ request do người dùng gửi lên
        String name = request.getParameter("name"); // Tên khách hàng
        String service = request.getParameter("service"); // Dịch vụ (thêm hoặc xóa khách hàng)
        String gender = request.getParameter("gender"); // Giới tính
        String address = request.getParameter("address"); // Địa chỉ
        String email = request.getParameter("email"); // Email
        String phone = request.getParameter("phone"); // Số điện thoại

// Khởi tạo đối tượng DAO để thao tác với cơ sở dữ liệu
        CustomerDAO d = new CustomerDAO();

// Kiểm tra loại dịch vụ mà người dùng yêu cầu
        if (service.equalsIgnoreCase("add")) { // Nếu yêu cầu là "add" (thêm khách hàng)
            String image = "assets/images/avatars/3108.jpg"; // Ảnh mặc định cho khách hàng mới
            String password = UUID.randomUUID().toString().substring(0, 9); // Tạo mật khẩu ngẫu nhiên 9 ký tự

            // Kiểm tra xem email có tồn tại trong hệ thống không
            boolean checkmail = d.isEmailExists(email);

            // Gửi lại dữ liệu vào request để giữ thông tin nhập trước đó
            request.setAttribute("name", name);
            request.setAttribute("gender", gender);
            request.setAttribute("address", address);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);

            if (checkmail) {
                // Nếu email đã tồn tại, hiển thị thông báo lỗi và quay về trang thêm khách hàng
                request.setAttribute("mess", "1"); // "1" có thể hiểu là trạng thái lỗi
                request.getRequestDispatcher("landing/manager/AddCustomerManager.jsp").forward(request, response);
            } else {
                // Nếu email chưa tồn tại, thực hiện thêm khách hàng vào cơ sở dữ liệu
                Boolean check = d.addCustomer(name, gender, address, password, email, phone, image);
                if (check) {
                    // Nếu thêm thành công, gửi thông báo thành công về trang thêm khách hàng
                    request.setAttribute("mess", "0"); // "0" có thể hiểu là trạng thái thành công
                    request.getRequestDispatcher("landing/manager/AddCustomerManager.jsp").forward(request, response);
                } else {
                    // Nếu thêm thất bại, chuyển hướng đến trang lỗi
                    response.sendRedirect("error");
                }
            }
        } else if (service.equalsIgnoreCase("del")) { // Nếu yêu cầu là "del" (xóa khách hàng)
            String user_id = request.getParameter("userId"); // Lấy user_id của khách hàng cần xóa
            String status = request.getParameter("status");
            
            // Thực hiện xóa khách hàng
            boolean checkdel = d.updateStatusCustomer(user_id,status);

            if (checkdel) {
                // Nếu xóa thành công, chuyển hướng về trang danh sách khách hàng
                response.sendRedirect("customercontroller");
            } else {
                // Nếu xóa thất bại, chuyển hướng đến trang lỗi
                response.sendRedirect("error");
            }
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
