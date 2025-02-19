package controller.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogOutController extends HttpServlet {

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
        // Xóa session
        request.getSession().removeAttribute("account");
        request.getSession().removeAttribute("admin");

        // Xóa cookies "user_email" và "password"
        Cookie[] cookies = request.getCookies(); // Lấy tất cả cookies
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("user_email") || cooky.getName().equals("password")) {
                    // Thiết lập max age của cookie về 0 để xóa
                    cooky.setMaxAge(0);
                    response.addCookie(cooky); // Thêm cookie vào response để xóa nó khỏi trình duyệt
                }
            }
        }

        // Chuyển hướng về trang chủ hoặc trang login sau khi đăng xuất
        response.sendRedirect(request.getContextPath() + "/loginnavigation"); // Thay đổi đường dẫn theo yêu cầu của bạn
    }

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "LogOutController - Handles user logout logic";
    }
}
