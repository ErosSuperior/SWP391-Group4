/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PostController;

import DAO.BlogsDAO;
import DAO.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.blog;
import model.category;

public class PostDetailController extends HttpServlet {

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
            out.println("<title>Servlet PostDetailController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostDetailController at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("blog_id"));
        CategoryDAO cateDAO = new CategoryDAO();
        List<category> cateList = cateDAO.getAllCategory();
        BlogsDAO blogsDAO = new BlogsDAO();
        blog blog = blogsDAO.getBlogsById(id);
//        System.out.println(blog.getCategory_id() + "==========");
        request.setAttribute("cc", blog.getCategory_id());
        request.setAttribute("blog", blog);
        request.setAttribute("cateList", cateList);
        request.getRequestDispatcher("PostDetail.jsp").forward(request, response);
    }



@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
        int id = 4;
        BlogsDAO blogsDAO = new BlogsDAO();
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        int category = Integer.parseInt(request.getParameter("category"));
        String bi = request.getParameter("bi");
        String detail = request.getParameter("detail");
         if (request.getParameter("status") != null) {
            int status_raw = Integer.parseInt(request.getParameter("status").toString());
            boolean status = true;
            if (status_raw == 0) {
                status = false;
            }
             System.out.println(status);
            String image = request.getParameter("image");
        blogsDAO.EditBlogById(id, title, bi, date, category, detail, image, status);
        response.sendRedirect("post-details");
        }
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
