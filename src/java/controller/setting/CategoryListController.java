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
import dao.CategoryDAO;
import init.CategoryInit;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.User;

@WebServlet(name = "CategoryListController", urlPatterns = {
    "/admin/categoryList",
    "/admin/addcategory",
    "/admin/editcategory",
    "/admin/managecategory",
    "/admin/deletecategory"
})
public class CategoryListController extends HttpServlet {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final CategoryInit categoryInit = new CategoryInit();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User account = checkSession(request, response);
        if (account == null) {
            return; // Stop further processing if user is not logged in
        }
        String url = request.getServletPath();
        switch (url) {
            case "/admin/categoryList" -> {
                try {
                    handleCategoryList(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case "/admin/addcategory" ->
                request.getRequestDispatcher("/landing/CategoryDetail.jsp").forward(request, response);
            case "/admin/editcategory" ->
                handleEditCategoryForm(request, response);
            case "/admin/deletecategory" ->
                handleDeleteCategory(request, response);
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
            case "/admin/managecategory" -> {
                String submit = request.getParameter("submit");
                try {
                    if ("add".equalsIgnoreCase(submit)) {
                        addCategory(request, response);
                    } else {
                        editCategory(request, response);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void handleCategoryList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String pageNoParam = request.getParameter("pageNo");
        String nameOrId = request.getParameter("nameOrId");
        int pageNo = (pageNoParam != null && !pageNoParam.isEmpty()) ? Integer.parseInt(pageNoParam) : 0;
        int pageSize = 3;

        SearchResponse<Category> searchResponse = categoryInit.getCategory(pageNo, pageSize, nameOrId);

        request.setAttribute("allCategories", searchResponse.getContent());
        request.setAttribute("totalElements", searchResponse.getTotalElements());
        request.setAttribute("pageNo", pageNo);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/landing/CategoryList.jsp").forward(request, response);
    }

    private void handleEditCategoryForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryIdParam = request.getParameter("categoryId");
        if (categoryIdParam != null && !categoryIdParam.trim().isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdParam);
                Category category = categoryDAO.getCategoryById(categoryId);
                if (category != null) {
                    request.setAttribute("category", category);
                    request.getRequestDispatcher("/landing/CategoryDetail.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                Logger.getLogger(CategoryListController.class.getName()).log(Level.SEVERE, "Invalid category ID", e);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/categoryList");
    }

    private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        String status = request.getParameter("status");
        if (categoryId != null && !categoryId.isEmpty() && status != null && !status.isEmpty()) {

            categoryDAO.updateStatusCategory(categoryId, status);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID is required");
        }
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        String icon = request.getParameter("icon");

        if (categoryName == null || categoryName.trim().isEmpty()) {
            request.setAttribute("message", "Category name is required");
            request.getRequestDispatcher("/landing/CategoryDetail.jsp").forward(request, response);
            return;
        }

        categoryDAO.addCategory(categoryName, icon);
        response.sendRedirect(request.getContextPath() + "/admin/categoryList");
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryIdParam = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        String icon = request.getParameter("icon");

        if (categoryIdParam == null || categoryIdParam.trim().isEmpty()
                || categoryName == null || categoryName.trim().isEmpty()) {
            request.setAttribute("message", "Required fields cannot be empty");
            request.getRequestDispatcher("/landing/CategoryDetail.jsp").forward(request, response);
            return;
        }

        try {
            int categoryId = Integer.parseInt(categoryIdParam);
            categoryDAO.updateCategory(categoryId, categoryName, icon);
            response.sendRedirect(request.getContextPath() + "/admin/categoryList");
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid category ID");
            request.getRequestDispatcher("/landing/CategoryDetail.jsp").forward(request, response);
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
