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

@WebServlet(name="RegisterServlet", urlPatterns = {"/register"})
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
        // Lấy thông tin đăng ký từ form
        String username = request.getParameter("username");
        // Giả sử form gửi về "true" hoặc "false" cho giới tính (nếu dùng radio button, hãy chuyển đổi phù hợp)
        String genderParam = request.getParameter("gender");  
        boolean gender = Boolean.parseBoolean(genderParam);
        String address = request.getParameter("address");
        String useremail = request.getParameter("useremail");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String repeatpassword = request.getParameter("repeatpassword");

        // Khởi tạo đối tượng gửi email và lấy mã xác minh ngẫu nhiên (ví dụ 6 ký tự/digits)
        SendEmailRegister sm = new SendEmailRegister();
        String code = sm.getRandom();
        
        // Lưu các thông tin đăng ký vào session (sử dụng mã code làm "formid" để phân biệt nếu cần nhiều phiên đăng ký)
        HttpSession session = request.getSession();
        session.setAttribute("username" + code, username);
        session.setAttribute("gender" + code, gender);
        session.setAttribute("address" + code, address);
        session.setAttribute("useremail" + code, useremail);
        session.setAttribute("phone" + code, phone);
        session.setAttribute("password" + code, password);
        session.setAttribute("repeatpassword" + code, repeatpassword);


        UserDAO accountDao = new UserDAO();
        // Kiểm tra xem tài khoản (dựa theo email) đã tồn tại chưa
        User account = accountDao.checkAccountExit(useremail);
        if (account != null) {
            request.setAttribute("r", "Account already exists!");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        } else if (!password.equals(repeatpassword)) {
            request.setAttribute("r", "Password does not match repeat password!");
            request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
            return;
        } else {
            // Tạo đối tượng UserRegister với tên, email và mã xác minh
            UserRegister userReg = new UserRegister(username, useremail, code);
            String text = "Hello " + userReg.getName() + ". You have successfully registered. Your verification code is: " 
                    + userReg.getCode() + ". Please use this code to activate your account!";
            
            // Gửi email xác minh
            boolean emailSent = sm.sendEmail(userReg, text);
            if (emailSent) {
                // Lưu đối tượng xác minh vào session
                session.setAttribute("authcode" + code, userReg);
                // Gửi mã formid (code) sang trang xác minh
                request.setAttribute("formid", code);
                request.getRequestDispatcher("landing/verifyRegister.jsp").forward(request, response);
            } else {
                try (PrintWriter out = response.getWriter()) {
                    out.println("Failed to send verification email!");
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Register Servlet handles user registration and email verification";
    }
}
