/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package init;
import java.util.List;
import model.SearchResponse;
import dao.UserDAO;
import model.User;

public class UserInit {
    UserDAO userDAO = new UserDAO();
    
    public SearchResponse<User> getUser(int pageNo, int pageSize, String nameOrId, int status, int role_id) throws Exception {
        int offset = pageNo * pageSize;
        List<User> users = userDAO.getUser(offset, pageSize, nameOrId,role_id,status,"u.user_id", "ASC");
        int totalElements = userDAO.countUser(nameOrId,role_id,status);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
}
