package UserProfile;

import dao.ChildrenDAO;
import init.ChildrenInit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import model.Children;
import model.SearchResponse;
import model.User;

@WebServlet(name = "ChildrenListController", urlPatterns = {
    "/children/list",
    "/children/add",
    "/children/update",
    "/children/delete",
    "/children/detail"
})
public class ChildrenListController extends HttpServlet {

    private final ChildrenDAO childrenDao = new ChildrenDAO();
    private final ChildrenInit childrenInit = new ChildrenInit();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isValidSession(request, response)) {
            return;
        }

        String url = request.getServletPath();
        switch (url) {
            case "/children/list":
                handleListChildren(request, response);
                break;
            case "/children/detail":
                handleChildDetail(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!isValidSession(request, response)) {
            return;
        }

        String url = request.getServletPath();
        switch (url) {
            case "/children/add":
                handleAddChild(request, response);
                break;
            case "/children/update":
                handleUpdateChild(request, response);
                break;
            case "/children/delete":
                handleDeleteChild(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    private boolean isValidSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return false;
        }
        return true;
    }

    private void handleListChildren(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }

        String pageNoParam = request.getParameter("pageNo");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 5; // Adjust as needed
        String nameOrId = request.getParameter("nameOrId");
        String sortBy = request.getParameter("sortBy") != null ? request.getParameter("sortBy") : "children_id";
        String sortDir = request.getParameter("sortDir") != null ? request.getParameter("sortDir") : "ASC";

        try {
            SearchResponse<Children> searchResponse = childrenInit.getChildren(account.getUser_id(), pageNo, pageSize, nameOrId, sortBy, sortDir);
            request.setAttribute("childrenList", searchResponse.getContent());
            request.setAttribute("totalElements", searchResponse.getTotalElements());
            request.setAttribute("pageNo", pageNo);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("sortBy", sortBy);
            request.setAttribute("sortDir", sortDir);
            request.setAttribute("nameOrId", nameOrId);
            request.getRequestDispatcher("/landing/customer/ChildrenList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            if (e instanceof SQLException) {
                request.setAttribute("error", "Database error occurred. Please try again later.");
            } else {
                request.setAttribute("error", "An error occurred while fetching children. Please try again.");
            }
            request.getRequestDispatcher("/landing/customer/ChildrenList.jsp").forward(request, response);
        }
    }

    // CHANGE: Updated to handle the ?add=true case explicitly
    private void handleChildDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String childIdParam = request.getParameter("childId");
        String addParam = request.getParameter("add");

        if ("true".equals(addParam)) {
            // For adding a new child, set child as null to show an empty form
            request.setAttribute("child", null);
            request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
        } else if (childIdParam != null && !childIdParam.isEmpty()) {
            int childId = Integer.parseInt(childIdParam);
            Children child = childrenInit.getChildById(childId);
            if (child != null && child.getUser_id() == ((User) request.getSession().getAttribute("account")).getUser_id()) {
                request.setAttribute("child", child);
                request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing childId or add parameter");
        }
    }

    private void handleAddChild(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }

        String name = request.getParameter("children_name");
        String genderParam = request.getParameter("children_gender");
        String ageParam = request.getParameter("children_age");

        if (name == null || name.trim().isEmpty() || genderParam == null || ageParam == null) {
            request.setAttribute("error", "Missing required parameters");
            request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
            return;
        }

        try {
            boolean gender = Boolean.parseBoolean(genderParam);
            int age = Integer.parseInt(ageParam);
            
            if (age < 0 || age > 18) {
                request.setAttribute("error", "Age must be between 0 and 18");
                request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
                return;
            }

            Children child = new Children(0, account.getUser_id(), name, gender, age);
            boolean success = childrenInit.addChild(child);
            
            if (success) {
                response.sendRedirect(request.getContextPath() + "/children/list");
            } else {
                request.setAttribute("error", "Failed to add child");
                request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid age format");
            request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while adding the child");
            request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
        }
    }

    private void handleUpdateChild(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }

        String childIdParam = request.getParameter("children_id");
        String name = request.getParameter("children_name");
        String genderParam = request.getParameter("children_gender");
        String ageParam = request.getParameter("children_age");

        if (childIdParam == null || name == null || name.trim().isEmpty() || genderParam == null || ageParam == null) {
            request.setAttribute("error", "Missing required parameters");
            request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
            return;
        }

        try {
            int childId = Integer.parseInt(childIdParam);
            boolean gender = Boolean.parseBoolean(genderParam);
            int age = Integer.parseInt(ageParam);
            
            if (age < 0 || age > 18) {
                request.setAttribute("error", "Age must be between 0 and 18");
                request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
                return;
            }

            Children child = new Children(childId, account.getUser_id(), name, gender, age);
            boolean success = childrenInit.updateChild(child);
            
            if (success) {
                response.sendRedirect(request.getContextPath() + "/children/list");
            } else {
                request.setAttribute("error", "Failed to update child");
                request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number format");
            request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while updating the child");
            request.getRequestDispatcher("/landing/customer/ChildDetail.jsp").forward(request, response);
        }
    }

    // CHANGE: Updated to return JSON response instead of redirecting
    private void handleDeleteChild(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User account = (User) session.getAttribute("account");
        
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/loginnavigation");
            return;
        }

        String childIdParam = request.getParameter("childId");
        if (childIdParam == null) {
            sendJsonResponse(response, false, "Missing child ID");
            return;
        }

        try {
            int childId = Integer.parseInt(childIdParam);
            Children child = childrenInit.getChildById(childId);
            
            if (child == null || child.getUser_id() != account.getUser_id()) {
                sendJsonResponse(response, false, "Child not found or unauthorized");
                return;
            }

            boolean success = childrenInit.deleteChild(childId);
            sendJsonResponse(response, success, success ? "Child deleted successfully" : "Failed to delete child");
        } catch (NumberFormatException e) {
            sendJsonResponse(response, false, "Invalid child ID format");
        } catch (Exception e) {
            sendJsonResponse(response, false, "An error occurred while deleting the child");
        }
    }

    // CHANGE: Added helper method to send JSON responses
    private void sendJsonResponse(HttpServletResponse response, boolean success, String message) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"success\": " + success + ", \"message\": \"" + message + "\"}");
        out.flush();
    }

    @Override
    public String getServletInfo() {
        return "Handles children-related operations";
    }
}