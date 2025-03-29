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
import model.User;

/**
 *
 * @author ADMIN
 */
public class StaffDAO extends DBContext{
    
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
}
