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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.user;
import model.userRole;

/**
 *
 * @author win
 */
public class UserListServlet extends HttpServlet {

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
            out.println("<title>Servlet UserListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserListServlet at " + request.getContextPath() + "</h1>");
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
        user user = (user) request.getSession().getAttribute("admin");
        if (user == null){
            PrintWriter out = response.getWriter();
            out.println("Access denied");
            
        }else{
        UserDAO u = new UserDAO();
        int page = 1;
        HttpSession session = request.getSession();
        session.removeAttribute("role");
        session.removeAttribute("sort");
        session.removeAttribute("status");
        session.removeAttribute("gender");
        List<userRole> list_role = new RoleDAO().getAllRole();
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        int maxPage = u.countTotalUser()/8;
        if(maxPage % u.countTotalUser() != 0){
            maxPage += 1;
        }
        List<user> list_user = u.listUserWithPagging(page);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("list_role", list_role);
        request.setAttribute("list_user", list_user);
        request.setAttribute("path", "home");
        request.getRequestDispatcher("UserList.jsp").forward(request, response);
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
