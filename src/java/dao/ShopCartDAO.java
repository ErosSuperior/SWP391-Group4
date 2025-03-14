/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.ReservationDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopCartDAO extends DBContext {

    private Connection connection; // Tạo đối tượng connection

    public ShopCartDAO() {
        try {
            this.connection = getConnection(); // Khởi tạo kết nối
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public int getReservationID(int user_id) { // Lấy ReservationID của người dùng
        int a = 0; // Tạo biến lưu trữ
        String sql = "SELECT `reservation_id`\n"
                + "FROM `reservation`\n"
                + "WHERE user_id = ? AND reservation_status = 0;"; // Câu lệnh truy vấn ReservationID
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id); // Đặt giá trị cho dấu "?"
            ResultSet rs = st.executeQuery(); // Thực thi
            if (rs.next()) {
                a = rs.getInt("reservation_id"); // Lưu kết quả
            } else {
                a = -1;
            }
        } catch (SQLException e) {
            System.out.println(e); // In ngoại lệ nếu lỗi
        }
        return a; // Trả kết quả
    }

    public List<ReservationDetail> getReservationDetail(int reservationID) {
        List<ReservationDetail> list = new ArrayList<>();
        String sql = "SELECT "
                + "    rd.reservation_detail_id, "
                + "    rd.reservation_id, "
                + "    rd.service_id, "
                + "    s.service_title, "
                + "    rd.price, "
                + "    rd.quantity, " // Added this if quantity exists in the database
                + "    si.image_link, "
                + "    c.category_id, "
                + "    c.category_name, "
                + "    rd.staff_id, "
                + "    rd.begin_time, "
                + "    rd.slot, "
                + "    rd.children_id "
                + "FROM service s "
                + "INNER JOIN reservation_detail rd ON rd.service_id = s.service_id "
                + "INNER JOIN service_image si ON s.service_id = si.service_id "
                + "INNER JOIN category c ON s.category_id = c.category_id "
                + "WHERE rd.reservation_id = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, reservationID);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ReservationDetail c = new ReservationDetail(
                            rs.getInt("reservation_detail_id"),
                            rs.getInt("reservation_id"),
                            rs.getInt("service_id"),
                            rs.getString("service_title"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"), // Ensure this exists in the SQL query
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getInt("staff_id"),
                            rs.getTimestamp("begin_time"), // Fixed date handling
                            rs.getInt("slot"),
                            rs.getInt("children_id"),
                            rs.getString("image_link")
                    );
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Better error handling
        }

        return list;
    }

    public boolean updateQuantity(String quantity, String service_id, int user_id) { // Hàm update số lượng sản phẩm trong giỏ hàng
        String sql = "UPDATE reservation_detail rd\n"
                + "JOIN reservation r ON rd.reservation_id = r.reservation_id\n"
                + "SET rd.quantity = ?\n"
                + "WHERE rd.service_id = ?\n"
                + "AND r.reservation_status = 0 AND r.user_id = ?;"; // Câu lệnh update sản phẩm 

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, quantity); // Set giá trị cho dấu "?"
            st.setString(2, service_id); // Set giá trị cho dấu "?"
            st.setInt(3, user_id); // Set giá trị cho dấu "?"

            int rowsAffected = st.executeUpdate(); // Lấy số dòng thay đổi

            if (rowsAffected > 0) { // Kiểm tra xem nếu có thay đổi thì trả về true 
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; // Ko thì false 
    }

    public int totalService(int userid) { // Hàm tính tổng số sản phẩm ở trong giỏ hàng
        int a = 0;
        String sql = "SELECT SUM(rd.quantity) AS total_quantity\n" // Câu lệnh tính tổng 
                + "FROM reservation_detail rd\n"
                + "JOIN reservation r ON rd.reservation_id = r.reservation_id\n"
                + "WHERE r.reservation_status = 0\n"
                + "AND r.user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userid); // Gán giá trị cho dấu "?"
            ResultSet rs = st.executeQuery(); // Thực thi câu lệnh 
            if (rs.next()) {
                a = rs.getInt("total_quantity"); // Lưu giá trị tổng số sản phẩm
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return a; // Trả về tổng số lượng sản phẩm
    }

    public boolean checkoutService(String total, String note, String name, String phone, String email, String address, int user_id) { // Hàm update các thông tin ở trong reservation khi ng dùng check out
        String sql = "UPDATE `reservation`\n" // Câu lệnh update thông tin
                + "SET\n"
                + "`total_price` = ?,\n"
                + "`reservation_status` = 1,\n"
                + "`created_date` = NOW(),\n"
                + "`receiver_address` = ?,\n"
                + "`receiver_number` = ?,\n"
                + "`receiver_email` = ?,\n"
                + "`receiver_name` = ?,\n"
                + "`note` = ?\n"
                + "WHERE `reservation_status` = 0 AND user_id = ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, total);
            st.setString(2, address);
            st.setString(3, phone);
            st.setString(4, email);
            st.setString(5, name);
            st.setString(6, note);
            st.setInt(7, user_id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteService(String delete_id, int user_id) { // Hàm xóa service trong giỏ hàng của ng dùng
        String sql = "DELETE reservation_detail\n"
                + "FROM reservation_detail\n"
                + "JOIN reservation ON reservation.reservation_id = reservation_detail.reservation_id\n"
                + "WHERE reservation_detail.service_id = ? \n"
                + "  AND reservation.reservation_status = 0\n"
                + "  AND reservation.user_id = ?;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, delete_id);
            st.setInt(2, user_id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean checkReservationTime(int reservationID) { // Hàm xóa service trong giỏ hàng của ng dùng
        String sql = "SELECT * FROM reservation_detail \n"
                + "WHERE begin_time < CURDATE() AND reservation_id = ? LIMIT 1;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, reservationID);

            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean deleteAllService(String delete_id) { // Hàm xóa service trong đơn hàng của ng dùng
        String sql = "DELETE FROM reservation_detail WHERE reservation_id = ?";

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
}
