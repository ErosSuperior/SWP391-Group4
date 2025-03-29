/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.User;

/**
 *
 * @author ADMIN
 */
public class StaffDAO extends DBContext {

    private Connection connection;

    public StaffDAO() {
        try {
            this.connection = getConnection(); // Initialize the connection
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public List<User> getAllStaff(int offset, int limit, String nameOrId, int status, String sortBy, String sortDir) {
        List<User> staff = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * "
                + "FROM users u "
                + "WHERE 1=1 "
                + "AND role_id = 3 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (user_fullname LIKE ? OR user_id = ?)");
        }

        if (status != -1) {
            query.append(" AND (user_status = ?)");
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            if (status != -1) {
                preparedStatement.setInt(index++, status);
            }

            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index++, offset);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUser_id(rs.getInt("user_id"));
                s.setUser_fullname(rs.getString("user_fullname"));
                s.setUser_email(rs.getString("user_email"));
                s.setUser_gender(rs.getBoolean("user_gender"));
                s.setUser_phone(rs.getString("user_phone"));
                s.setUser_image(rs.getString("user_image"));
                s.setUser_address(rs.getString("user_address"));
                s.setUser_status(rs.getBoolean("user_status"));
                staff.add(s);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return staff;
    }

    public int countAllStaff(String nameOrId, int status) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM users u "
                + "WHERE 1=1 "
                + "AND role_id = 3 ");
        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (user_fullname LIKE ? OR user_id = ?)");
        }

        if (status != -1) {
            query.append(" AND (status = ?)");
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

    public boolean checkDuplicateEmail(int staff_id, String email) {
        String query = "SELECT COUNT(*) FROM users WHERE user_email = ?";
        if (staff_id != 0) {
            query += " AND user_id != ?";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            if (staff_id != 0) {
                preparedStatement.setInt(2, staff_id);
            }
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean checkDuplicatePhone(int staff_id, String phone) {
        String query = "SELECT COUNT(*) FROM users WHERE user_phone = ?";
        if (staff_id != 0) {
            query += " AND user_id != ?";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, phone);
            if (staff_id != 0) {
                preparedStatement.setInt(2, staff_id);
            }
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean addUser(User user) {
        String query = "INSERT INTO users (user_fullname, user_gender, user_address, user_password, user_email, user_phone, role_id, user_status, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUser_fullname());
            preparedStatement.setInt(2, user.isUser_gender() ? 1 : 0);
            preparedStatement.setString(3, user.getUser_address());
            preparedStatement.setString(4, user.getUser_password());
            preparedStatement.setString(5, user.getUser_email());
            preparedStatement.setString(6, user.getUser_phone());
            preparedStatement.setInt(7, 3);
            preparedStatement.setInt(8, 0);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean editUser(User user) {
        String query = "UPDATE users SET user_fullname = ?, user_gender = ?, user_address = ?, user_phone = ? WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUser_fullname());
            preparedStatement.setInt(2, user.isUser_gender() ? 1 : 0);
            preparedStatement.setString(3, user.getUser_address());
            preparedStatement.setString(4, user.getUser_phone());
            preparedStatement.setInt(5, user.getUser_id());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public List<Category> getAllSpec(String staff_id) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT c.category_id, c.category_name, c.status, c.icon "
                + "FROM category c "
                + "JOIN specialization s ON c.category_id = s.categoryid "
                + "WHERE s.userid = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, staff_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setCategory_name(rs.getString("category_name"));
                category.setCategory_status(rs.getInt("status"));
                category.setCategory_icon(rs.getString("icon"));
                categories.add(category);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return categories;
    }

    public List<Category> getUnassignedCategories(String staff_id) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT c.category_id, c.category_name, c.status, c.icon "
                + "FROM category c "
                + "WHERE c.category_id NOT IN ( "
                + "   SELECT s.categoryid FROM specialization s WHERE s.userid = ? )";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, staff_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setCategory_name(rs.getString("category_name"));
                category.setCategory_status(rs.getInt("status"));
                category.setCategory_icon(rs.getString("icon"));
                categories.add(category);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return categories;
    }

    public boolean addSpecialization(int staffId, int categoryId) {
        String sql = "INSERT INTO specialization (userid, categoryid) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, staffId);
            preparedStatement.setInt(2, categoryId);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSpecialization(int staffId, int categoryId) {
        String sql = "DELETE FROM specialization WHERE userid = ? AND categoryid = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, staffId);
            preparedStatement.setInt(2, categoryId);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0; // Returns true if deletion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
