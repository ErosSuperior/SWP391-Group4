/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.ServiceDAO;
import init.ServiceInit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Service;

@WebServlet(name = "ServiceManagerController", urlPatterns = {
    "/manager/ServiceList",
    "/manager/ServiceList/Add",
    "/manager/ServiceList/Edit",
    "/manager/ServiceList/Update",
    "/manager/ServiceList/Delete"
})
public class ServiceManagerController extends HttpServlet {

    private ServiceDAO serviceDAO;
    private ServiceInit serviceInit;

    @Override
    public void init() throws ServletException {
        serviceDAO = new ServiceDAO();
        serviceInit = new ServiceInit();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServiceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServiceController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/manager/ServiceList":
                handleServiceList(request, response);
                break;
            case "/manager/ServiceList/Add":
                showAddForm(request, response);
                break;
            case "/manager/ServiceList/Update":
                showEditForm(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/manager/ServiceList/Add":
                addService(request, response);
                break;
            case "/manager/ServiceList/Update":
                updateService(request, response);
                break;
//            case "/manager/UpdateServiceStatus":
//                updateServiceStatus(request, response);
//                break;
        }
    }

    private void handleServiceList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameOrId = request.getParameter("nameOrId");
        String statusParam = request.getParameter("status");
        String pageParam = request.getParameter("page");
        String sortBy = request.getParameter("sortBy");
        String sortDir = request.getParameter("sortDir");

        int status = (statusParam != null) ? Integer.parseInt(statusParam) : -1;
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int limit = 3; // Số bản ghi mỗi trang
        int offset = (page - 1) * limit;

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "title";
        }
        if (sortDir == null || sortDir.isEmpty()) {
            sortDir = "asc";
        }

        List<Service> services = serviceDAO.getAllService(offset, limit, nameOrId, -1, status, sortBy, sortDir);

        int totalRecords = serviceDAO.countAllServices(nameOrId, -1, status);
        int totalPages = (int) Math.ceil((double) totalRecords / limit);

        request.setAttribute("services", services);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("nameOrId", nameOrId);
        request.setAttribute("status", status);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("sortDir", sortDir);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/landing/manager/ServiceManager.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> categories = serviceDAO.getActiveCategory();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        Service service = serviceDAO.getServiceById(serviceId);
        List<Service> categories = serviceDAO.getActiveCategory();
        request.setAttribute("service", service);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
    }

    private void addService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Service service = mapRequestToService(request);
            boolean success = serviceDAO.addService(service);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/manager/ServiceList");
            } else {
                request.setAttribute("error", "Failed to add service");
                showAddForm(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format");
            showAddForm(request, response);
        }
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Service service = mapRequestToService(request);
            service.setServiceId(Integer.parseInt(request.getParameter("serviceId")));

            boolean success = serviceDAO.updateService(service);
            if (success) {
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
            throws ServletException, IOException {
        try {
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));
            int newStatus = Integer.parseInt(request.getParameter("newStatus"));

            serviceDAO.updateServiceStatus(serviceId, newStatus);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private Service mapRequestToService(HttpServletRequest request) throws IllegalArgumentException {
        Service service = new Service();
        String title = request.getParameter("title");
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Service title is required");
        }
        service.setServiceTitle(title);

        String bi = request.getParameter("bi");
        service.setServiceBi(bi != null ? bi : "");

        String categoryIdStr = request.getParameter("categoryId");
        if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required");
        }
        service.setCategoryId(Integer.parseInt(categoryIdStr));

        String priceStr = request.getParameter("price");
        if (priceStr == null || priceStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Price is required");
        }
        double price = Double.parseDouble(priceStr);
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        service.setServicePrice(price);

        String discountStr = request.getParameter("discount");
        service.setServiceDiscount(discountStr != null && !discountStr.trim().isEmpty() ? Double.parseDouble(discountStr) : 0.0);

        String detail = request.getParameter("detail");
        service.setServiceDetail(detail != null ? detail : "");

        String image = request.getParameter("image");
        service.setServiceImage(image != null ? image : "");

        return service;
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing services in the child management system";
    }
}