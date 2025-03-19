/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.blog;

import dao.CustomerBlogDAO;
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
import model.Blog;
import model.Category;

@WebServlet(name = "BlogController", urlPatterns = {"/blogs"})
public class BlogController extends HttpServlet {

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
            out.println("<title>Servlet BlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogController at " + request.getContextPath() + "</h1>");
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
        // Lấy giá trị từ request của người dùng
        String search = request.getParameter("search"); // Lấy từ khóa tìm kiếm từ request
        String category_id = request.getParameter("category_id"); // Lấy ID danh mục từ request
        String pageParam = request.getParameter("page"); // Lấy số trang từ request

// Khởi tạo giá trị mặc định cho phân trang
        int page = 1; // Mặc định hiển thị trang đầu tiên
        int pageSize = 9; // Số lượng bài viết hiển thị trên mỗi trang

        try {
            // Kiểm tra nếu người dùng có truyền tham số trang (page)
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam); // Chuyển đổi pageParam sang số nguyên
                if (page < 1) {
                    page = 1; // Nếu page nhỏ hơn 1 thì đặt lại thành 1
                }
            }
        } catch (NumberFormatException e) {
            page = 1; // Nếu có lỗi chuyển đổi (ví dụ nhập chữ), thì mặc định là trang 1
        }

// Tạo đối tượng DAO để truy vấn dữ liệu từ cơ sở dữ liệu
        CustomerBlogDAO d = new CustomerBlogDAO();

// Lấy danh sách bài viết theo bộ lọc tìm kiếm và phân trang
        List<Blog> listblog = d.getCustomerBlog(search, category_id, page, pageSize);

// Lấy 3 bài viết mới nhất
        List<Blog> list3post = d.get3RecentPost();

// Lấy danh sách các danh mục
        List<Category> listcategory = d.getAllCategory();

// Tính tổng số bài viết theo điều kiện tìm kiếm
        int totalBlogs = d.countTotalBlogs(search, category_id);

// Tính tổng số trang dựa trên tổng số bài viết và số bài trên mỗi trang
        int totalPages = (int) Math.ceil((double) totalBlogs / pageSize);

// Kiểm tra nếu số trang vượt quá giới hạn thì đặt lại giá trị phù hợp
        if (totalPages > 0) {
            if (page > totalPages) {
                page = totalPages; // Nếu trang lớn hơn tổng số trang, đặt lại thành trang cuối
            }
        } else {
            page = 1; // Nếu không có bài viết nào, đặt trang mặc định là 1
        }

// Đưa dữ liệu cần thiết vào request để hiển thị trên trang JSP
        request.setAttribute("listcategory", listcategory); // Gửi danh sách danh mục
        request.setAttribute("list3post", list3post); // Gửi danh sách 3 bài viết mới nhất
        request.setAttribute("search", search); // Gửi giá trị tìm kiếm
        request.setAttribute("cate_id", category_id); // Gửi ID danh mục được chọn
        request.setAttribute("listblog", listblog); // Gửi danh sách bài viết theo tìm kiếm
        request.setAttribute("page", page); // Gửi số trang hiện tại
        request.setAttribute("totalPages", totalPages); // Gửi tổng số trang

// Xây dựng URL chứa tham số tìm kiếm để hỗ trợ điều hướng
        StringBuilder url = new StringBuilder("blogs");
        List<String> params = new ArrayList<>();

// Nếu có từ khóa tìm kiếm, mã hóa và thêm vào danh sách tham số
        if (search != null && !search.trim().isEmpty()) {
            String encodedSearch = URLEncoder.encode(search, StandardCharsets.UTF_8);
            params.add("search=" + encodedSearch);
        }

// Nếu có ID danh mục, thêm vào danh sách tham số
        if (category_id != null && !category_id.isEmpty()) {
            params.add("category_id=" + category_id);
        }

// Nếu có tham số, nối vào URL
        if (!params.isEmpty()) {
            url.append("?").append(String.join("&", params));
        }

// Đưa URL gốc vào request để sử dụng trên trang JSP
        request.setAttribute("baseURL", url.toString());

// Chuyển hướng request đến trang hiển thị Blog.jsp
        request.getRequestDispatcher("landing/customer/Blog.jsp").forward(request, response);

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
