/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllerr;

import DAO.ReservationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.user;


public class UserMedicalexammination extends HttpServlet {

   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          ReservationDAO reserDAO = new ReservationDAO();
        HttpSession session = request.getSession();
        int user_id = 0;
        if (session.getAttribute("account") != null || session.getAttribute("admin") != null) {
            user user = (user) session.getAttribute("admin");
            if (session.getAttribute("account") != null) {
                user = (user) session.getAttribute("account");
            }
        user_id = user.getUser_id();
        request.setAttribute("uid", user_id);
        request.getRequestDispatcher("User_Medical_Exammination.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("error.jsp");
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   

}
