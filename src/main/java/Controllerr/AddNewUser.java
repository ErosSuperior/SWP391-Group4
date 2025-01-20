/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllerr;

import DAO.RoleDAO;
import DAO.UserDAO;
import SendEmail.SendEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.user;
import model.userRole;

/**
 *
 * @author win
 */
public class AddNewUser extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewUser at " + request.getContextPath() + "</h1>");
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
        user user1 = (user) request.getSession().getAttribute("admin");
        if (user1 == null){
            PrintWriter out = response.getWriter();
            out.println("Access denied");
        }else{
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String gender_raw = request.getParameter("gender");
        String image = request.getParameter("image");
        int role = Integer.parseInt(request.getParameter("role"));
        String status_raw = request.getParameter("status");
        boolean gender = true;
        boolean status = true;
        if (gender_raw == "0") {
            gender = false;
        }
        if (status_raw == "0") {
            gender = false;
        }
        UserDAO u =new UserDAO();
        List<user> list_email = new UserDAO().getAllUser();
        if (checkEmailExit(list_email, email) == true) {
            String errEmail = "Email already exists !";
            request.setAttribute("errEmail", errEmail);
            if (u.checkPhone(phone) == false) {
                String errPhone = "Phone number is Invalid!";
                request.setAttribute("errPhone", errPhone);
            }
            request.getRequestDispatcher("addNewUser.jsp").forward(request, response);
        } else if (u.checkPhone(phone) == false) {
            String errPhone = "Phone number is not correct !";
            request.setAttribute("errPhone", errPhone);
            request.getRequestDispatcher("addNewUser.jsp").forward(request, response);
        } else {
            List<userRole> list_role = new RoleDAO().getAllRole();
            user user = new user(role, name, gender, address, password, email, phone, role, status, image);
            new UserDAO().addNewUser(user);
            String role1 = "";
            for (int i = 0; i < list_role.size(); i++) {
                if (user.getUser_role() == list_role.get(i).getRole_id()) {
                    role1 = list_role.get(i).getRole_name();
                }
            }
            SendEmail sm = new SendEmail();
            String text = "Don't send your password for every one!!!\nYour password is: "+user.getUser_password();
            sm.sendEmail(user,text);
            request.setAttribute("user", user);
            request.setAttribute("list_role", list_role);
            request.setAttribute("role", role1);
            request.setAttribute("block", true);
            request.getRequestDispatcher("userDetail.jsp").forward(request, response);
        }
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

    private boolean checkEmailExit(List<user> list_email, String email) {
        for (int i = 0; i < list_email.size(); i++) {
            if (list_email.get(i).getUser_email().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
