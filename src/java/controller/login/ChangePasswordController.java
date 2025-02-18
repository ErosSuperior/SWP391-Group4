package controller.login;

import SendEmail.SendEmail;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;

/**
 *
 * @author win
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/resetredirect", "/resetpassword"})
public class ChangePasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String username = request.getParameter("username");
            String oldpassword = request.getParameter("oldpassword");
            String newpassword = request.getParameter("newpassword");
            String repeatnewpassword = request.getParameter("repeatpassword");

            UserDAO accountDao = new UserDAO();
            User account = accountDao.checkAccountExit(username);

            if (account == null) {
                request.setAttribute("c", "The account doesn't exist yet!");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else if (!oldpassword.equals(account.getUser_password())) {
                request.setAttribute("c", "Password not match!!!");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else if (newpassword.equals(oldpassword)) {
                request.setAttribute("c", "The new password must not be the same as the old password!");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else if (!newpassword.equals(repeatnewpassword)) {
                request.setAttribute("c", "The repeat new password must be the same as the new password!");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            } else {
                // Nếu mật khẩu thay đổi thành công, gọi method changePassword của UserDAO
                accountDao.changePassword(username, newpassword);
                // Đăng xuất người dùng nếu cần
                session.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response); // Hoặc có thể redirect về login page
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi nếu có
            request.setAttribute("c", "An error occurred while changing the password.");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/resetredirect":
                request.getRequestDispatcher("landing/forgot-password.jsp").forward(request, response);
                break;
            case "/resetpassword":
                String emailparam = request.getParameter("email");
                UserDAO accountDao = new UserDAO();
                User account = accountDao.checkAccountExit(emailparam);
                if (account == null) {
                    request.setAttribute("errorMessage", "Account doesn't exist");
                    request.getRequestDispatcher("landing/forgot-password.jsp").forward(request, response);
                }

                SendEmail sendemail = new SendEmail();
                // Generate a new password
                String newPassword = sendemail.passGenerate();

                // Update the password in the database
                accountDao.changePassword(emailparam, newPassword);

                // Send the new password via email
                String emailContent = "Hello " + account.getUser_fullname() + ",\n\n"
                        + "Your password has been reset. Your new password is:\n\n"
                        + newPassword + "\n\n"
                        + "Please log in and change your password immediately for security reasons.\n\n"
                        + "Best regards,\nYour Support Team";

                boolean emailSent = sendemail.sendEmail(account, emailContent);

                if (emailSent) {
                    request.getRequestDispatcher("landing/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Failed to send the email. Please try again.");
                    request.getRequestDispatcher("landing/forgot-password.jsp").forward(request, response);
                }
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
