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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.User;

@WebServlet(name = "CustomerController", urlPatterns = {"/customercontroller"})
public class CustomerController extends HttpServlet {

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
            out.println("<title>Servlet CustomerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerController at " + request.getContextPath() + "</h1>");
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
        // Lấy các tham số từ request (người dùng nhập vào form hoặc từ URL)
        String search = request.getParameter("search"); // Tìm kiếm theo tên hoặc thông tin khách hàng
        String sort = request.getParameter("sort"); // Sắp xếp theo tiêu chí nào (VD: name, date)
        String by = request.getParameter("by"); // Sắp xếp theo thứ tự tăng dần hoặc giảm dần
        String status = request.getParameter("status"); // Lọc khách hàng theo trạng thái (VD: active, inactive)
        String pageParam = request.getParameter("page"); // Số trang hiện tại

// Khai báo biến phân trang
        int page = 1; // Mặc định trang đầu tiên
        int pageSize = 10; // Số khách hàng hiển thị trên mỗi trang

        try {
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam); // Chuyển đổi số trang từ chuỗi sang số nguyên
                if (page < 1) { // Đảm bảo số trang không nhỏ hơn 1
                    page = 1;
                }
            }
        } catch (NumberFormatException e) {
            page = 1; // Nếu có lỗi chuyển đổi số trang, đặt lại về trang 1
        }

// Tạo DAO để thao tác với cơ sở dữ liệu
        CustomerDAO d = new CustomerDAO();

// Lấy danh sách khách hàng từ database theo các tiêu chí tìm kiếm, sắp xếp, lọc và phân trang
        List<User> listcustomer = d.getAllCustomer(search, sort, by, status, page, pageSize);

// Đếm tổng số khách hàng thỏa mãn điều kiện tìm kiếm để tính số trang
        int totalCustomers = d.countCustomer(search, status);
        int totalPages = (int) Math.ceil((double) totalCustomers / pageSize); // Tính tổng số trang

// Kiểm tra nếu số trang hiện tại lớn hơn tổng số trang thì đặt về trang cuối cùng
        if (totalPages > 0) {
            if (page > totalPages) {
                page = totalPages;
            }
        } else {
            page = 1; // Nếu không có khách hàng nào thì đặt trang về 1
        }

// Gửi dữ liệu đến request để hiển thị trên giao diện
        request.setAttribute("page", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search);
        request.setAttribute("sort", sort);
        request.setAttribute("by", by);
        request.setAttribute("status", status);
        request.setAttribute("listcustomer", listcustomer);

// Xây dựng URL để hỗ trợ điều hướng trang với các tham số tìm kiếm và lọc
        StringBuilder url = new StringBuilder("customercontroller");
        List<String> params = new ArrayList<>();

        if (search != null && !search.isBlank()) {
            String encodedSearch = URLEncoder.encode(search, StandardCharsets.UTF_8); // Mã hóa chuỗi tìm kiếm để tránh lỗi URL
            params.add("search=" + encodedSearch);
        }

        if (sort != null && !sort.isEmpty()) {
            params.add("sort=" + sort);
        }

        if (by != null && !by.isEmpty()) {
            params.add("by=" + by);
        }

        if (status != null && !status.isBlank()) {
            params.add("status=" + status);
        }

        // Nếu có tham số trong danh sách, nối chúng vào URL
        if (!params.isEmpty()) {
            url.append("?").append(String.join("&", params));
        }

        // Gửi URL này đến giao diện để sử dụng trong phân trang hoặc điều hướng
        request.setAttribute("baseURL", url.toString());

        // Chuyển hướng đến trang quản lý khách hàng
        request.getRequestDispatcher("landing/manager/CustomerManager.jsp").forward(request, response);

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
        // Lấy tham số từ request để xử lý bộ lọc và sắp xếp
        String search = request.getParameter("search"); // Tìm kiếm theo từ khóa
        String sort = request.getParameter("sort"); // Sắp xếp theo tiêu chí (VD: tên, ngày tạo)
        String by = request.getParameter("by"); // Sắp xếp tăng dần hoặc giảm dần
        String status = request.getParameter("status"); // Trạng thái của khách hàng (VD: active, inactive)

// Tạo đối tượng StringBuilder để xây dựng URL điều hướng
        StringBuilder url = new StringBuilder("customercontroller");
        List<String> params = new ArrayList<>(); // Danh sách tham số URL

// Kiểm tra nếu có tham số search thì mã hóa và thêm vào danh sách tham số
        if (search != null && !search.isBlank()) {
            String encodedSearch = URLEncoder.encode(search, StandardCharsets.UTF_8); // Mã hóa để tránh lỗi URL
            params.add("search=" + encodedSearch);
        }

// Kiểm tra nếu có tham số sắp xếp, thêm vào danh sách tham số
        if (sort != null && !sort.isEmpty()) {
            params.add("sort=" + sort);
        }

// Kiểm tra nếu có tham số thứ tự sắp xếp (tăng dần, giảm dần)
        if (by != null && !by.isEmpty()) {
            params.add("by=" + by);
        }

// Kiểm tra nếu có tham số trạng thái, thêm vào danh sách tham số
        if (status != null && !status.isBlank()) {
            params.add("status=" + status);
        }

// Nếu có tham số nào trong danh sách, nối chúng vào URL
        if (!params.isEmpty()) {
            url.append("?").append(String.join("&", params)); // Ghép các tham số thành URL hợp lệ
        }

// Chuyển hướng người dùng đến trang customercontroller với các tham số đã lọc
        response.sendRedirect(url.toString());

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
