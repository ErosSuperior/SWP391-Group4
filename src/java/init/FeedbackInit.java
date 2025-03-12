/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package init;

import dao.FeedbackDAO;
import java.util.List;
import model.Feedback;
import model.SearchResponse;

/**
 *
 * @author thang
 */
public class FeedbackInit {
    FeedbackDAO feedbackDao = new FeedbackDAO();
    
    public SearchResponse<Feedback> getServiceFeedback(int pageNo, int pageSize, String nameOrId , int serviceId) {
        int offset = pageNo * pageSize;
        List<Feedback> users = feedbackDao.getActiveServiceFeedback(offset, pageSize, nameOrId,serviceId,"created_date", "ASC");
        int totalElements = feedbackDao.countServiceFeedback(nameOrId,serviceId);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
    
    public SearchResponse<Feedback> getAllServiceFeedback(int pageNo, int pageSize, String nameOrId , int serviceId, int status, int ratestar, String sortBy, String sortDir) {
        int offset = pageNo * pageSize;
        List<Feedback> users = feedbackDao.getAllServiceFeedback(offset, pageSize, nameOrId,serviceId,  status,  ratestar,sortBy, sortDir);
        int totalElements = feedbackDao.countAllServiceFeedback(nameOrId,serviceId,  status,  ratestar);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
}
