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
@WebServlet(name = "ManagerBlogController", urlPatterns = {"/manager/managerlistBlog", "/manager/manageraddBlog", "/manager/managereditBlog", "/manager/managerupdatestatusBlog", "/manager/manageredits"})
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
        List<Blog> category = blogDao.getActiveCategory();
        List<Blog> author = blogDao.getAllAuthor();
        switch (url) {
            case "/manager/managerlistBlog":
                handleBlogList(request, response);
                break;
            case "/manager/manageraddBlog":
                request.setAttribute("category", category);
                request.setAttribute("author", author);
                request.getRequestDispatcher("/landing/manager/PostManagerDetail.jsp").forward(request, response);
                break;
            case "/manager/managereditBlog":
                String blog_id = request.getParameter("blog_id");
                String category_id = request.getParameter("selectedCategoryId");
                String title = request.getParameter("blog_title");
                String author_id = request.getParameter("selectedAuthor");
                String detail = request.getParameter("detail");
                String image = request.getParameter("image");
                request.setAttribute("blog_id", blog_id);
                request.setAttribute("selectedCategoryId", category_id);
                request.setAttribute("title", title);
                request.setAttribute("selectedAuthor", author_id);
                request.setAttribute("detail", detail);
                request.setAttribute("image", image);
                request.setAttribute("category", category);
                request.setAttribute("author", author);

                request.getRequestDispatcher("/landing/manager/PostManagerDetail.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/manager/managerupdatestatusBlog":
                updateStatus(request, response);
                break;
            case "/manager/manageredits":
                String submit = request.getParameter("submit");
                if (submit.equalsIgnoreCase("add")) {
                    addPost(request, response);
                    handleBlogList(request, response);
                } else {
                    editPost(request, response);
                    handleBlogList(request, response);
                }
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

    private void addPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Blog> category = blogDao.getActiveCategory();
        List<Blog> author = blogDao.getAllAuthor();
        String name = request.getParameter("name");
        String author_param = request.getParameter("author");
        String category_param = request.getParameter("category");
        String detail = request.getParameter("detail");
        String image = request.getParameter("image");
        if (name == null || name.trim().isEmpty()
                || author_param == null || author_param.trim().isEmpty()
                || category_param == null || category_param.trim().isEmpty()
                || detail == null || detail.trim().isEmpty()
                || image == null || image.trim().isEmpty()) {
            request.setAttribute("message", "All fields are required.");
            request.setAttribute("selectedCategoryId", category_param);
            request.setAttribute("title", name);
            request.setAttribute("selectedAuthor", author_param);
            request.setAttribute("detail", detail);
            request.setAttribute("image", image);
            request.setAttribute("category", category);
            request.setAttribute("author", author);
            request.getRequestDispatcher("/landing/manager/PostManagerDetail.jsp").forward(request, response);
        }
        try {
            int authorId = Integer.parseInt(author_param);
            int categoryId = Integer.parseInt(category_param);
            Blog blog = new Blog();
            blog.setBlogCategory(categoryId);
            blog.setBlogUserId(authorId);
            blog.setBlogTitle(name);
            blog.setBlogImage(image);
            blog.setBlogDetail(detail);
            blogDao.addBlog(blog);
        } catch (NumberFormatException e) {
        }
    }

    private void editPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> category = blogDao.getActiveCategory();
        List<Blog> author = blogDao.getAllAuthor();
        String id_param = request.getParameter("blog_id");
        String name = request.getParameter("name");
        String author_param = request.getParameter("author");
        String category_param = request.getParameter("category");
        String detail = request.getParameter("detail");
        String image = request.getParameter("image");
        if (id_param == null || id_param.trim().isEmpty()
                || name == null || name.trim().isEmpty()
                || author_param == null || author_param.trim().isEmpty()
                || category_param == null || category_param.trim().isEmpty()
                || detail == null || detail.trim().isEmpty()
                || image == null || image.trim().isEmpty() || author_param.equalsIgnoreCase("null") || category_param.equalsIgnoreCase("null")) {
            request.setAttribute("message", "All fields are required.");
            request.setAttribute("selectedCategoryId", category_param);
            request.setAttribute("blog_id", id_param);
            request.setAttribute("title", name);
            request.setAttribute("selectedAuthor", author_param);
            request.setAttribute("detail", detail);
            request.setAttribute("image", image);
            request.setAttribute("category", category);
            request.setAttribute("author", author);
            request.getRequestDispatcher("/landing/manager/PostManagerDetail.jsp").forward(request, response);
        }
        try {
            int id = Integer.parseInt(id_param);
            int authorId = Integer.parseInt(author_param);
            int categoryId = Integer.parseInt(category_param);
            Blog blog = new Blog();
            blog.setBlogId(id);
            blog.setBlogCategory(categoryId);
            blog.setBlogUserId(authorId);
            blog.setBlogTitle(name);
            blog.setBlogImage(image);
            blog.setBlogDetail(detail);
            blogDao.updateBlog(blog);

        } catch (NumberFormatException e) {
        }
    }
//    private void editPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String id_param = request.getParameter("blog_id");
//        String name = request.getParameter("name");
//        String author_param = request.getParameter("author");
//        String category_param = request.getParameter("category");
//        String detail = request.getParameter("detail");
//        String image = request.getParameter("image");
//
//        // Set attributes to verify in the JSP page
//        request.setAttribute("blog_id", id_param);
//        request.setAttribute("title", name);
//        request.setAttribute("selectedAuthor", author_param);
//        request.setAttribute("selectedCategoryId", category_param);
//        request.setAttribute("detail", detail);
//        request.setAttribute("image", image);
//
//        // Forward to a blank test JSP
//        request.getRequestDispatcher("/test.jsp").forward(request, response);
//    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
