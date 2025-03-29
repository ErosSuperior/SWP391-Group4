package controller.login;

import dao.UserDAO;
import model.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/loginnavigation"})
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();

        switch (url) {
            case "/loginnavigation":
                request.getRequestDispatcher("landing/login.jsp").forward(request, response);
                break;
            case "/login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                // Gọi hàm xác thực đăng nhập
                User account = userDAO.login(email, password);

                if (account != null) {
                    int roleStatus = userDAO.checkRoleStatus(account.getUser_id());
                    if (roleStatus == 0) {
                        request.setAttribute("error", "Your role has been disabled. Contact admin for support.");
                        request.setAttribute("user_email", email);
                        request.getRequestDispatcher("landing/login.jsp").forward(request, response);
                        return;
                    }
                    if (account.isUser_status()) {
                        // Lưu thông tin người dùng vào session
                        request.getSession().setAttribute("account", account);
                        response.sendRedirect(request.getContextPath() + "/home");
                    } else {
                        // Tài khoản bị ban
                        request.setAttribute("error", "Your account has been banned!");
                        request.setAttribute("user_email", email);
                        request.getRequestDispatcher("landing/login.jsp").forward(request, response);
                    }
                } else {
                    // Sai thông tin đăng nhập
                    request.setAttribute("error", "Email or password incorrect! Please try again.");
                    request.setAttribute("user_email", email);
                    request.getRequestDispatcher("landing/login.jsp").forward(request, response);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = (User) request.getSession().getAttribute("account");
        if (account != null) {
            response.sendRedirect("index.jsp");
            return;
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet handles user authentication";
    }
}
