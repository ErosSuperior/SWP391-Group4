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
import model.servicedetailmanager;

/**
 *
 * @author truon
 */
public class EditServiceDetailManager extends HttpServlet {

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
            out.println("<title>Servlet EditServiceDetailManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditServiceDetailManager at " + request.getContextPath() + "</h1>");
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
        String service_id = request.getParameter("service_id");

        // load service manager
        int sid = 0;
        if (service_id != null) {
            sid = Integer.parseInt(service_id);
            servicedetailmanager serdetail = new ServicesDAO().servicedetailmanagerbyID(sid);
            request.setAttribute("serdetail", serdetail);
            request.getRequestDispatcher("EditServiceManager.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("EditServiceManager.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        //edit service manager       
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        String detail = request.getParameter("detail");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String image = request.getParameter("image");
        String category = request.getParameter("category");
        String sid = request.getParameter("sid");

        // update service 
        if (price != null && discount != null && discount != null && sid != null) {

            // update service need
            float prices = Float.parseFloat(price);
            float discounts = Float.parseFloat(discount);
            int categoryidd = Integer.parseInt(category);
            int sidd = Integer.parseInt(sid);
            ServicesDAO servicesDAO = new ServicesDAO();
            try {
                if (discounts < prices) {
                    servicesDAO.editservicemanager(title, brief, detail, prices, discounts, categoryidd, image, sidd);
                    request.setAttribute("mess", "Update service manager successfull");
                    session.setAttribute("title", title);
                    session.setAttribute("brief", brief);
                    session.setAttribute("detail", detail);
                    session.setAttribute("prices", prices);
                    session.setAttribute("discounts", discounts);
                    session.setAttribute("categoryidd", categoryidd);
                    session.setAttribute("image", image);
                    session.setAttribute("sidd", sidd);
                    request.getRequestDispatcher("EditServiceManager.jsp").forward(request, response);

                } else {
                    request.setAttribute("mess", "Add new service manager failed! Check your intput");
                    session.setAttribute("title", title);
                    session.setAttribute("brief", brief);
                    session.setAttribute("detail", detail);
                    session.setAttribute("prices", prices);
                    session.setAttribute("discounts", discounts);
                    session.setAttribute("categoryidd", categoryidd);
                    session.setAttribute("image", image);
                    session.setAttribute("sidd", sidd);
                    request.getRequestDispatcher("EditServiceManager.jsp").forward(request, response);
                }

            } catch (Exception ex) {
                Logger.getLogger(AddNewServiceManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }

        request.getRequestDispatcher("EditServiceManager.jsp").forward(request, response);
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


