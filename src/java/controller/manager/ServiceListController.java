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
@WebServlet(name = "ServiceListController", urlPatterns = {"/manager/ServicesList"})
public class ServiceListController extends HttpServlet {
    private ServiceDAO serviceDAO;

    @Override
    public void init() throws ServletException {
        serviceDAO = new ServiceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get parameter from request
        String nameOrId = request.getParameter("nameOrId");
        String statusParam = request.getParameter("status");
        String pageParam = request.getParameter("page");
        String sortBy = request.getParameter("sortBy");
        String sortDir = request.getParameter("sortDir");

        // Mặc định giá trị nếu không có trên request
        int status = (statusParam != null) ? Integer.parseInt(statusParam) : -1;
        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
        int limit = 3; // Số bản ghi mỗi trang
        int offset = (page - 1) * limit;

        
// Nếu không có giá trị, mặc định sắp xếp theo title, ascending
if (sortBy == null || sortBy.isEmpty()) {
    sortBy = "title";
}
if (sortDir == null || sortDir.isEmpty()) {
    sortDir = "asc";
}

        // Gọi DAO để lấy danh sách dịch vụ
        List<Service> services = serviceDAO.getAllService(offset, limit, nameOrId, -1, status, sortBy, sortDir);
        
        // Tính tổng số bản ghi để phân trang
        int totalRecords = serviceDAO.countAllServices(nameOrId, -1, status);
        int totalPages = (int) Math.ceil((double) totalRecords / limit);

        // Đẩy dữ liệu lên request để hiển thị trên JSP
        request.setAttribute("services", services);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("nameOrId", nameOrId);
        request.setAttribute("status", status);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("sortDir", sortDir);

        // Chuyển hướng đến JSP để render dữ liệu
        RequestDispatcher dispatcher = request.getRequestDispatcher("/landing/manager/ServiceManager.jsp");
        dispatcher.forward(request, response);
    }
}