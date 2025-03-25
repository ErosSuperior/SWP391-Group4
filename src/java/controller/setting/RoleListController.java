package controller.setting;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.SearchResponse;
import dao.RoleDAO;
import init.RoleInit;
import model.userRole;

@WebServlet(name = "RoleListController", urlPatterns = {
    "/admin/roleList",
    "/admin/addRole",
    "/admin/editRole",
    "/admin/manageRole",
    "/admin/deleteRole"
})
public class RoleListController extends HttpServlet {

    private final RoleDAO roleDAO = new RoleDAO();
    private final RoleInit roleInit = new RoleInit();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/admin/roleList" -> {
                try {
                    handleRoleList(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(RoleListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "/admin/addRole" ->
                request.getRequestDispatcher("/landing/RoleDetail.jsp").forward(request, response);
            case "/admin/editRole" ->
                handleEditRoleForm(request, response);
            case "/admin/deleteRole" ->
                handleDeleteRole(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();
        switch (url) {
            case "/admin/manageRole" -> {
                String submit = request.getParameter("submit");
                try {
                    if ("add".equalsIgnoreCase(submit)) {
                        addRole(request, response);
                    } else {
                        editRole(request, response);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(RoleListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void handleRoleList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String pageNoParam = request.getParameter("pageNo");
        String nameOrId = request.getParameter("nameOrId");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 2;

        SearchResponse<userRole> searchResponse = roleInit.getRoles(pageNo, pageSize, nameOrId);

        request.setAttribute("allRoles", searchResponse.getContent());
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/RoleList.jsp").forward(request, response);
    }

    private void handleEditRoleForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roleIdParam = request.getParameter("roleId");
        if (roleIdParam != null && !roleIdParam.trim().isEmpty()) {
            try {
                int roleId = Integer.parseInt(roleIdParam);
                userRole role = roleDAO.getRoleById(roleId);
                if (role != null) {
                    request.setAttribute("role", role);
                    request.getRequestDispatcher("/landing/RoleDetail.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(RoleListController.class.getName()).log(Level.SEVERE, "Invalid role ID", e);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/roleList");
    }

    private void handleDeleteRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roleId = request.getParameter("roleId");
        String status = request.getParameter("status");
        if (roleId != null && !roleId.isEmpty()) {
            roleDAO.updateStautsRole(roleId,status);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Role ID is required");
        }
    }

    private void addRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roleName = request.getParameter("roleName");

        if (roleName == null || roleName.trim().isEmpty()) {
            request.setAttribute("message", "Role name is required");
            request.getRequestDispatcher("/landing/RoleDetail.jsp").forward(request, response);
            return;
        }

        roleDAO.addRole(roleName);
        response.sendRedirect(request.getContextPath() + "/admin/roleList");
    }

    private void editRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roleIdParam = request.getParameter("roleId");
        String roleName = request.getParameter("roleName");

        if (roleIdParam == null || roleIdParam.trim().isEmpty() || roleName == null || roleName.trim().isEmpty()) {
            request.setAttribute("message", "Required fields cannot be empty");
            request.getRequestDispatcher("/landing/RoleDetail.jsp").forward(request, response);
            return;
        }

        try {
            int roleId = Integer.parseInt(roleIdParam);
            roleDAO.updateRole(roleId, roleName);
            response.sendRedirect(request.getContextPath() + "/admin/roleList");
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid role ID");
            request.getRequestDispatcher("/landing/RoleDetail.jsp").forward(request, response);
        }
    }
}
