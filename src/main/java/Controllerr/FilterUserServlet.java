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
public class FilterUserServlet extends HttpServlet {
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO u = new UserDAO();
        RoleDAO r = new RoleDAO();
        HttpSession session = request.getSession();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<userRole> list_role = new RoleDAO().getAllRole();

        if (request.getParameter("txt") != null) {
            String search = request.getParameter("txt");
            List<user> list_user = u.getUserBySearch(search);
            request.setAttribute("list_user", list_user);
            request.setAttribute("list_role", list_role);
            request.setAttribute("txtS", request.getParameter("txt"));
            request.getRequestDispatcher("UserList.jsp").forward(request, response);
        }
        int[] role = null;
        int[] status = null;
        int[] gender = null;
        String sort = "id";
        String mess = "\norder by [user_id]";
        String[] role_raw = request.getParameterValues("role");
        String[] status_raw = request.getParameterValues("status");
        String[] gender_raw = request.getParameterValues("gender");
        role = (int[]) session.getAttribute("role");
        status = (int[]) session.getAttribute("status");
        gender = (int[]) session.getAttribute("gender");
        if(session.getAttribute("sort") != null){
            sort = (String) session.getAttribute("sort");
        }
        if(request.getParameter("sort") != null){
            sort = request.getParameter("sort");
        }
        System.out.println(sort+"=========================");
        if (role_raw != null) {
            role = new int[role_raw.length];
            for (int i = 0; i < role_raw.length; i++) {
                role[i] = Integer.parseInt(role_raw[i]);
            }
        }
        if (status_raw != null) {
            status = new int[status_raw.length];
            for (int i = 0; i < status_raw.length; i++) {
                status[i] = Integer.parseInt(status_raw[i]);
            }
        }
        if (gender_raw != null) {
            gender = new int[gender_raw.length];
            for (int i = 0; i < gender_raw.length; i++) {
                gender[i] = Integer.parseInt(gender_raw[i]);
            }
        }
        if(sort.equals("name")){
            mess = "\norder by [user_fullname]";
        }
        else if(sort.equals("gender")){
            mess = "\norder by [user_gender]";
        }
        else if(sort.equals("email")){
            mess = "\norder by [user_email]";
        }
        else if(sort.equals("mobile")){
            mess = "\norder by [user_phone]";
        }
        else if(sort.equals("role")){
            mess = "\norder by [role_id]";
        }
        else if(sort.equals("status")){
            mess = "\norder by [user_status]";
        }
        List<user> list_user = u.getUserByFilter(role, status, gender,mess, page);
        int maxPage = u.countTotalUserFilter(role) / 8;
        if (u.countTotalUserFilter(role) % 8 != 0 && 8*maxPage < u.countTotalUserFilter(role)) {
            maxPage += 1;
        }
        request.setAttribute("list_user", list_user);
        boolean[] role_check = new boolean[list_role.size()];
        boolean[] check = new boolean[4];
        if (status != null) {
            for (int i = 0; i < status.length; i++) {
                if (status[i] == 1) {
                    check[0] = true;
                }
                if (status[i] == 0) {
                    check[1] = true;
                }
            }
        }
        if (gender != null) {
            for (int i = 0; i < gender.length; i++) {
                if (gender[i] == 1) {
                    check[2] = true;
                }
                if (gender[i] == 0) {
                    check[3] = true;
                }
            }
        }

        for (int i = 0; i < list_role.size(); i++) {
            if (check_role(role, list_role.get(i).getRole_id())) {
                role_check[i] = true;
            } else {
                role_check[i] = false;
            }
        }
        session.setAttribute("role", role);
        session.setAttribute("status", status);
        session.setAttribute("gender", gender);
        session.setAttribute("sort", sort);
        request.setAttribute("path", "filter");
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("page", page);
        request.setAttribute("check", check);
        request.setAttribute("role_check", role_check);
        request.setAttribute("list_role", list_role);
        request.getRequestDispatcher("UserList.jsp").forward(request, response);
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

    private boolean check_role(int[] role, int role_id) {
        if (role == null) {
            return false;
        } else {
            for (int i = 0; i < role.length; i++) {
                if (role_id == role[i]) {
                    return true;
                }
            }
            return false;
        }
    }
}
