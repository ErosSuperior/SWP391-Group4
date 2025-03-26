package controller.login;

import dao.UserDAO;
import SendEmail.SendEmailRegister;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import model.User;
import model.UserRegister;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Hiển thị trang đăng ký
        request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String username = request.getParameter("username");
        String genderParam = request.getParameter("gender");
        boolean gender = (genderParam != null) && Boolean.parseBoolean(genderParam);
        String address = request.getParameter("address");
        String useremail = request.getParameter("useremail");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String repeatpassword = request.getParameter("repeatpassword");

        // Định dạng regex
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String phoneRegex = "^[0-9]{10,11}$";
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";

        // Kiểm tra dữ liệu hợp lệ
        if (!Pattern.matches(emailRegex, useremail)) {
            request.setAttribute("r", "❌ Invalid email format! Please use a valid email address (example: user@example.com)");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        }

        if (username.length() < 3) {
            request.setAttribute("r", "❌ Full name must be at least 3 characters! Please enter your full name.");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        }

        if (address.length() < 5) {
            request.setAttribute("r", "❌ Address must be at least 5 characters! Please provide a complete address.");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        }

        if (!Pattern.matches(phoneRegex, phone)) {
            request.setAttribute("r", "❌ Phone number must be 10-11 digits! Please enter a valid phone number without spaces or special characters.");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        }

        if (!Pattern.matches(passwordRegex, password)) {
            request.setAttribute("r", "❌ Password must be at least 6 characters and contain both letters and numbers!\n" +
                              "Example: Password123");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        }

        if (!password.equals(repeatpassword)) {
            request.setAttribute("r", "❌ Passwords do not match! Please make sure both password fields are identical.");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        }

        // Kiểm tra email đã tồn tại chưa
        UserDAO accountDao = new UserDAO();
        User account = accountDao.checkAccountExit(useremail);
        if (account != null) {
            request.setAttribute("r", "❌ Account already exists! An account with this email address is already registered.\n" +
                              "Please try logging in or use a different email address.");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        }

        // Nếu hợp lệ, tiếp tục gửi email xác nhận
        SendEmailRegister sm = new SendEmailRegister();
        String code = sm.getRandom();
        HttpSession session = request.getSession();
        session.setAttribute("username" + code, username);
        session.setAttribute("gender" + code, gender);
        session.setAttribute("address" + code, address);
        session.setAttribute("useremail" + code, useremail);
        session.setAttribute("phone" + code, phone);
        session.setAttribute("password" + code, password);
        session.setAttribute("repeatpassword" + code, repeatpassword);

        UserRegister userReg = new UserRegister(username, useremail, code);
        String text = "Hello " + userReg.getName() + ", your verification code is: " + userReg.getCode();

        boolean emailSent = sm.sendEmail(userReg, text);
        if (emailSent) {
            session.setAttribute("authcode" + code, userReg);
            request.setAttribute("formid", code);
            request.getRequestDispatcher("landing/verifyRegister.jsp").forward(request, response);
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("Failed to send verification email!");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Register Servlet handles user registration and email verification";
    }
}
