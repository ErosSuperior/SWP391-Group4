package controller.customer;

import dao.ReservationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Service;
import dao.ServiceDAO;
import dao.UserDAO;
import init.ServiceInit;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.SearchResponse;
import model.User;

/**
 *
 * @author thang
 */
@WebServlet(name = "CustomerServiceController", urlPatterns = {"/customer/customerlistService", "/customer/customerdetailService", "/customer/service/addToCart"})
public class CustomerServiceController extends HttpServlet {

    ServiceDAO serviceDAO = new ServiceDAO();
    ServiceInit serviceInit = new ServiceInit();
    ReservationDAO reservationDao = new ReservationDAO();
    UserDAO userDao = new UserDAO();

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
                handleAddtoCart(request, response);
                break;
            default:
                System.out.println("Unknown URL requested: " + url);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
            if ((minPriceParam != null && !minPriceParam.trim().isEmpty()) && (maxPriceParam != null && !maxPriceParam.trim().isEmpty())) {
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
        request.getRequestDispatcher("/landing/customer/ServiceList.jsp").forward(request, response);
    }

    private void handleServiceDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int serviceId = -1; // Default value to prevent errors
        String serviceIdParam = request.getParameter("serviceId");

        // Retrieve cart message from request attributes
        String cartmessage = (String) request.getAttribute("cartmessage");

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
        request.setAttribute("cartmessage", cartmessage);  
        request.setAttribute("highlightedService", highlightedService);
        request.setAttribute("relatedServices", allServiceByCategory);
        request.setAttribute("staffList", userDao.getAllStaffNotBusy());
        request.setAttribute("serviceId", serviceId);

        request.getRequestDispatcher("/landing/customer/ServiceDetail.jsp").forward(request, response);
    }

    private void handleAddtoCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if user is logged in
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            // Redirect to login page if not logged in
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }
        boolean cartExist = reservationDao.checkCartExist(account.getUser_id());
        try {
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            String reservationDate = request.getParameter("reservation_date");

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Date utilDate = format.parse(reservationDate);

            Date beginTime = new Date(utilDate.getTime());

            int selectedStaffId = Integer.parseInt(request.getParameter("selected_staff"));

            String serviceparam = request.getParameter("serviceId");
            int serviceId = Integer.parseInt(serviceparam);
            Service sv = serviceDAO.getServicebyId(serviceId);

            if (!cartExist) {
                boolean addcart = reservationDao.addCart(account.getUser_id()); // create a cart if not exist
            }

            int cartId = reservationDao.getCartByUserID(account.getUser_id());

            int curQuantity = reservationDao.getCartQuantity(cartId, serviceId);

            if (curQuantity != 0) {
                int newQuantity = curQuantity + quantity;

                reservationDao.updateCartQuantity(cartId, serviceId, newQuantity); // If the cart already have the correnponding service, update the newquantity to the cart 

                request.setAttribute("cartmessage", "SUCCESSFUL ADDED");

                handleServiceDetail(request, response);

            } else {

                reservationDao.addToCart(cartId, serviceId, (float) (sv.getServicePrice() - sv.getServiceDiscount()), selectedStaffId, beginTime, quantity, sv.getCategoryId());

                request.setAttribute("cartmessage", "SUCCESSFUL ADDED");

                handleServiceDetail(request, response);
            }

        } catch (Exception e) {
            handleServiceList(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
