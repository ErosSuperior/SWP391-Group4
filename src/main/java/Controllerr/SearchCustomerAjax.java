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
public class SearchCustomerAjax extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        UserDAO userDAO = new UserDAO();
        RoleDAO roleDAO = new RoleDAO();
        List<userRole> list_role = roleDAO.getAllRole();
        String search_index = request.getParameter("txt");
        
        List<user> listUser = userDAO.getCustomerBySearch(search_index);
        
        String role = "";
        String status = "";
        String gender = "";
        String color = "";
        PrintWriter out = response.getWriter();
        for (user u : listUser) {
            for(int i = 0;i<list_role.size();i++){
            if(list_role.get(i).getRole_id() == u.getUser_role()){
                role = list_role.get(i).getRole_name();
            }
        }
            if(!u.isUser_gender()){
                gender = "src=\"./assets/img/female.jpg";
            }
            else{
                gender = "src=\"./assets/img/male.jpg";
            }
            if(!u.isUser_status()){
                status = "Not activated";
                color = "color: red";
            }
            else{
                status = "Is activated";
                color = "color: green";
            }
            out.println("<div class=\"col-sm-6 mb-3\">\n"
                    + "                                        <div class=\"card\">\n"
                    + "                                            <div class=\"card-body\">\n"
                    + "                                                <h5 class=\"card-title\">" + u.getUser_id() + "\n"
                    + "                                                            <img width=\"30px\" "+gender+"\">\n"
                    + "                                                </h5>\n"
                    + "                                                <p class=\"card-text\">" + u.getUser_name() + "</p>\n"
                    + "                                                <p class=\"card-text\">" + u.getUser_email() + "</p>\n"
                    + "                                                <p class=\"card-text\">" + u.getUser_phone() + "</p>\n"
                                        + "                                                        <p class=\"card-text\">"+role+"</p>\n"
                    + "                                                        <p style=\""+ color+"\" class=\"card-text\">"+status+"</p> \n"
                    + "                                                <c:set var=\"func1\" value=\"view\" />\n"
                    + "                                                <c:set var=\"func2\" value=\"edit\" />\n"
                    + "                                                <a href=\"user_detail?user_id=" + u.getUser_id() + "&function=${func1}\" class=\"btn btn-primary\">View User</a>\n"
                    + "                                                <a href=\"user_detail?user_id=" + u.getUser_id() + "&function=${func2}\" class=\"btn btn-success\">Edit User</a>\n"
                    + "                                            </div>\n"
                    + "\n"
                    + "                                        </div>\n"
                    + "                                    </div>");
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
        processRequest(request, response);
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
