package init;

import java.util.List;
import model.SearchResponse;
import dao.CategoryDAO;
import model.Category;

public class CategoryInit {
    CategoryDAO categoryDAO = new CategoryDAO();

    public SearchResponse<Category> getCategory(int pageNo, int pageSize, String nameOrId) throws Exception {
        int offset = pageNo * pageSize;
        List<Category> categories = categoryDAO.getCategories(offset, pageSize, nameOrId, "category_id", "ASC");
        int totalElements = categoryDAO.countCategories(nameOrId);
        return new SearchResponse<>(totalElements, categories, pageNo, pageSize);
    }
}