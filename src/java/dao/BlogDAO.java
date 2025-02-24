package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import context.DBContext;


public class BlogDAO extends DBContext {

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
        StringBuilder query = new StringBuilder("SELECT b.*, bu.user_fullname AS blog_author, bc.category_id AS blog_category, bc.category_name AS blog_category_name "
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
                s.setBlogCategory(rs.getInt("category_id"));
                s.setBlogCategoryName(rs.getString("blog_category_name"));
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
            if (status != -1) {
                preparedStatement.setInt(index++, status);
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

    public List<Blog> getTop3LatestBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT b.*, bu.user_fullname AS blog_author "
                + "FROM blogs b "
                + "LEFT JOIN users bu ON b.user_id = bu.user_id "
                + "WHERE b.view_able = 1 "
                + "ORDER BY b.blog_created_date DESC "
                + "LIMIT 3";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("blog_id"));
                blog.setBlogTitle(rs.getString("title"));
                blog.setBlogUserId(rs.getInt("user_id"));
                blog.setAuthorName(rs.getString("blog_author"));
                blog.setBlogCategory(rs.getInt("category_id"));
                blog.setBlogBi(rs.getString("bi"));
                blog.setBlogDetail(rs.getString("detail"));
                blog.setBlogImage(rs.getString("blog_image"));
                blog.setBlogStatus(rs.getInt("view_able"));
                blog.setBlogCreatedDate(rs.getDate("blog_created_date"));

                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    public List<Blog> getActiveCategory() {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT * FROM category WHERE 1=1 ORDER BY category_id DESC";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return blogs; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogCategory(rs.getInt("category_id"));
                s.setBlogCategoryName(rs.getString("category_name"));
                blogs.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return blogs;
    }

    public List<Blog> getAllAuthor() {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT * FROM users WHERE 1=1 AND role_id != 4 ORDER BY user_id ASC";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return blogs; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Blog s = new Blog();
                s.setBlogUserId(rs.getInt("user_id"));
                s.setAuthorName(rs.getString("user_fullname"));
                blogs.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return blogs;
    }

    public boolean addBlog(Blog blog) {
        String query = "INSERT INTO blogs (user_id, title, blog_created_date, category_id, detail, blog_image, view_able) "
                + "VALUES (?, ?,CURDATE(), ?, ?, ?, 1)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, blog.getBlogUserId());
            preparedStatement.setString(2, blog.getBlogTitle());
            preparedStatement.setInt(3, blog.getBlogCategory());
            preparedStatement.setString(4, blog.getBlogDetail());
            preparedStatement.setString(5, blog.getBlogImage());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBlog(Blog blog) {
        String query = "UPDATE blogs SET title = ?, category_id = ?, detail = ?, blog_image = ?, user_id = ? "
                + "WHERE blog_id = ?";
//
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, blog.getBlogTitle());
            preparedStatement.setInt(2, blog.getBlogCategory());
            preparedStatement.setString(3, blog.getBlogDetail());
            preparedStatement.setString(4, blog.getBlogImage());
            preparedStatement.setInt(5, blog.getBlogUserId());
            preparedStatement.setInt(6, blog.getBlogId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
