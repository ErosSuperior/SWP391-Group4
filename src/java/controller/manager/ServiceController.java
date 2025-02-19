/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.ServiceDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Service;

/**
 *
 * @author CMD
 */
@WebServlet(name = "ServiceListController", urlPatterns = {"/manager/ServiceList", "/manager/ServiceList/Add", "/manager/ServiceList/Update", "/manager/ServiceList/Delete", "/manager/ServiceDetail"})
public class ServiceController extends HttpServlet {

    private ServiceDAO serviceDAO;

    @Override
    public void init() throws ServletException {
        serviceDAO = new ServiceDAO();
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
                handleAddService(request, response);
            case "/manager/ServiceList/Update":
                break;
            case "/manager/ServiceList/Delete":
                break;
            case "/manager/ServiceDetail":
                handleServiceDetail(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/manager/managerupdatestatusBlog":
                updateService(request, response);
                break;
        }
    }

    private void handleServiceList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get parameter from request
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

    private void handleServiceDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get parameter from request
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/landing/manager/ServiceDetail.jsp");
        dispatcher.forward(request, response);
    }
    
    private void handleAddService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get parameter from request
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/landing/manager/AddService.jsp");
        dispatcher.forward(request, response);
    }
    private void updateService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
