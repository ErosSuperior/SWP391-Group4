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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Service;

@WebServlet(name = "ServiceController", urlPatterns = {
    "/manager/serviceList", 
    "/manager/serviceAdd", 
    "/manager/serviceEdit", 
    "/manager/serviceUpdateStatus", 
    "/manager/serviceEdits"})
public class ServiceController extends HttpServlet {

    private ServiceDAO serviceDao;
    private ServiceInit serviceInit;

    @Override
    public void init() throws ServletException {
        serviceDao = new ServiceDAO();
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
            case "/manager/serviceList":
                handleServiceList(request, response);
                break;
            case "/manager/serviceAdd":
                request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
                break;
            case "/manager/serviceEdit":
                
                String serviceId = request.getParameter("serviceId");
                String title = request.getParameter("serviceTitle");
                String categoryId = request.getParameter("categoryId");
                String price = request.getParameter("servicePrice");
                String discount = request.getParameter("serviceDiscount");
                String detail = request.getParameter("serviceDetail");
                String image = request.getParameter("serviceImage");
                request.setAttribute("serviceId", serviceId);
                request.setAttribute("title", title);
                request.setAttribute("categoryId", categoryId);
                request.setAttribute("servicePrice", price);
                request.setAttribute("serviceDiscount", discount);
                request.setAttribute("serviceDetail", detail);
                request.setAttribute("serviceImage", image);
                request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/manager/serviceUpdateStatus":
                updateStatus(request, response);
                break;
            case "/manager/serviceEdits":
                String submit = request.getParameter("submit");
                if (submit.equalsIgnoreCase("add")) {
                    addService(request, response);
                    handleServiceList(request, response);
                } else {
                    editService(request, response);
                    handleServiceList(request, response);
                }
                break;
        }
    }

    private void handleServiceList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageParam = request.getParameter("page");
        int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
        int limit = 3;
        int offset = (page - 1) * limit;

        String nameOrId = request.getParameter("nameOrId");
        String statusParam = request.getParameter("status");
        int status = (statusParam != null && !statusParam.isEmpty()) ? Integer.parseInt(statusParam) : -1;
        String sortBy = request.getParameter("sortBy");
        String sortDir = request.getParameter("sortDir");

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "title";
        }
        if (sortDir == null || sortDir.isEmpty()) {
            sortDir = "asc";
        }

        List<Service> services = serviceDao.getAllService(offset, limit, nameOrId, -1, status, sortBy, sortDir);
        int totalRecords = serviceDao.countAllServices(nameOrId, -1, status);
        int totalPages = (int) Math.ceil((double) totalRecords / limit);

        request.setAttribute("services", services);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("nameOrId", nameOrId);
        request.setAttribute("status", status);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("sortDir", sortDir);

        request.getRequestDispatcher("/landing/manager/ServiceManager.jsp").forward(request, response);
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serviceIdParam = request.getParameter("serviceId");
        int serviceId = Integer.parseInt(serviceIdParam);
        String statusParam = request.getParameter("serviceStatus");
        int status = Integer.parseInt(statusParam);
        serviceDao.updateStatus(serviceId, status);
    }

    private void addService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> category = serviceDao.getActiveCategory();
        String title = request.getParameter("title");
        String categoryIdParam = request.getParameter("categoryId");
        String priceParam = request.getParameter("servicePrice");
        String discountParam = request.getParameter("serviceDiscount");
        String detail = request.getParameter("serviceDetail");
        String image = request.getParameter("serviceImage");

        if (title == null || title.trim().isEmpty() ||
            categoryIdParam == null || categoryIdParam.trim().isEmpty() ||
            priceParam == null || priceParam.trim().isEmpty() ||
            detail == null || detail.trim().isEmpty() ||
            image == null || image.trim().isEmpty()) {
            request.setAttribute("message", "All fields are required.");
            request.setAttribute("title", title);
            request.setAttribute("categoryId", categoryIdParam);
            request.setAttribute("servicePrice", priceParam);
            request.setAttribute("serviceDiscount", discountParam);
            request.setAttribute("serviceDetail", detail);
            request.setAttribute("serviceImage", image);
            request.setAttribute("category", category);
            request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
            return;
        }

        try {
            int categoryId = Integer.parseInt(categoryIdParam);
            double price = Double.parseDouble(priceParam);
            double discount = (discountParam != null && !discountParam.trim().isEmpty()) ? Double.parseDouble(discountParam) : 0.0;

            Service service = new Service();
            service.setServiceTitle(title);
            service.setCategoryId(categoryId);
            service.setServicePrice(price);
            service.setServiceDiscount(discount);
            service.setServiceDetail(detail);
            service.setServiceImage(image);
            service.setServiceStatus(1); // Default status
            serviceDao.addService(service);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid number format.");
            request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
        }
    }

    private void editService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Service> category = serviceDao.getActiveCategory();        
        String serviceIdParam = request.getParameter("serviceId");
        String title = request.getParameter("title");
        String categoryIdParam = request.getParameter("categoryId");
        String priceParam = request.getParameter("servicePrice");
        String discountParam = request.getParameter("serviceDiscount");
        String detail = request.getParameter("serviceDetail");
        String image = request.getParameter("serviceImage");

        if (serviceIdParam == null || serviceIdParam.trim().isEmpty() ||
            title == null || title.trim().isEmpty() ||
            categoryIdParam == null || categoryIdParam.trim().isEmpty() ||
            priceParam == null || priceParam.trim().isEmpty() ||
            detail == null || detail.trim().isEmpty() ||
            image == null || image.trim().isEmpty()) {
            request.setAttribute("message", "All fields are required.");
            request.setAttribute("serviceId", serviceIdParam);
            request.setAttribute("title", title);
            request.setAttribute("categoryId", categoryIdParam);
            request.setAttribute("servicePrice", priceParam);
            request.setAttribute("serviceDiscount", discountParam);
            request.setAttribute("serviceDetail", detail);
            request.setAttribute("serviceImage", image);
            request.setAttribute("category", category);
            request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
            return;
        }

        try {
            int serviceId = Integer.parseInt(serviceIdParam);
            int categoryId = Integer.parseInt(categoryIdParam);
            double price = Double.parseDouble(priceParam);
            double discount = (discountParam != null && !discountParam.trim().isEmpty()) ? Double.parseDouble(discountParam) : 0.0;

            Service service = new Service();
            service.setServiceId(serviceId);
            service.setServiceTitle(title);
            service.setCategoryId(categoryId);
            service.setServicePrice(price);
            service.setServiceDiscount(discount);
            service.setServiceDetail(detail);
            service.setServiceImage(image);
            serviceDao.updateService(service);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid number format.");
            request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for managing services in the child management system";
    }
}