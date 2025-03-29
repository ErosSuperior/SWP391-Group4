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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = (User) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }

        String newpass = request.getParameter("newpass");
        String oldpass = request.getParameter("oldpass");

        // Thêm validation cho mật khẩu mới
        if (newpass == null || newpass.length() <= 6 || newpass.contains(" ")) {
            request.getSession().setAttribute("passmess", "New password must be more than 6 characters and contain no spaces!");
            response.sendRedirect("userprofile?status=404");
            return;
        }

        String hashedpass = HashPassword.hashPassword(newpass);
        UserDAO d = new UserDAO();
        User user = d.getUserDetail(account.getUser_id());
        request.setAttribute("user", user);

        boolean check = d.updatePassword(hashedpass, account.getUser_id(), HashPassword.hashPassword(oldpass));

        if (!check) {
            response.sendRedirect("userprofile?status=404");
        } else {
            User usernew = d.getUserDetail(account.getUser_id());
            request.getSession().setAttribute("account", usernew);
            response.sendRedirect("userprofile?status=001");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}