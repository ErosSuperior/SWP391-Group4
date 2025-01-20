/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicelist.manager;

import DAO.ServicesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author truon
 */
public class AddNewServiceManager extends HttpServlet {

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
            out.println("<title>Servlet AddNewServiceManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewServiceManager at " + request.getContextPath() + "</h1>");
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


//        request.getRequestDispatcher("AddNewServiceManager.jsp").forward(request, response);
         response.sendRedirect("AddNewServiceManager.jsp");

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
               HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String detail = request.getParameter("detail");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String image = request.getParameter("image");
        String category = request.getParameter("category");

        if (price != null && discount != null && discount != null) {
            float prices = Float.parseFloat(price);
            float discounts = Float.parseFloat(discount);
            int categoryidd = Integer.parseInt(category);
            ServicesDAO servicesDAO = new ServicesDAO();
            try {
                if (discounts < prices) {
                    servicesDAO.adnewservice(title, brief, detail, prices, discounts, categoryidd, image);
                    request.setAttribute("mess", "Add new service manager successfull");
                    session.setAttribute("title", title);
                    session.setAttribute("brief", brief);
                    session.setAttribute("detail", detail);
                    session.setAttribute("prices", prices);
                    session.setAttribute("discounts", discounts);
                    session.setAttribute("image", image);
                    request.getRequestDispatcher("AddNewServiceManager.jsp").forward(request, response);
                    return;
                } else {
                    request.setAttribute("mess", "Add new service manager fail. Please check your input!");
                    request.getRequestDispatcher("AddNewServiceManager.jsp").forward(request, response);
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(AddNewServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("mess", "Add new service manager failed! Check your intput");
            request.getRequestDispatcher("AddNewServiceManager.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("AddNewServiceManager.jsp").forward(request, response);
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


