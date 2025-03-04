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
                + "AND reservation_status != 0 "
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
                + "AND reservation_status != 0 "
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

    public List<Reservation> getReservationDetailonResId(int reservationId) {
        List<Reservation> reservations = new ArrayList<>();

        String query = "SELECT r.*, rd.* "
                + "FROM reservation r "
                + "LEFT JOIN reservation_detail rd ON r.reservation_id = rd.reservation_id "
                + "WHERE 1=1 "
                + "AND r.reservation_id = ? ";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return reservations;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int index = 1;
            preparedStatement.setInt(index++, reservationId);
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

    public int totalService(int userid) { // Hàm tính tổng số sản phẩm ở trong giỏ hàng
        int a = 0;
        String sql = "SELECT SUM(rd.quantity) AS total_quantity "
                + "FROM reservation_detail rd "
                + "JOIN reservation r ON rd.reservation_id = r.reservation_id "
                + "WHERE r.reservation_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userid); // Gán giá trị cho dấu "?"
            ResultSet rs = st.executeQuery(); // Thực thi câu lệnh 
            if (rs.next()) {
                a = rs.getInt("total_quantity");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a;
    }

    public User getReceiverInfo(int reservation_id) {
        User users = null; // Set to null initially

        String query = "SELECT receiver_name, receiver_address, receiver_email, receiver_number "
                + "FROM reservation WHERE reservation_id = ?";

        if (connection == null) {
            System.err.println("Database connection is not available.");
            return null; // Return null if there's no connection
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reservation_id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) { // Use `if` instead of `while`
                    users = new User();
                    users.setUser_fullname(rs.getString("receiver_name"));
                    users.setUser_address(rs.getString("receiver_address"));
                    users.setUser_email(rs.getString("receiver_email"));
                    users.setUser_phone(rs.getString("receiver_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }

        return users; // Return the user object
    }

    public int getPaymentStatus(int reservation_id) {
        String query = "SELECT payment_status "
                + "FROM reservation WHERE reservation_id = ?";
        int status = -1;
        if (connection == null) {
            System.err.println("Database connection is not available.");
            return 0; // Return null if there's no connection
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, reservation_id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                preparedStatement.setInt(1, reservation_id);
                if (rs.next()) { // Use `if` instead of `while`
                    status = rs.getInt("payment_status");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }

        return status; // Return the user object

    }

    public boolean deleteService(String delete_id) { // Hàm xóa service trong giỏ hàng của ng dùng
        String sql = "DELETE reservation_detail\n"
                + " FROM reservation_detail\n"
                + " JOIN reservation ON reservation.reservation_id = reservation_detail.reservation_id\n"
                + " WHERE reservation_detail.reservation_detail_id = ? ;\n";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, delete_id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateQuantity(String quantity, String service_id, String detail_id) { // Hàm update số lượng sản phẩm trong giỏ hàng
        String sql = "UPDATE reservation_detail rd\n"
                + "JOIN reservation r ON rd.reservation_id = r.reservation_id\n"
                + "SET rd.quantity = ?\n"
                + "WHERE rd.service_id = ?\n"
                + "AND rd.reservation_detail_id = ?;"; // Câu lệnh update sản phẩm 

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, quantity); // Set giá trị cho dấu "?"
            st.setString(2, service_id); // Set giá trị cho dấu "?"
            st.setString(3, detail_id); // Set giá trị cho dấu "?"

            int rowsAffected = st.executeUpdate(); // Lấy số dòng thay đổi

            if (rowsAffected > 0) { // Kiểm tra xem nếu có thay đổi thì trả về true 
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; // Ko thì false 
    }

    public int countServiceInReservation(int reservation_id) {
        String sql = "SELECT COUNT(*) AS detail_count FROM reservation_detail WHERE reservation_id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, reservation_id); // Set the value for the placeholder "?"

            ResultSet rs = st.executeQuery(); // Execute the query
            if (rs.next()) {
                return rs.getInt("detail_count"); // Retrieve the count from the result set
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return 0; // Return 0 if there's an error or no records found
    }

    public void updateReservationStatus(int reservation_id, int status) {
        String sql = "UPDATE reservation SET reservation_status = ? WHERE reservation_id = ?;";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, status);
            st.setInt(2, reservation_id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Print error details for debugging
        }
    }

    public String getReservationNote(int reservation_id) {
        String sql = "SELECT note FROM reservation WHERE reservation_id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, reservation_id); // Set the value for the placeholder "?"

            ResultSet rs = st.executeQuery(); // Execute the query
            if (rs.next()) {
                return rs.getString("note"); // Retrieve the count from the result set
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print the exception for debugging
        }
        return null;
    }

    public boolean updateReservation(String reservation_id, String note, String receiver_address,
            String receiver_number, String receiver_email,
            String receiver_name, String total_price) {
        String sql = "UPDATE reservation SET "
                + "note = ?, "
                + "receiver_address = ?, "
                + "receiver_number = ?, "
                + "receiver_email = ?, "
                + "receiver_name = ?, "
                + "total_price = ?, "
                + "created_date = CURDATE() "
                + // Sets the current date
                "WHERE reservation_id = ?;";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, note);
            st.setString(2, receiver_address);
            st.setString(3, receiver_number);
            st.setString(4, receiver_email);
            st.setString(5, receiver_name);
            st.setString(6, total_price); 
            st.setString(7, reservation_id);

            return st.executeUpdate() > 0; // Returns true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace(); // Print error for debugging
        }
        return false;
    }

    public boolean hasReservationWithService(int userId, String serviceId) {
        String sql = "SELECT COUNT(*) FROM reservation r " +
                     "JOIN reservation_detail rd ON r.reservation_id = rd.reservation_id " +
                     "WHERE r.user_id = ? AND rd.service_id = ? AND r.reservation_status = 3";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, serviceId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, return true
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Default return false if no match found
    }
    
}
