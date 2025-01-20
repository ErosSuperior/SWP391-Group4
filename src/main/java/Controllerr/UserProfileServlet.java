/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllerr;

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
public class UserProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String user_image = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProfileServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfileServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        user user = new user();

        if (session.getAttribute("account") == null&&session.getAttribute("admin") == null) {
            
                request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        if (session.getAttribute("account") != null) {
            user = (user) session.getAttribute("account");
        } else {
            user = (user) session.getAttribute("admin");
        }
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
        }
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        user user = (user) session.getAttribute("account");
        if (session.getAttribute("admin") != null) {
            user = (user) session.getAttribute("admin");
        }
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String image = request.getParameter("image");
        if (image.equals("")) {
            image = user.getUser_image();
        }
        if (new UserDAO().checkPhone(phone) == false) {
            String errPhone = "Phone number is Invalid!";
            request.setAttribute("errPhone", errPhone);
            request.setAttribute("user", user);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
        } else {
            int gender_raw = Integer.parseInt(request.getParameter("gender"));
            boolean gender = true;
            if (gender_raw == 0) {
                gender = false;
            }
            user edit = new user(user.getUser_id(), name, gender, address, user.getUser_password(), email, phone, user.getUser_role(), user.isUser_status(), image);
            new UserDAO().EditUser(edit);
            user userold = (user) session.getAttribute("account");
            if ((user) session.getAttribute("admin") != null) {
                userold = (user) session.getAttribute("admin");
            }
            user user_new = new UserDAO().getUserById(userold.getUser_id());
            session.removeAttribute("account");
            session.removeAttribute("admin");
            session.setAttribute("account", user_new);
            session.setAttribute("admin", user_new);
            request.setAttribute("user", user_new);
            request.getRequestDispatcher("edituser.jsp").forward(request, response);
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
