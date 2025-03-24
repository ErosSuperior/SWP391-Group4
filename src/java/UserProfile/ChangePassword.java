/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package UserProfile;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utl.HashPassword;

@WebServlet(name = "ChangePassword", urlPatterns = {"/changepassword"})
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        User account = (User) request.getSession().getAttribute("account"); // Lấy session để kiểm tra xem đăng nhập chưa
        if (account == null) { // Nếu chưa đăng nhập
            response.sendRedirect(request.getContextPath() + "/loginnavigation"); // Đẩy về trang login
            return; // Dừng thực hiện các lệnh tiếp theo
        }

// Lấy mật khẩu mới và mật khẩu cũ từ request
        String newpass = request.getParameter("newpass");
        String oldpass = request.getParameter("oldpass");

// Mã hóa mật khẩu mới trước khi lưu vào cơ sở dữ liệu
        String hashedpass = HashPassword.hashPassword(newpass);

// Khởi tạo đối tượng UserDAO để thao tác với cơ sở dữ liệu
        UserDAO d = new UserDAO();

// Lấy thông tin chi tiết của người dùng hiện tại dựa trên user_id từ account
        User user = d.getUserDetail(account.getUser_id());

// Gửi thông tin người dùng đến request để hiển thị trên giao diện nếu cần
        request.setAttribute("user", user);

// Kiểm tra và cập nhật mật khẩu mới vào cơ sở dữ liệu
// So sánh mật khẩu cũ đã nhập (sau khi hash) với mật khẩu trong cơ sở dữ liệu
        boolean check = d.updatePassword(hashedpass, account.getUser_id(), HashPassword.hashPassword(oldpass));

// Nếu cập nhật không thành công (mật khẩu cũ không đúng hoặc lỗi khác)
        if (!check) {
            response.sendRedirect("userprofile?status=404"); // Chuyển hướng với mã lỗi 404
        } else {
            User usernew = d.getUserDetail(account.getUser_id());
            request.getSession().setAttribute("account", usernew);
            response.sendRedirect("userprofile?status=001"); // Chuyển hướng với mã thành công 001
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
