/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import context.DBContext;
/**
 *
 * @author thang
 */
public class BlogDAO extends DBContext{
    private Connection connection;

    public BlogDAO() {
        try {
            this.connection = getConnection(); // Initialize the connection
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }
    
    public List<Blog> getBlog(int offset, int limit, String nameOrId, int categoryId, int status, String sortBy, String sortDir) {
        List<Blog> blogs = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT b.*, bu.user_fullname AS blog_author, bc.category_id AS blog_category "
                + "FROM blogs b "
                + "LEFT JOIN users bu ON b.user_id = bu.user_id "
                + "LEFT JOIN category bc ON b.category_id = bc.category_id "
                + "WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (b.title LIKE ? OR b.blog_id = ?)");
        }

        if (status != -1) {
            query.append(" AND (b.view_able = ?)");
        }
        
        if (categoryId != -1) {
            query.append(" AND (b.category_id = ?)");
        }
        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return blogs; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }
            
            if (status != -1) {
                preparedStatement.setInt(index++, status);
            }
            
            if (categoryId != -1) {
                preparedStatement.setInt(index++, categoryId);
            }

            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index++, offset);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogId(rs.getInt("blog_id"));
                s.setBlogTitle(rs.getString("title"));
                s.setBlogUserId(rs.getInt("user_id"));
                s.setAuthorName(rs.getString("blog_author"));
                s.setBlodCategory(rs.getInt("category_id"));
                s.setBlogBi(rs.getString("bi"));
                s.setBlogDetail(rs.getString("detail"));
                s.setBlogImage(rs.getString("blog_image"));
                s.setBlogStatus(rs.getInt("view_able"));
                blogs.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return blogs;
    }
    
    public int countBlog(String nameOrId, int categoryId, int status) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM blogs b "
                + "LEFT JOIN users bu ON b.user_id = bu.user_id "
                + "LEFT JOIN category bc ON b.category_id = bc.category_id "
                + "WHERE 1=1 ");
        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (b.title LIKE ? OR b.blog_id = ?)");
        }

        if (status != -1) {
            query.append(" AND (b.view_able = ?)");
        }
        
        if (categoryId != -1) {
            query.append(" AND (b.category_id = ?)");
        }

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return 0; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }
            if (categoryId != -1) {
                preparedStatement.setInt(index++, categoryId);
            }
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return count;
    }
    
    public boolean updateStatus(int blogId, int newStatus) {
        String q = "UPDATE blogs "
                + "SET view_able = ? "
                + "WHERE blog_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(q)) {

            preparedStatement.setInt(1, newStatus);
            preparedStatement.setInt(2, blogId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            return false; // Return false in case of error
        }
    }
}
