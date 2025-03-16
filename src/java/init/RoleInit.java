package init;

import java.util.List;
import model.SearchResponse;
import dao.RoleDAO;
import model.userRole;

public class RoleInit {
    private final RoleDAO roleDAO = new RoleDAO();

    public SearchResponse<userRole> getRoles(int pageNo, int pageSize, String nameOrId) throws Exception {
        int offset = pageNo * pageSize;
        List<userRole> roles = roleDAO.getRoles(offset, pageSize, nameOrId, "role_id", "ASC");
        int totalElements = roleDAO.countRoles(nameOrId);
        return new SearchResponse<>(totalElements, roles, pageNo, pageSize);
    }
}