/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package init;
import dao.StaffDAO;
import java.util.List;
import model.SearchResponse;
import model.User;
/**
 *
 * @author ADMIN
 */
public class StaffInit {
    StaffDAO staffdao = new StaffDAO();
    
    public SearchResponse<User> getAllStaff(int pageNo, int pageSize, String nameOrId, int status) {
        int offset = pageNo * pageSize;
        List<User> users = staffdao.getAllStaff(offset, pageSize, nameOrId, status,"user_id", "ASC");
        int totalElements = staffdao.countAllStaff(nameOrId,status);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
}
