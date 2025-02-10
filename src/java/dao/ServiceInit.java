/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.SearchResponse;
import model.Service;
/**
 *
 * @author thang
 */
public class ServiceInit {
    ServiceDAO serviceDAO = new ServiceDAO();
    
    public SearchResponse<Service> getActiveService(int pageNo, int pageSize, String nameOrId , int categoryId, int minPrice, int maxPrice) {
        int offset = pageNo * pageSize;
        List<Service> users = serviceDAO.getActiveService(offset, pageSize, nameOrId,categoryId ,"service_created_date", "ASC", minPrice,maxPrice);
        int totalElements = serviceDAO.countActiveService(nameOrId,categoryId, minPrice,maxPrice);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
    
}
