/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import com.google.gson.Gson;
import dao.StaffDAO;
import init.StaffInit;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import model.Category;
import model.SearchResponse;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "StaffManagerController", urlPatterns = {"/StaffList", "/StaffDetailAdd", "/StaffEdits", "/StaffDetailEdit", "/StaffListSpec", "/StaffAddSpec", "/StaffDelSpec"})
public class StaffManagerController extends HttpServlet {

    StaffDAO staffDao = new StaffDAO();
    StaffInit staffInit = new StaffInit();

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
            out.println("<title>Servlet StaffManagerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffManagerController at " + request.getContextPath() + "</h1>");
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
            return; // Stop processing if user is not logged in or not admin
        }
        String url = request.getServletPath();
        switch (url) {
            case "/StaffList":
                handleStaffList(request, response);
                break;
            case "/StaffDetailAdd":
                request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
                break;
            case "/StaffDetailEdit":
                String staff_id = request.getParameter("staff_id");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String address = request.getParameter("address");
                String genderparam = request.getParameter("gender");
                String image = request.getParameter("image");
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("gender", genderparam);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("image", image);
                List<Category> c = staffDao.getAllSpec(staff_id);
                request.setAttribute("listspec", c);
                List<Category> uc = staffDao.getUnassignedCategories(staff_id);
                request.setAttribute("listnotspec", uc);
                request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
                break;
            case "/StaffAddSpec":
                handleAddSpec(request, response);
                break;
            case "/StaffDelSpec":
                handleDeleteSpec(request, response);
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
            return; // Stop processing if user is not logged in or not admin
        }
        String url = request.getServletPath();
        switch (url) {
            case "/StaffEdits":
                String submit = request.getParameter("submit");
                if ("add".equalsIgnoreCase(submit)) {
                    handleAddStaff(request, response);
                } else {
                    handleEditStaff(request, response);
                }
        }
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

    private void handleStaffList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;
        String nameOrId = request.getParameter("nameOrId");
        int status = -1;
        try {
            String statusParam = request.getParameter("status");
            status = (statusParam != null && !statusParam.isEmpty()) ? Integer.parseInt(statusParam) : -1;
        } catch (NumberFormatException e) {
            System.err.println("Invalid categoryId: " + e.getMessage());
        }
        SearchResponse<User> searchResponse = staffInit.getAllStaff(pageNo, pageSize, nameOrId, status);

        request.setAttribute("allblogs", searchResponse.getContent());
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/manager/StaffList.jsp").forward(request, response);
    }

    private void handleAddStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String genderparam = request.getParameter("gender");
        boolean gender;

        if (genderparam.equalsIgnoreCase("1")) {
            gender = true;
        } else {
            gender = false;
        }

        System.out.println(gender);
        System.out.println(name);
        System.out.println(email);
        System.out.println(phone);
        System.out.println(address);

        if (staffDao.checkDuplicateEmail(0, email)) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("gender", genderparam);
            request.setAttribute("errormess", "The email is already used!");
            request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            return;
        }

        if (staffDao.checkDuplicatePhone(0, phone)) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("gender", genderparam);
            request.setAttribute("errormess", "The phone number is already used!");
            request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            return;
        }

        if (!isValidPhoneNumber(phone)) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("gender", genderparam);
            request.setAttribute("errormess", "The phone number are written in the wrong format!");
            request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            return;
        }

        if (!isValidEmail(email)) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("gender", genderparam);
            request.setAttribute("errormess", "The email are written in the wrong format!");
            request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            return;
        }

        User newstaff = new User();
        newstaff.setUser_fullname(name);
        newstaff.setUser_email(email);
        newstaff.setUser_phone(phone);
        newstaff.setUser_address(address);
        newstaff.setUser_gender(gender);
        newstaff.setUser_password(UUID.randomUUID().toString().substring(0, 9));

        boolean a = staffDao.addUser(newstaff);

        if (a) {
            handleStaffList(request, response);
        } else {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("gender", genderparam);
            request.setAttribute("errormess", "Add Failed!");
            request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            return;
        }
    }

    public boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("^0\\d{9}$");
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
                && !email.startsWith("@");
    }

    private void handleEditStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String genderparam = request.getParameter("gender");
        boolean gender;

        if (genderparam.equalsIgnoreCase("1")) {
            gender = true;
        } else {
            gender = false;
        }

        System.out.println(gender);
        System.out.println(name);
        System.out.println(email);
        System.out.println(phone);
        System.out.println(address);
        System.out.println(genderparam);

    }

    private void handleAddSpec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String staff_id = request.getParameter("staff_id");
        String category_id = request.getParameter("category_id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String genderparam = request.getParameter("gender");
        String image = request.getParameter("image");

        try {
            int staffid = Integer.parseInt(staff_id);
            int categoryid = Integer.parseInt(category_id);
            boolean check = staffDao.addSpecialization(staffid, categoryid);
            if (check) {
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("gender", genderparam);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("image", image);
                List<Category> c = staffDao.getAllSpec(staff_id);
                request.setAttribute("listspec", c);
                List<Category> uc = staffDao.getUnassignedCategories(staff_id);
                request.setAttribute("listnotspec", uc);
                request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            } else {
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("gender", genderparam);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("image", image);
                List<Category> c = staffDao.getAllSpec(staff_id);
                request.setAttribute("listspec", c);
                List<Category> uc = staffDao.getUnassignedCategories(staff_id);
                request.setAttribute("listnotspec", uc);
                request.setAttribute("errormess", "Add Failed!");
                request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("gender", genderparam);
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("image", image);
            List<Category> c = staffDao.getAllSpec(staff_id);
            request.setAttribute("listspec", c);
            List<Category> uc = staffDao.getUnassignedCategories(staff_id);
            request.setAttribute("listnotspec", uc);
            request.setAttribute("errormess", "Add Failed!");
            request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
        }
    }

    private void handleDeleteSpec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String staff_id = request.getParameter("staff_id");
        String category_id = request.getParameter("category_id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String genderparam = request.getParameter("gender");
        String image = request.getParameter("image");
        try {
            int staffid = Integer.parseInt(staff_id);
            int categoryid = Integer.parseInt(category_id);
            boolean check = staffDao.deleteSpecialization(staffid, categoryid);
            if (check) {
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("gender", genderparam);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("image", image);
                List<Category> c = staffDao.getAllSpec(staff_id);
                request.setAttribute("listspec", c);
                List<Category> uc = staffDao.getUnassignedCategories(staff_id);
                request.setAttribute("listnotspec", uc);
                request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            } else {
                request.setAttribute("name", name);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("address", address);
                request.setAttribute("gender", genderparam);
                request.setAttribute("staff_id", staff_id);
                request.setAttribute("image", image);
                List<Category> c = staffDao.getAllSpec(staff_id);
                request.setAttribute("listspec", c);
                List<Category> uc = staffDao.getUnassignedCategories(staff_id);
                request.setAttribute("listnotspec", uc);
                request.setAttribute("errormess", "Delete Failed!");
                request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("gender", genderparam);
            request.setAttribute("staff_id", staff_id);
            request.setAttribute("image", image);
            List<Category> c = staffDao.getAllSpec(staff_id);
            request.setAttribute("listspec", c);
            List<Category> uc = staffDao.getUnassignedCategories(staff_id);
            request.setAttribute("listnotspec", uc);
            request.setAttribute("errormess", "Delete Failed!");
            request.getRequestDispatcher("/landing/manager/StaffManagerDetail.jsp").forward(request, response);
        }
    }

    private User checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null || account.getRole_id() != 2) { // Chỉ admin (role_id = 1) được truy cập
            response.sendRedirect(request.getContextPath() + "/nav/error");
            return null; // Stop further processing
        }
        return account;
    }
}
