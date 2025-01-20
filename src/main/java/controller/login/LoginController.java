/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.user;

/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // trường hợp đã lưu remember me thì lần tới đăng nhập nó sẽ không yêu cầu đăng nhập thêm
        // processRequest(request, response);
        // kiem tra cookie
        Cookie[] cookies = request.getCookies(); // lay cookies
        String user_email = null;
        String password = null;
        // lay usename va password do voa xem no co hop le khong
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("user_email")) { // neu bang thi luu vao usernam 
                    user_email = cooky.getValue();
                }
                if (cooky.getName().equals("password")) { // neu bang thi luu vao usernam 
                    password = cooky.getValue();
                }
                if (user_email != null && password != null) {
                    break;
                }
            }
        }

        // kiem tra da co trong trinh duyet, neu co thi mang di login
        // co the co trong trinh duyet nhung da doi mat khau
        if (user_email != null && password != null) {
            user account = new UserDAO().login(user_email, password);
            if (account != null) { // cookies hop le thi cho no dang nhap
                request.getSession().setAttribute("account", account);
            }
        }
        // con khong hop le thi cho ve trang login
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String user_email = request.getParameter("user_email");
        String password = request.getParameter("password");
        boolean remember = request.getParameter("remember") != null;

        //   string letter = "/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/";
        // check usename vaf password
        user account = new UserDAO().login(user_email, password);

//        HttpSession sessionaccount = request.getSession();
//        sessionaccount.setAttribute("user_email", user_email);
        if (account != null) {        // hop le => cho dang nhap va luu tren session => neu khac null thi dung
            if (remember) { // remenber => tao cookie va luu duoi trinh duyet nguoi dung
                Cookie user_emailCookie = new Cookie("user_email", user_email); // cookie chi luu duoi dang string
                user_emailCookie.setMaxAge(60 * 60 * 24);// giay gio ngay
                Cookie passwordCookie = new Cookie("password", password); // cookie chi luu duoi dang string
                passwordCookie.setMaxAge(60 * 60 * 24);// giay gio ngay
                response.addCookie(user_emailCookie);// luu vao may nguoi dung
                response.addCookie(passwordCookie);// luu vao may nguoi dung
            }
            //=============================
            //Authorization for Users
            if (account.getUser_role() != 1) {
                request.getSession().setAttribute("account", account); // luu len session
                response.sendRedirect("index");
            } else if (account.getUser_role() == 1) {
                request.getSession().setAttribute("admin", account); // luu len session
                response.sendRedirect("index");
            }
            //=================================
//        }//Auth for Admin 
//        else if (account.getRole_id() == 1) {
//            request.getSession().setAttribute("admin", account); // luu len session
//            response.sendRedirect("Admin");
//
//        }//Auth for Docter
//        else if (account.getRole_id() == 2) {
//            request.getSession().setAttribute("doctor", account); // luu len session
//            response.sendRedirect("staff");
//        } //Auth for Nurse
//        else if (account.getRole_id() == 3) {
//            request.getSession().setAttribute("nurse", account); // luu len session
//            response.sendRedirect("staff");
//        } //Auth for Customer
//        else if (account.getRole_id() == 4) {
//            request.getSession().setAttribute("nurse", account); // luu len session
//            response.sendRedirect("staff");
//        } //Auth for Users
//        else if (account.getRole_id() == 5) {
//            request.getSession().setAttribute("nurse", account); // luu len session
//            response.sendRedirect("Index");
        } else {         // khong hop le => tra ve loi
            request.setAttribute("error", "Username or password incorrect!. Please try again!");
            request.setAttribute("user_email", user_email);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
