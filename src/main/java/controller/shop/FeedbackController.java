/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.shop;

import java.io.IOException;
import java.io.PrintWriter;
import model.feedback;
import DAO.FeedbackDAO;
import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.user;

/**
 *
 * @author admin
 */
public class FeedbackController extends HttpServlet {

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
            out.println("<title>Servlet FeedbackController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackController at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("user_id") != null) {
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            user user = (user) new UserDAO().getUserById(user_id);
            request.setAttribute("user", user);
            System.err.println("Userrrrrrrrrrrrrrrrrrrrrr:"+user==null);
        }
        if(request.getParameter("res_id") != null){
            request.setAttribute("res_id", request.getParameter("res_id"));
        }
        
        request.getRequestDispatcher("feedback.jsp").forward(request, response);
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
            int reservation_id = 0;
            int user_id = 0;
            if (request.getParameter("user_id") != null&&!request.getParameter("user_id").toString().equals("")) {
                user_id = Integer.parseInt(request.getParameter("user_id"));
                if (request.getParameter("res_id") != null&&!request.getParameter("res_id").toString().equals("")) {
                    reservation_id = Integer.parseInt(request.getParameter("res_id"));
                }
            }
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            int gender_raw = Integer.parseInt(request.getParameter("gender"));
            boolean gender = true;
            if (gender_raw == 0) {
                gender = false;
            }
            String phone = request.getParameter("phone");
            String note = request.getParameter("note");
            String star = request.getParameter("star");
            String image = request.getParameter("image");
            int rate = Integer.parseInt(request.getParameter("star"));
            feedback feedback = new feedback(reservation_id, user_id, note, name, gender, email, phone, image, rate, false);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            feedbackDAO.InsertNewFeedBack(feedback);
            response.sendRedirect("success.jsp");
//        } catch (Exception e) {
//            response.sendRedirect("error.jsp");
//        }
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
