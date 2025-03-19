/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.FeedbackDAO;
import dao.ReservationDAO;
import init.FeedbackInit;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Feedback;
import model.SearchResponse;
import model.Service;
import model.User;

/**
 *
 * @author thang
 */
@WebServlet(name = "FeedbackController", urlPatterns = {"/manager/feedbackList", "/manager/updatefeedbackstatus"})
public class FeedbackController extends HttpServlet {

    FeedbackDAO feedbackDao = new FeedbackDAO();
    FeedbackInit feedbackInit = new FeedbackInit();
    ReservationDAO reservationDao = new ReservationDAO();

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
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        String url = request.getServletPath();
        switch (url) {
            case "/manager/feedbackList":
                handleListFeedback(request, response);
                break;

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
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        String url = request.getServletPath();
        switch (url) {
            case "/manager/updatefeedbackstatus":
                updateStatus(request, response);
                break;

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    protected void handleListFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;
        String nameOrId = request.getParameter("nameOrId");
        int serviceId = -1;
        int status = -1;
        int rateStar = -1;
        String sortDir = "ASC";
        String sortBy = "feedback_id";

        try {
            String statusparam = request.getParameter("status");
            if (statusparam != null && !statusparam.trim().isEmpty()) {
                status = Integer.parseInt(statusparam);
            }

            String ratestarparam = request.getParameter("ratestar");
            if (ratestarparam != null && !ratestarparam.trim().isEmpty()) {
                rateStar = Integer.parseInt(ratestarparam);
            }

            String serviceIdparam = request.getParameter("serviceId");
            if (serviceIdparam != null && !serviceIdparam.trim().isEmpty()) {
                serviceId = Integer.parseInt(serviceIdparam);
            }

            String sortDirparam = request.getParameter("sortdir");
            if (sortDirparam != null && !sortDirparam.isEmpty()){
                sortDir = sortDirparam;
            }

            String sortByparam = request.getParameter("sortvalue");
            if (sortByparam != null && !sortByparam.isEmpty()){
                sortBy = sortByparam;
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid parameter: " + e.getMessage());
        }

        List<Service> serviceinfo = reservationDao.getAllServiceInfo();
        SearchResponse<Feedback> searchResponse = feedbackInit.getAllServiceFeedback(pageNo, pageSize, nameOrId, serviceId, status, rateStar, sortBy, sortDir);

        request.setAttribute("allblogs", searchResponse.getContent());
        request.setAttribute("serviceinfo", serviceinfo);
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/manager/FeedbackManager.jsp").forward(request, response);
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        String userIdParam = request.getParameter("feedbackId");
        int userId = Integer.parseInt(userIdParam);
        String statusParam = request.getParameter("status");
        int status = Integer.parseInt(statusParam);
        feedbackDao.updateFeedbackStatus(userId, status);

    }

    private User checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null || account.getRole_id() != 2) {
            response.sendRedirect(request.getContextPath() + "/nav/error");
            return null;  // Stop further processing
        }

        return account;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
