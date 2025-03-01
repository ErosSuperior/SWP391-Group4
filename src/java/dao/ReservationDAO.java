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
import java.util.Date;
import java.util.List;
import model.Reservation;
import model.Service;
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

    public boolean checkCartExist(int userId) {
        String sql = "SELECT 1 FROM reservation WHERE user_id = ? AND reservation_status = 0 LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // If there's a result, return true (cart exists)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // No cart found
    }

    public boolean addCart(int userId) {
        String sql = "INSERT INTO reservation (user_id, total_price, reservation_status, payment_status, created_date) "
                + "VALUES (?, 0.00, 0, 0, CURDATE())";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Insert failed
    }

    public boolean addToCart(int reservationId, int serviceId, float price, int staffId, Date beginTime, int quantity, int category) {
        String sql = "INSERT INTO reservation_detail (reservation_id, service_id, price, quantity, category_id, staff_id, begin_time, slot, children_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, NULL, NULL)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            stmt.setInt(2, serviceId);
            stmt.setDouble(3, price);
            stmt.setInt(4, quantity);
            stmt.setInt(5, category);
            stmt.setInt(6, staffId);
            stmt.setDate(7, new java.sql.Date(beginTime.getTime()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Returns true if insertion is successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Insert failed
    }

    public Integer getCartByUserID(int userId) {
        String query = "SELECT reservation_id FROM reservation WHERE user_id = ? AND reservation_status = 0";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("reservation_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no reservation is found
    }

    public int getCartQuantity(int reservationId, int serviceId) {
        int quantity = 0;
        String query = "SELECT quantity FROM reservation_detail WHERE reservation_id = ? AND service_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, reservationId);
            ps.setInt(2, serviceId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }

    public void updateCartQuantity(int reservationId, int serviceId, int quantity) {
        String query = "UPDATE reservation_detail SET quantity = ? WHERE reservation_id = ? AND service_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, quantity);
            ps.setInt(2, reservationId);
            ps.setInt(3, serviceId);

            int updatedRows = ps.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Cart quantity updated successfully.");
            } else {
                System.out.println("No matching record found to update.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> getReservation(int offset, int limit, String nameOrId, int userId, int day, int month, int year, String sortBy, String sortDir) {
        List<Reservation> reservations = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * "
                + "FROM reservation "
                + "WHERE 1=1 "
                + "AND reservation_status != 2 "
                + "AND user_id = ?");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (note LIKE ? OR reservation_id = ?)");
        }

        if (year != -1) {
            query.append(" AND YEAR(created_date) = ?");
        }

        if (month != -1) {
            query.append(" AND MONTH(created_date) = ?");
        }

        if (day != -1) {
            query.append(" AND DAY(created_date) = ?");
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

            if (year != -1) {
                preparedStatement.setInt(index++, year);
            }

            if (month != -1) {
                preparedStatement.setInt(index++, month);
            }

            if (day != -1) {
                preparedStatement.setInt(index++, day);
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

    public int countReservation(String nameOrId, int userId, int day, int month, int year) {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) "
                + "FROM reservation "
                + "WHERE 1=1 "
                + "AND reservation_status != 2 "
                + "AND user_id = ?");
        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (note LIKE ? OR reservation_id = ?)");
        }

        if (year != -1) {
            query.append(" AND YEAR(created_date) = ?");
        }

        if (month != -1) {
            query.append(" AND MONTH(created_date) = ?");
        }

        if (day != -1) {
            query.append(" AND DAY(created_date) = ?");
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

            if (year != -1) {
                preparedStatement.setInt(index++, year);
            }

            if (month != -1) {
                preparedStatement.setInt(index++, month);
            }

            if (day != -1) {
                preparedStatement.setInt(index++, day);
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

    public List<Reservation> getReservationDetailonId(int userId) {
        List<Reservation> reservations = new ArrayList<>();

        String query = "SELECT r.*, rd.* "
                + "FROM reservation r "
                + "LEFT JOIN reservation_detail rd ON r.reservation_id = rd.reservation_id "
                + "WHERE 1=1 "
                + "AND r.user_id = ? ";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return reservations;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int index = 1;
            preparedStatement.setInt(index++, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Reservation s = new Reservation();
                s.setReservation_id(rs.getInt("rd.reservation_id"));
                s.setQuantity(rs.getInt("rd.quantity"));
                s.setDetail_id(rs.getInt("rd.reservation_detail_id"));
                s.setPrice(rs.getFloat("rd.price"));
                s.setBegin_time(rs.getDate("rd.begin_time"));
                s.setService_id(rs.getInt("rd.service_id"));
                reservations.add(s);
            }
        } catch (Exception e) {
        }

        return reservations;
    }

    public List<Service> getAllServiceInfo() {
        List<Service> srvc = new ArrayList<>();
        String query = "SELECT * "
                + "FROM service "
                + "WHERE 1=1";
        if (connection == null) {
            System.err.println("Database connection is not available.");
            return srvc;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Service s = new Service();
                s.setServiceId(rs.getInt("service_id"));
                s.setServiceTitle(rs.getString("service_title"));
                srvc.add(s);
            }
        } catch (Exception e) {
        }
        return srvc;
    }
}
