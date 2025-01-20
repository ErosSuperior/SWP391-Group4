/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.shop;

import DAO.AdminDAO;
import DAO.BlogsDAO;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.ServicesDAO;
import DAO.SliderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.SliderHomeServices;
import model.blog;
import model.category;
import model.feedback;
import model.hotservices;
import model.service;
import model.setting;

/**
 *
 * @author admin
 */
public class IndexController extends HttpServlet {

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
            out.println("<title>Servlet IndexController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndexController at " + request.getContextPath() + "</h1>");
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
        //1. Slider
        ServicesDAO servicesDAO = new ServicesDAO();
        //slider dao
        List<SliderHomeServices> sliderList = new SliderDAO().getAllSliderHomePage();
        request.setAttribute("sliderList", sliderList);
////        //2. Services Category
        CategoryDAO categoryDAO = new CategoryDAO();
        List<category> categoryList = categoryDAO.getAllCategory();
        request.setAttribute("categoryList", categoryList);
////        //3. All Service
////       
        List<service> serviceList = servicesDAO.getAllServices();
        request.setAttribute("serviceList", serviceList);
//        //Fix services
        List<List<service>> list = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            List<service> service = servicesDAO.getServicesByCategoryIDforIndex(i + 1);
            list.add(service);
        }
        request.setAttribute("list", list);
//        //4. Hot Services
        List<hotservices> hotserviceList = servicesDAO.getHotServices();
        List<service> ListHotService = new ArrayList<>();
        for (int i = 0; i < hotserviceList.size(); i++) {
            service servicesHot = servicesDAO.getServicesByServicesID(hotserviceList.get(i).getServices_id());
            ListHotService.add(servicesHot);
        }
        request.setAttribute("ListHotService", ListHotService);
        //5. Blogs
        BlogsDAO blogDAO = new BlogsDAO();
        List<blog> blogList = blogDAO.getBlogsIndexNew();
        request.setAttribute("blogList", blogList);
        //6. Feedback
        List<feedback> listFeedback = new FeedbackDAO().getAllFeedback();
        request.setAttribute("listFeedback", listFeedback);
        //7. Settings
        AdminDAO settingDAO = new AdminDAO();
        HttpSession session = request.getSession();
        List<setting> listSetting = settingDAO.getSettingList();
        session.setAttribute("listSetting", listSetting);
        request.setAttribute("listSetting", listSetting);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        processRequest(request, response);
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
