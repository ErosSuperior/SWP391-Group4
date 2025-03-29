package init;

import dao.ChildrenDAO;
import java.util.List;
import model.Children;
import model.SearchResponse;

public class ChildrenInit {
    private final ChildrenDAO childrenDao = new ChildrenDAO();

    // Get children for a specific user with pagination and search
    public SearchResponse<Children> getChildren(int userId, int pageNo, int pageSize, String nameOrId, String sortBy, String sortDir) throws Exception {
        int offset = pageNo * pageSize;
        List<Children> children = childrenDao.getChildren(userId, offset, pageSize, nameOrId, sortBy, sortDir); // Updated to include userId
        int totalElements = childrenDao.countChildren(userId, nameOrId); // Updated to include userId
        return new SearchResponse<>(totalElements, children, pageNo, pageSize);
    }

    // Get child by ID
    public Children getChildById(int childId) {
        return childrenDao.getChildById(childId);
    }

    // Add a new child
    public boolean addChild(Children child) throws Exception { // Added return value
        return childrenDao.addChild(child);
    }

    // Update a child
    public boolean updateChild(Children child) throws Exception { // Added return value
        childrenDao.updateChild(child);
        return true; // Could improve by checking rows affected
    }

    // Delete a child
    public boolean deleteChild(int childId) throws Exception { // Added return value
        childrenDao.deleteChild(childId);
        return true; // Could improve by checking rows affected
    }
}