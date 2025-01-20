/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.managerslider;

import DAO.SliderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.category;
import model.listslidermanager;

/**
 *
 * @author truon
 */
public class ListSliderManager extends HttpServlet {

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
            out.println("<title>Servlet ListSliderManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListSliderManager at " + request.getContextPath() + "</h1>");
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
        final int PAGE_SIZE = 5;
        //      List<listservicemanager> listser = new ServicesDAO().listservicemanager();

        // category
        List<category> listCategories = new SliderDAO().getAllCategories();
        HttpSession session = request.getSession();
        session.setAttribute("listCategories", listCategories);

        // Phan trang
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }
        SliderDAO servicesDAO = new SliderDAO();
        List<listslidermanager> listser = new SliderDAO().getSliderWithPagging(page, PAGE_SIZE);
        int totalSlider = SliderDAO.getTotalSlider();
        int totalPage = totalSlider / PAGE_SIZE;
        if (totalSlider % PAGE_SIZE != 0) {
            totalPage += 1;
        }

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listser", listser);
        request.getRequestDispatcher("ListSliderManager.jsp").forward(request, response);

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
         try {
            String id = request.getParameter("id");
            String status = request.getParameter("status");

            if (id != null && status != null && !id.equals("")) {
                int idd = Integer.parseInt(id);
                int statuss = Integer.parseInt(status);
                //    boolean st = Boolean.parseBoolean(request.getParameter("status"));
                        SliderDAO sliderDAO = new SliderDAO();
                if (statuss == 0) {
                    sliderDAO.setStatus(1, idd);
                } else {
                    sliderDAO.setStatus(0, idd);
                }
              //  response.sendRedirect("listslidermanager");
            }
            //request.getRequestDispatcher("ListSliderManager.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
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
