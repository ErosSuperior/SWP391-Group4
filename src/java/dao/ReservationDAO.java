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
import model.Reservation;
import model.User;

/**
 *
 * @author thang
 */
public class ReservationDAO extends DBContext {

    private Connection connection;

    public ReservationDAO() {
        try {
            this.connection = getConnection(); // Initialize the connection
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public String getUserImg(int userId) {
        String query = "SELECT user_image FROM users WHERE user_id = ?";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return "no img"; // Return "no img" if the connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("user_image"); // Return the user image
            }
        } catch (Exception e) {
        }

        return "noimg"; // Default return if no user image is found
    }

    public List<Reservation> getReservation(int offset, int limit, String nameOrId, int userId, String sortBy, String sortDir) {
        List<Reservation> reservations = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * "
                + "FROM reservation "
                + "WHERE 1=1 "
                + "AND reservation_status != 2 "
                + "AND user_id = ?");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (note LIKE ? OR reservation_id = ?)");
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return reservations; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            preparedStatement.setInt(index++, userId);

            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
            }

            preparedStatement.setInt(index++, limit);
            preparedStatement.setInt(index++, offset);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reservation s = new Reservation();
                s.setReservation_id(rs.getInt("reservation_id"));
                s.setCreated_date(rs.getDate("created_date"));
                s.setStatus(rs.getInt("reservation_status"));
                s.setNote(rs.getString("note"));
                s.setTotal_price(rs.getFloat("total_price"));
                reservations.add(s);
            }
        } catch (Exception e) {
        }
        return reservations;
    }

    public int countReservation(String nameOrId, int userId) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM reservation "
                + "WHERE 1=1 "
                + "AND reservation_status != 2 "
                + "AND user_id = ?");
        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (note LIKE ? OR reservation_id = ?)");
        }

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return 0; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;
            preparedStatement.setInt(index++, userId);
            if (nameOrId != null && !nameOrId.isEmpty()) {
                preparedStatement.setString(index++, "%" + nameOrId + "%");
                preparedStatement.setString(index++, nameOrId);
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

    public List<User> getAllUserInfo() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE 1=1 ";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return users; // Return "no img" if the connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUser_id(rs.getInt("user_id"));
                s.setUser_fullname(rs.getString("user_fullname"));
                s.setUser_gender(rs.getBoolean("user_gender"));
                s.setUser_address(rs.getString("user_address"));
                s.setUser_email(rs.getString("user_email"));
                s.setUser_phone(rs.getString("user_phone"));
                s.setUser_image(rs.getString("user_image"));
                users.add(s);
            }
        } catch (Exception e) {
        }

        return users;
    }

    public List<Reservation> getIdtoCompare() {
        List<Reservation> res = new ArrayList<>();
        String query = "SELECT r.*, rd.staff_id AS staff_id "
                + "FROM reservation r "
                + "LEFT JOIN reservation_detail rd "
                + "ON r.reservation_id = rd.reservation_id "
                + "WHERE 1=1 "
                + "AND reservation_status != 2 ";
        if (connection == null) {
            System.err.println("Database connection is not available.");
            return res; // Return empty list if connection failed
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reservation s = new Reservation();
                s.setReservation_id(rs.getInt("reservation_id"));
                s.setUser_id(rs.getInt("user_id"));
                s.setStaff_id(rs.getInt("staff_id"));
                res.add(s);
            }
        } catch (Exception e) {
        }
        return res;
    }
}
