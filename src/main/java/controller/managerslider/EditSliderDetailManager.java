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
import java.util.logging.Level;
import java.util.logging.Logger;

import model.sliderdetailmanager;
import servicelist.manager.AddNewServiceManager;

/**
 *
 * @author truon
 */
public class EditSliderDetailManager extends HttpServlet {

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
            out.println("<title>Servlet EditSliderDetailManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditSliderDetailManager at " + request.getContextPath() + "</h1>");
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

        String slider_id = request.getParameter("slider_id");
        int sid = 0;
        if (slider_id != null) {
            sid = Integer.parseInt(slider_id);
            sliderdetailmanager serdetail = new SliderDAO().sliderdetailmanagerbyID(sid);
            request.setAttribute("serdetail", serdetail);
            request.getRequestDispatcher("EditSliderDetailManager.jsp").forward(request, response);
        }

        request.getRequestDispatcher("EditSliderDetailManager.jsp").forward(request, response);
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

        String title = request.getParameter("title");;
        String image = request.getParameter("image");
        String notes = request.getParameter("notes");
        String sid = request.getParameter("sid");

        if (sid != null) {

            // update slider list
            int sidd = Integer.parseInt(sid);
            SliderDAO sliderDAO = new SliderDAO();
            try {
                sliderDAO.editsliderDetailmanager(title, image, notes, sidd);
                session.setAttribute("title", title);
                session.setAttribute("image", image);
                session.setAttribute("notes", notes);
                session.setAttribute("sidd", sidd);

                request.setAttribute("mess", "Update slider manager successfull");
                request.getRequestDispatcher("EditSliderDetailManager.jsp").forward(request, response);
            } catch (Exception ex) {
                Logger.getLogger(AddNewServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("mess", "Add new slider manager failed! Check your intput");
            request.getRequestDispatcher("EditSliderDetailManager.jsp").forward(request, response);
        }
        request.getRequestDispatcher("EditSliderDetailManager.jsp").forward(request, response);

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
