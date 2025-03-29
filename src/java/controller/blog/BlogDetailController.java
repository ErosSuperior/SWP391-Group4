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
import java.util.List;
import model.Blog;
import model.Category;

@WebServlet(name = "BlogDetailController", urlPatterns = {"/blogdetail"})
public class BlogDetailController extends HttpServlet {

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
            out.println("<title>Servlet BlogDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogDetailController at " + request.getContextPath() + "</h1>");
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
// Lấy giá trị blogId từ request của người dùng
        String blogId = request.getParameter("blogId");

// Khởi tạo đối tượng DAO để truy vấn dữ liệu từ database
        CustomerBlogDAO d = new CustomerBlogDAO();

// Lấy thông tin chi tiết của bài viết dựa trên blogId
        Blog blogdetail = d.getBlogDetail(blogId);

// Lấy danh sách 5 bài viết gần đây (có thể là bài viết liên quan)
        List<Blog> relatedpost = d.get5RecentPost();

// Lấy danh sách 3 bài viết mới nhất
        List<Blog> list3post = d.get3RecentPost();

// Lấy danh sách các danh mục bài viết
        List<Category> listcategory = d.getActiveCategories();

// Đưa dữ liệu vào request để truyền sang trang JSP
        request.setAttribute("listcategory", listcategory); // Gửi danh sách danh mục
        request.setAttribute("list3post", list3post); // Gửi danh sách 3 bài viết mới nhất
        request.setAttribute("relatedpost", relatedpost); // Gửi danh sách 5 bài viết liên quan
        request.setAttribute("bl", blogdetail); // Gửi thông tin chi tiết bài viết

// Chuyển hướng request đến trang BlogDetail.jsp để hiển thị thông tin bài viết
        request.getRequestDispatcher("landing/customer/BlogDetail.jsp").forward(request, response);

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
