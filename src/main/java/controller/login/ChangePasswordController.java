/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import model.user;

/**
 *
 * @author win
 */
public class ChangePasswordController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            HttpSession sessionchangepass = request.getSession();
// cmt abv
            String username = request.getParameter("username");
            String oldpassword = request.getParameter("oldpassword");
            String newpassword = request.getParameter("newpassword");
            String repeatnewpassword = request.getParameter("repeatpassword");

            UserDAO accountDao = new UserDAO();
            user account = accountDao.checkAccountExit(username);
//
//            if (account == null && newpassword == oldpassword) {
//                response.sendRedirect("login");
//            } else {
//                accountDao.changePasword(username, newpassword);
//                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
//
//            }

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
                accountDao.changePasword(username, newpassword);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
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
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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
        return "Short description";
    }// </editor-fold>

}
