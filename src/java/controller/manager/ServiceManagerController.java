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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@WebServlet(name = "ServiceManagerController", urlPatterns = {
        "/manager/ServiceList", "/manager/ServiceList/Add", "/manager/ServiceList/Detail", "/manager/ServiceList/UpdateStatus"
})
public class ServiceManagerController extends HttpServlet {
    private ServiceDAO serviceDAO;
    private ServiceInit serviceInit;

    @Override
    public void init() {
        serviceDAO = new ServiceDAO();
        serviceInit = new ServiceInit();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        switch (request.getServletPath()) {
            case "/manager/ServiceList" -> handleServiceList(request, response);
            case "/manager/ServiceList/Add" -> showAddForm(request, response);
            case "/manager/ServiceList/Detail" -> showEditForm(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        switch (request.getServletPath()) {
            case "/manager/ServiceList/Add" -> addService(request, response);
            case "/manager/ServiceList/Detail" -> updateService(request, response);
            case "/manager/ServiceList/UpdateStatus" -> updateServiceStatus(request, response);
        }
    }

    private void handleServiceList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int pageNo = parseIntOrDefault(request.getParameter("pageNo"), 0);
            int pageSize = 4;
            String nameOrId = request.getParameter("nameOrId");
            int categoryId = parseIntOrDefault(request.getParameter("categoryId"), -1);
            int status = parseIntOrDefault(request.getParameter("status"), -1);
            String sortBy = request.getParameter("sortvalue") != null ? request.getParameter("sortvalue") : "service_id";
            String sortDir = request.getParameter("sortdir") != null ? request.getParameter("sortdir") : "ASC";

            SearchResponse<Service> searchResponse = serviceInit.getService(pageNo, pageSize, nameOrId, categoryId, status, sortDir, sortBy);
            request.setAttribute("allblogs", searchResponse.getContent());
            request.setAttribute("totalElements", searchResponse.getTotalElements());
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);
            request.getRequestDispatcher("/landing/manager/ServiceManager.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", serviceDAO.getActiveCategory());
        request.setAttribute("action", "add");
        request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        request.setAttribute("service", serviceDAO.getServiceById(serviceId));
        request.setAttribute("categories", serviceDAO.getActiveCategory());
        request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
    }

    private void addService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Service service = mapRequestToService(request);
            if (serviceDAO.addService(service)) {
                service.setServiceId(serviceDAO.getLatestService());
                service.setServiceImage(request.getParameter("image"));
                service.setServiceStatus(1);
                serviceDAO.insertServiceImage(service.getServiceId(), service.getServiceImage());
                serviceDAO.insertServiceStatus(service.getServiceId(), service.getServiceStatus());
                handleServiceList(request, response);
            } else {
                request.setAttribute("error", "Failed to add service");
                showAddForm(request, response);
            }
        } catch (IllegalArgumentException | SQLException e) {
            request.setAttribute("error", e.getMessage());
            showAddForm(request, response);
        }
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Service service = mapRequestToService(request);
            service.setServiceId(Integer.parseInt(request.getParameter("serviceId")));
            if (serviceDAO.updateService(service)) {
                handleServiceList(request, response);
            } else {
                request.setAttribute("error", "Failed to update service");
                showEditForm(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format");
            showEditForm(request, response);
        }
    }

    private void updateServiceStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            serviceDAO.updateServiceStatus(
                    Integer.parseInt(request.getParameter("serviceId")),
                    Integer.parseInt(request.getParameter("serviceStatus"))
            );
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
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
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(errorMessage);
        return value;
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        try { return Integer.parseInt(value); } catch (NumberFormatException e) { return defaultValue; }
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        try { return Double.parseDouble(value); } catch (NumberFormatException e) { return defaultValue; }
    }
    
    private User checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null || account.getRole_id() != 2) {
            response.sendRedirect(request.getContextPath() + "/nav/error");
            return null;  // Stop further processing
        }

        return account;
    }
}
