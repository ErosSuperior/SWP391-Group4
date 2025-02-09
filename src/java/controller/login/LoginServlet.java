package controller;

import dao.UserDAO;
import model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Nếu người dùng đã đăng nhập (có thông tin trong session), chuyển hướng về trang index
        User account = (User) request.getSession().getAttribute("account");
        if (account != null) {
            response.sendRedirect("index.jsp");
            return;
        }
        // Nếu chưa đăng nhập, chuyển tới trang login.jsp
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin đăng nhập từ form (lưu ý: trường input có name="email" trong login.jsp)
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Gọi hàm xác thực đăng nhập
        User account = userDAO.login(email, password);

        if (account != null) {
            // Lưu thông tin người dùng vào session
            request.getSession().setAttribute("account", account);
            // Điều hướng đến trang index.jsp (có thể phân luồng nếu có nhiều vai trò)
            response.sendRedirect("index.jsp");
        } else {
            // Nếu đăng nhập không thành công, hiển thị thông báo lỗi
            request.setAttribute("error", "Username or password incorrect! Please try again.");
            // Giữ lại email đã nhập (nếu muốn hiển thị lại trên form)
            request.setAttribute("user_email", email);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet handles user authentication";
    }
}
