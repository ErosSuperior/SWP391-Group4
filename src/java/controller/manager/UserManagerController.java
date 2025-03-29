/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SearchResponse;
import dao.UserDAO;
import init.UserInit;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@WebServlet(name = "UserManagerController", urlPatterns = {
    "/admin/adminList",
    "/admin/adduser",
    "/admin/edituser",
    "/admin/updatestatus",
    "/admin/manageedits"
})
public class UserManagerController extends HttpServlet {

    UserDAO userDao = new UserDAO();
    UserInit userInit = new UserInit();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        String url = request.getServletPath();
        switch (url) {
            case "/admin/adminList":
                try {
                    handleUserList(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(UserManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "/admin/adduser":
                request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
                break;
            case "/admin/edituser":
                handleEditUserForm(request, response);
                break;
            case "/admin/updatestatus":
                try {
                    updateStatus(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(UserManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        String url = request.getServletPath();
        switch (url) {
            case "/admin/manageedits":
                String submit = request.getParameter("submit");
                try {
                    if (submit.equalsIgnoreCase("add")) {
                        addUser(request, response);
                    } else {
                        editUser(request, response);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

    private void handleUserList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 4;
        String nameOrId = request.getParameter("nameOrId");
        int role_id = -1;
        int status = -1;

        try {
            String role_idParam = request.getParameter("role_id");
            if (role_idParam != null && !role_idParam.isEmpty()) {
                role_id = Integer.parseInt(role_idParam);
            }
            String statusParam = request.getParameter("status");
            status = (statusParam != null && !statusParam.isEmpty()) ? Integer.parseInt(statusParam) : -1;

            SearchResponse<User> searchResponse = userInit.getUser(pageNo, pageSize, nameOrId, role_id, status);

            request.setAttribute("alluser", searchResponse.getContent());
            request.setAttribute("totalElements", searchResponse.getTotalElements());
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);
            request.getRequestDispatcher("/landing/manager/UserManager.jsp").forward(request, response);
            return;
        } catch (NumberFormatException e) {
            System.err.println("Invalid roleId or status: " + e.getMessage());
        }

        SearchResponse<User> searchResponse = userInit.getUser(pageNo, pageSize, nameOrId, role_id, status);

        request.setAttribute("alluser", searchResponse.getContent());
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/manager/UserManager.jsp").forward(request, response);
    }

    private void handleEditUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email != null && !email.trim().isEmpty()) {
            User user = userDao.checkAccountExit(email);
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/adminList");
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            String userId = request.getParameter("userId");
            String status = request.getParameter("status"); // Convert "1" to true, "0" to false

            userDao.deleteUser(userId, status); // This method actually updates status
            handleUserList(request, response);
        } catch (NumberFormatException e) {
            Logger.getLogger(UserManagerController.class.getName()).log(Level.SEVERE, "Error updating user status", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String roleId = request.getParameter("roleId");
        String status = request.getParameter("status");
        String image = request.getParameter("image");

        // Validate required fields
        if (fullname == null || fullname.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            request.setAttribute("message", "Required fields cannot be empty");
            request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
            return;
        }

        // Check email existence
        User existingUser = userDao.checkAccountExit(email);
        if (existingUser != null) {
            request.setAttribute("message", "Email already exists");
            request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
            return;
        }

        // Validate password
        if (password.length() < 6 || password.contains(" ")) {
            request.setAttribute("message", "Password must be at least 6 characters and cannot contain spaces");
            request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
            return;
        }

        try {
            boolean genderBool = Boolean.parseBoolean(gender);
            int roleIdInt = Integer.parseInt(roleId);
            boolean statusBool = Boolean.parseBoolean(status);

            userDao.addUser(fullname, genderBool, address, password, email, phone, roleIdInt, statusBool, image);
            response.sendRedirect(request.getContextPath() + "/admin/adminList");
        } catch (NumberFormatException e) {
            Logger.getLogger(UserManagerController.class.getName()).log(Level.SEVERE, "Error adding user", e);
            request.setAttribute("message", "Invalid input data");
            request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
        }
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String fullname = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String roleId = request.getParameter("roleId");
        String status = request.getParameter("status");
        String image = request.getParameter("image");

        // Validate required fields
        if (userId == null || userId.trim().isEmpty()
                || fullname == null || fullname.trim().isEmpty()) {
            request.setAttribute("message", "Required fields cannot be empty");
            request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
            return;
        }

        try {
            int userIdInt = Integer.parseInt(userId);
            boolean genderBool = Boolean.parseBoolean(gender);
            int roleIdInt = Integer.parseInt(roleId);
            boolean statusBool = Boolean.parseBoolean(status);

            // Lấy email hiện tại từ database thay vì dùng từ form
            User existingUser = userDao.getUserDetail(userIdInt);
            if (existingUser == null) {
                request.setAttribute("message", "User not found");
                request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
                return;
            }

            userDao.updateUser(userIdInt, fullname, genderBool, address, existingUser.getUser_email(),
                    phone, roleIdInt, statusBool, image);
            response.sendRedirect(request.getContextPath() + "/admin/adminList");
        } catch (NumberFormatException e) {
            Logger.getLogger(UserManagerController.class.getName()).log(Level.SEVERE, "Error updating user", e);
            request.setAttribute("message", "Invalid input data");
            request.getRequestDispatcher("/landing/manager/UserManagerDetail.jsp").forward(request, response);
        }
    }

    private User checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null || account.getRole_id() != 1) {
            response.sendRedirect(request.getContextPath() + "/nav/error");
            return null;  // Stop further processing
        }

        return account;
    }
}