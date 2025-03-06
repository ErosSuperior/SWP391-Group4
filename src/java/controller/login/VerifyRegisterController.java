/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserRegister;
import model.User;

/**
 *
 * @author win
 */
@WebServlet(name = "VerifyRegisterController", urlPatterns = {"/verifyRegister"})
public class VerifyRegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            UserDAO userDao = new UserDAO();
            
            String formId = request.getParameter("formid");
            User existingUser = userDao.checkAccountExit((String) session.getAttribute("username" + formId));
            if (existingUser != null) {
                request.setAttribute("r", "Account already exists!");
                request.getRequestDispatcher("landing/signup.jsp").forward(request, response);
                return;
            }

            UserRegister userRegister = (UserRegister) session.getAttribute("authcode" + formId);
            String code1 = request.getParameter("authcode1");
            String code2 = request.getParameter("authcode2");
            String code3 = request.getParameter("authcode3");
            String code4 = request.getParameter("authcode4");
            String code5 = request.getParameter("authcode5");
            String code6 = request.getParameter("authcode6");
            String verificationCode = code1 + code2 + code3 + code4 + code5 + code6;

            String username = (String) session.getAttribute("username" + formId);
            Boolean gender = (Boolean) session.getAttribute("gender" + formId);
            String address = (String) session.getAttribute("address" + formId);
            String email = (String) session.getAttribute("useremail" + formId);
            String phone = (String) session.getAttribute("phone" + formId);
            String password = (String) session.getAttribute("password" + formId);

            if (verificationCode.equals(userRegister.getCode())) {
                // Pass valid role_id (e.g., 1 for regular users)
                userDao.register(username, true, address, password, email, phone, 4, true, phone);
                response.sendRedirect("landing/login.jsp");
            } else {
                request.setAttribute("error", "Incorrect verification code!");
                request.setAttribute("formid", formId);
                request.getRequestDispatcher("landing/verifyRegister.jsp").forward(request, response);
            }
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
        processRequest(request, response);
    }
}