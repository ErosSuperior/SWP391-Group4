/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllerr;

import DAO.FeedbackDAO;
import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import model.feedback;

/**
 *
 * @author admin
 */
public class FeedBackDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet FeedbackDetailsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackDetailsServlet at " + request.getContextPath() + "</h1>");
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
      int feedbackId = Integer.parseInt(request.getParameter("feedback_id"));
     //  int feedbackId = 1;
       FeedbackDAO feedbackDAO = new FeedbackDAO();
       feedback feedback = feedbackDAO.getFeedbackByID(feedbackId);
       String name = "";
       if(feedback.getUser_id() != 0){
           name = new UserDAO().getUserById(feedback.getUser_id()).getUser_name();
       }
       name = feedback.getName();
       request.setAttribute("feedback", feedback);
       request.setAttribute("name", name);
       request.getRequestDispatcher("feedbackDetails.jsp").forward(request, response);
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
        int feedbackId = Integer.parseInt(request.getParameter("feedback_id"));
         FeedbackDAO feedbackDAO = new FeedbackDAO();
        if (request.getParameter("status") != null) {
            int status_raw = Integer.parseInt(request.getParameter("status").toString());
            boolean status = true;
           
            if (status_raw == 0) {
                status = false;
                feedbackDAO.setFeedbackStatusOn(feedbackId);
            }else{
                status = true;
                feedbackDAO.setFeedbackStatusOff(feedbackId);
            }
        }
        
          response.sendRedirect("/project_swp391/FeedbackManagerServlet");
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
