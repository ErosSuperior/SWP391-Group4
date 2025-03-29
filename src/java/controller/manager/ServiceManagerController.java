/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.ServiceDAO;
import init.ServiceInit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SearchResponse;
import model.Service;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ServiceManagerController", urlPatterns = {
    "/manager/ServiceList",
    "/manager/ServiceList/Add",
    "/manager/ServiceList/Detail",
    "/manager/ServiceList/UpdateStatus"
})
public class ServiceManagerController extends HttpServlet {

    private ServiceDAO serviceDAO = new ServiceDAO();
    private ServiceInit serviceInit = new ServiceInit();

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServiceManagerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceManagerController at " + request.getContextPath() + "</h1>");
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
            case "/manager/ServiceList":
                handleServiceList(request, response);
                break;
            case "/manager/ServiceList/Add":
                showAddForm(request, response);
                break;
            case "/manager/ServiceList/Detail":
                showEditForm(request, response);
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
            case "/manager/ServiceList/Add":
                addService(request, response);
                break;
            case "/manager/ServiceList/Detail":
                updateService(request, response);
                break;
            case "/manager/ServiceList/UpdateStatus":
                updateServiceStatus(request, response);
                break;
        }
    }

    private void handleServiceList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        String sortBy = request.getParameter("sortvalue") != null ? request.getParameter("sortvalue") : "service_id";
        String sortDir = request.getParameter("sortdir") != null ? request.getParameter("sortdir") : "ASC";
        SearchResponse<Service> searchResponse = serviceInit.getService(pageNo, pageSize, nameOrId, categoryId, status, sortDir, sortBy);

        request.setAttribute("allServices", searchResponse.getContent());
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/manager/ServiceManager.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categories", serviceDAO.getActiveCategory());
        request.setAttribute("action", "add");
        request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        request.setAttribute("service", serviceDAO.getServiceById(serviceId));
        request.setAttribute("categories", serviceDAO.getActiveCategory());
        request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
    }

    private void addService(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        Service service = mapRequestToService(request);
        service.setServiceStatus(1); // Đặt trạng thái mặc định là 1 (Active)
        if (serviceDAO.addService(service)) {
            service.setServiceId(serviceDAO.getLatestService());
            service.setServiceImage(request.getParameter("image"));
            serviceDAO.insertServiceImage(service.getServiceId(), service.getServiceImage());
            // Thêm bước lưu trạng thái vào bảng service_status
            serviceDAO.insertServiceStatus(service.getServiceId(), service.getServiceStatus());
            response.sendRedirect(request.getContextPath() + "/manager/ServiceList");
        } else {
            request.setAttribute("error", "Failed to add service");
            showAddForm(request, response);
        }
    } catch (IllegalArgumentException | SQLException e) {
        request.setAttribute("error", e.getMessage());
        showAddForm(request, response);
    }
}

    private void updateService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        Service existingService = serviceDAO.getServiceById(serviceId);
        Service updatedService = mapRequestToService(request);
        
        // Cập nhật các trường từ form, giữ nguyên trạng thái
        existingService.setServiceTitle(updatedService.getServiceTitle());
        existingService.setServiceBi(updatedService.getServiceBi());
        existingService.setCategoryId(updatedService.getCategoryId());
        existingService.setServicePrice(updatedService.getServicePrice());
        existingService.setServiceDiscount(updatedService.getServiceDiscount());
        existingService.setServiceDetail(updatedService.getServiceDetail());
        
        if (serviceDAO.updateService(existingService)) {
            response.sendRedirect(request.getContextPath() + "/manager/ServiceList");
        } else {
            request.setAttribute("error", "Failed to update service");
            showEditForm(request, response);
        }
    } catch (NumberFormatException e) {
        request.setAttribute("error", "Invalid number format");
        showEditForm(request, response);
    }
    }

    private void updateServiceStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        serviceDAO.updateServiceStatus(
                Integer.parseInt(request.getParameter("serviceId")),
                Integer.parseInt(request.getParameter("serviceStatus"))
        );
    }

    private Service mapRequestToService(HttpServletRequest request) {
        Service service = new Service();
        service.setServiceTitle(validateNotEmpty(request.getParameter("title"), "Service title is required"));
        service.setServiceBi(request.getParameter("bi"));
        service.setCategoryId(Integer.parseInt(validateNotEmpty(request.getParameter("categoryId"), "Category is required")));
        service.setServicePrice(Double.parseDouble(validateNotEmpty(request.getParameter("price"), "Price is required")));
        service.setServiceDiscount(parseDoubleOrDefault(request.getParameter("discount"), 0.0));
        service.setServiceDetail(request.getParameter("detail"));
        return service;
    }

    private String validateNotEmpty(String value, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private User checkSession(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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
    } // </editor-fold>
}
