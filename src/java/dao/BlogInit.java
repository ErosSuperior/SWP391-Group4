/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.List;
import model.SearchResponse;
import model.Blog;
import dao.BlogDAO;
/**
 *
 * @author thang
 */
public class BlogInit {
    BlogDAO blogDAO = new BlogDAO();
    
    public SearchResponse<Blog> getBlog(int pageNo, int pageSize, String nameOrId , int categoryId, int status) {
        int offset = pageNo * pageSize;
        List<Blog> users = blogDAO.getBlog(offset, pageSize, nameOrId,categoryId,status,"b.blog_id", "ASC");
        int totalElements = blogDAO.countBlog(nameOrId,categoryId,status);
        return new SearchResponse<>(totalElements, users, pageNo, pageSize);
    }
}
