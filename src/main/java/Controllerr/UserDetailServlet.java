/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllerr;

import DAO.RoleDAO;
import DAO.UserDAO;
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
public class UserDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet UserDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserDetailServlet at " + request.getContextPath() + "</h1>");
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
            if(request.getParameter("user_id")==null || request.getParameter("user_id").toString().equals("")){
            response.sendRedirect((String)request.getSession().getAttribute("previousPath"));
            return;
            }
        int id = Integer.parseInt(request.getParameter("user_id"));
        String func = request.getParameter("function");
        user user = new UserDAO().getUserById(id);
        List<userRole> list_role = new RoleDAO().getAllRole();
        String role = "";
        for (int i = 0; i < list_role.size(); i++) {
            if (user.getUser_role() == list_role.get(i).getRole_id()) {
                role = list_role.get(i).getRole_name();
            }
        }
        if (user != null) {
            request.setAttribute("user", user);
            request.setAttribute("role", role);
            request.setAttribute("list_role", list_role);
        }
        request.setAttribute("user_id", id);
        if(func.equals("edit")){
            request.getRequestDispatcher("userDetail.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("ViewUser.jsp").forward(request, response);
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
        
        if(request.getParameter("user_id")==null || request.getParameter("user_id").toString().equals("")){
            response.sendRedirect((String)request.getSession().getAttribute("previousPath"));
            return;
        }
        int id = Integer.parseInt(request.getParameter("user_id"));
        user user = new UserDAO().getUserById(id);
        List<userRole> list_role = new RoleDAO().getAllRole();
        if (request.getParameter("role")!=null) {
            int role_raw = Integer.parseInt(request.getParameter("role").toString());
            user.setUser_role(role_raw);
        }
        if (request.getParameter("status") != null) {
            int status_raw = Integer.parseInt(request.getParameter("status").toString());
            boolean status = true;
            if (status_raw == 0) {
                status = false;
            }
            user.setUser_status(status);
        }
        String role = "";
        for (int i = 0; i < list_role.size(); i++) {
            if (user.getUser_role() == list_role.get(i).getRole_id()) {
                role = list_role.get(i).getRole_name();
            }
        }
        new UserDAO().EditUser(user);
        request.setAttribute("user_id", id);
        request.setAttribute("user", user);
        request.setAttribute("list_role", list_role);
        request.setAttribute("role", role);
        request.getRequestDispatcher("userDetail.jsp").forward(request, response);
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
