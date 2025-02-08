/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blog;
import model.SearchResponse;
import dao.BlogDAO;
import dao.BlogInit;
import java.util.List;
/**
 *
 * @author thang
 */
@WebServlet(name = "ManagerBlogController", urlPatterns = {"/manager/managerlistBlog", "/manager/manageraddBlog", "/manager/managereditBlog", "/manager/managerupdatestatusBlog"})
public class ManagerBlogController extends HttpServlet {

    BlogDAO blogDao = new BlogDAO();
    BlogInit blogInit = new BlogInit();
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
            out.println("<title>Servlet ManagerBlogController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerBlogController at " + request.getContextPath() + "</h1>");
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
        String url = request.getServletPath();
        switch (url) {
            case "/manager/managerlistBlog":
                handleBlogList(request, response);
                break;
            case "/manager/manageraddBlog":
                break;
            case "/manager/managereditBlog":
                break;
            case "/manager/managerupdatestatusBlog":
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/manager/managerupdatestatusBlog":
                updateStatus(request,response);
                break;
        }
    }
    
    private void handleBlogList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;
        String nameOrId = request.getParameter("nameOrId");
        int categoryId = -1; // Default value
        int status = -1;
        try {
            String categoryIdParam = request.getParameter("categoryId");
            if (categoryIdParam != null && !categoryIdParam.trim().isEmpty()) {
                categoryId = Integer.parseInt(categoryIdParam);
            }
            String statusParam = request.getParameter("status");
            status = (statusParam != null && !statusParam.isEmpty()) ? Integer.parseInt(statusParam) : -1;
        } catch (NumberFormatException e) {
            System.err.println("Invalid categoryId: " + e.getMessage());
        }
        SearchResponse<Blog> searchResponse = blogInit.getBlog(pageNo, pageSize, nameOrId, categoryId, status);
        
        request.setAttribute("allblogs", searchResponse.getContent());
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/manager/PostManager.jsp").forward(request, response);
    }
    
    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userIdParam = request.getParameter("blogId");
        int userId = Integer.parseInt(userIdParam);
        String statusParam = request.getParameter("blogStatus");
        int status = Integer.parseInt(statusParam);
        blogDao.updateStatus(userId, status);

    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
