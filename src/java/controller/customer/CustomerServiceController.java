package controller.customer;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Service;
import dao.ServiceDAO;
import dao.ServiceInit;
import java.util.List;
import model.SearchResponse;

/**
 *
 * @author thang
 */
@WebServlet(name = "CustomerServiceController", urlPatterns = {"/customer/customerlistService", "/customer/customerdetailService", "/customer/service/addToCart"})
public class CustomerServiceController extends HttpServlet {

    ServiceDAO serviceDAO = new ServiceDAO();
    ServiceInit serviceInit = new ServiceInit();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerServiceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerServiceController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/customer/customerlistService":
                handleServiceList(request, response);
                break;
            case "/customer/customerdetailService":
                handleServiceDetail(request, response);
                break;
            case "/customer/service/addToCart":
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void handleServiceList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;
        String nameOrId = request.getParameter("nameOrId");
        int categoryId = -1; // Default value
        int minPrice = -1;
        int maxPrice = -1;
        try {
            String categoryIdParam = request.getParameter("categoryId");
            if (categoryIdParam != null && !categoryIdParam.trim().isEmpty()) {
                categoryId = Integer.parseInt(categoryIdParam);
            }
            String minPriceParam = request.getParameter("minPrice");
            String maxPriceParam = request.getParameter("maxPrice");
            if ((minPriceParam != null && !minPriceParam.trim().isEmpty())&&(maxPriceParam != null && !maxPriceParam.trim().isEmpty())){
                minPrice = Integer.parseInt(minPriceParam);
                maxPrice = Integer.parseInt(maxPriceParam);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid categoryId: " + e.getMessage());
        }
        SearchResponse<Service> searchResponse = serviceInit.getActiveService(pageNo, pageSize, nameOrId, categoryId, minPrice, maxPrice);
        List<Service> allCategory = serviceDAO.getActiveCategory();
        List<Service> bestService = serviceDAO.findBestService();
        
        request.setAttribute("category", allCategory);
        request.setAttribute("bestservice", bestService);
        request.setAttribute("allservices", searchResponse.getContent());
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/customerservice/ServiceList.jsp").forward(request, response);
    }

    private void handleServiceDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceId = -1; // Default value to prevent errors
        String serviceIdParam = request.getParameter("serviceId");

        try {
            if (serviceIdParam != null && !serviceIdParam.trim().isEmpty()) {
                serviceId = Integer.parseInt(serviceIdParam);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid serviceId: " + e.getMessage());
        }

        
            List<String> serviceImage = serviceDAO.getAllServiceImages(serviceId);
            Service highlightedService = serviceDAO.getServicebyId(serviceId);
            int categoryOfService = highlightedService.getCategoryId();
            List<Service> allServiceByCategory = serviceDAO.getAllServicebyCategory(categoryOfService);
        
        
        // Set attributes for JSP
        request.setAttribute("serviceImages", serviceImage);
        request.setAttribute("highlightedService", highlightedService);
        request.setAttribute("relatedServices", allServiceByCategory);
        request.setAttribute("serviceId", serviceId);
        request.getRequestDispatcher("/landing/customerservice/ServiceDetail.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
