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
import model.CategoryRevenue;
import model.FeedbackStatistics;
import model.ReservationStatistic;
import model.ReservationStatistics;
import model.User;
import model.UserFull;

public class DashboardDAO extends DBContext {

    private Connection connection; // Tạo đối tượng connection

    public DashboardDAO() {
        try {
            this.connection = getConnection(); // Khởi tạo kết nối
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public List<ReservationStatistics> getReservationStatistics(int selectedYear) {
        List<ReservationStatistics> list = new ArrayList<>();

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        String sql = "SELECT\n"
                + "    MONTH(created_date) AS month,\n"
                + "    COUNT(CASE WHEN reservation_status = 3 THEN 1 END) AS success_count,\n"
                + "    COUNT(CASE WHEN reservation_status = 1 THEN 1 END) AS submitted_count,\n"
                + "    COUNT(CASE WHEN reservation_status = 4 THEN 1 END) AS cancelled_count\n"
                + "FROM reservation\n"
                + "WHERE YEAR(created_date) = ?\n"
                + "GROUP BY MONTH(created_date)\n"
                + "ORDER BY MONTH(created_date);";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, selectedYear);

            ResultSet rs = st.executeQuery();

            int[] successCounts = new int[12];
            int[] submittedCounts = new int[12];
            int[] cancelledCounts = new int[12];

            while (rs.next()) {
                int monthIndex = rs.getInt("month") - 1;
                successCounts[monthIndex] = rs.getInt("success_count");
                submittedCounts[monthIndex] = rs.getInt("submitted_count");
                cancelledCounts[monthIndex] = rs.getInt("cancelled_count");
            }

            for (int i = 0; i < 12; i++) {
                list.add(new ReservationStatistics(
                        months[i],
                        successCounts[i],
                        cancelledCounts[i],
                        submittedCounts[i]
                ));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<CategoryRevenue> getRevenueByCategory(String startDate, String endDate) {
        List<CategoryRevenue> categoryRevenues = new ArrayList<>();

        String sql = "SELECT c.category_name, SUM(rd.price * rd.quantity) AS revenue "
                + "FROM reservation r "
                + "JOIN reservation_detail rd ON r.reservation_id = rd.reservation_id "
                + "JOIN service s ON rd.service_id = s.service_id "
                + "JOIN category c ON s.category_id = c.category_id "
                + "WHERE r.reservation_status = 3 ";

        // Kiểm tra nếu startDate và endDate không null hoặc trống
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql += "AND r.created_date BETWEEN ? AND ? ";
        } else {
            // Nếu không có startDate và endDate, sử dụng 7 ngày gần nhất
            sql += "AND r.created_date >= CURDATE() - INTERVAL 7 DAY ";
        }

        sql += "GROUP BY c.category_name"; // Nhóm theo category

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            // Set start date và end date cho PreparedStatement
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                st.setString(1, startDate);
                st.setString(2, endDate);
            }

            // Thực thi câu lệnh SQL và nhận kết quả trả về
            ResultSet rs = st.executeQuery();

            // Duyệt qua kết quả trả về và tạo đối tượng CategoryRevenue để thêm vào danh sách
            while (rs.next()) {
                String categoryName = rs.getString("category_name");
                double revenue = rs.getDouble("revenue");
                CategoryRevenue categoryRevenue = new CategoryRevenue(categoryName, revenue);
                categoryRevenues.add(categoryRevenue);
            }
        } catch (SQLException e) {
            System.out.println(e); // Ghi log nếu có lỗi
        }

        return categoryRevenues;
    }

    public List<UserFull> getNewlyCustomer(String startDate, String endDate) {
        List<UserFull> list = new ArrayList<>();

        String sql = "SELECT `user_id`,\n"
                + "    `users`.`user_fullname`,\n"
                + "    `users`.`user_gender`,\n"
                + "    `users`.`user_address`,\n"
                + "    `users`.`user_password`,\n"
                + "    `users`.`user_email`,\n"
                + "    `users`.`user_phone`,\n"
                + "    `users`.`role_id`,\n"
                + "    `users`.`user_status`,\n"
                + "    `users`.`user_image`,\n"
                + "    `users`.`created_date`\n"
                + "FROM `users`\n"
                + "WHERE `role_id` = 4 ";

        // Kiểm tra nếu startDate và endDate không null hoặc trống
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql += "AND `users`.created_date BETWEEN ? AND ? ";
        } else {
            // Nếu không có startDate và endDate, sử dụng 7 ngày gần nhất
            sql += "AND `users`.created_date >= CURDATE() - INTERVAL 7 DAY ";
        }

        sql += "LIMIT 5;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            // Set start date và end date cho PreparedStatement
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                st.setString(1, startDate);
                st.setString(2, endDate);
            }

            // Thực thi câu lệnh SQL và nhận kết quả trả về
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                UserFull c = new UserFull(
                        rs.getInt("user_id"),
                        rs.getString("user_fullname"),
                        rs.getBoolean("user_gender"),
                        rs.getString("user_address"),
                        rs.getString("user_password"),
                        rs.getString("user_email"),
                        rs.getString("user_phone"),
                        rs.getInt("role_id"),
                        rs.getBoolean("user_status"),
                        rs.getString("user_image"),
                        rs.getDate("created_date")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e); // Ghi log nếu có lỗi
        }

        return list;
    }

    public int getNewlyRegisteredCustomerCount(String startDate, String endDate) {
        int count = 0; // Biến để đếm số lượng người dùng mới

        // Câu lệnh SQL để đếm số lượng người dùng mới
        String sql = "SELECT COUNT(u.user_id) AS user_count "
                + "FROM users u "
                + "WHERE `role_id` = 4 ";

        // Kiểm tra nếu startDate và endDate không null hoặc trống
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql += "AND u.created_date BETWEEN ? AND ? "; // Nếu có startDate và endDate
        } else {
            // Nếu không có startDate và endDate, sử dụng 7 ngày gần nhất
            sql += "AND u.created_date >= CURDATE() - INTERVAL 7 DAY ";
        }

        try {
            // Kết nối đến cơ sở dữ liệu và chuẩn bị câu lệnh SQL
            PreparedStatement st = connection.prepareStatement(sql);

            // Set start date và end date cho PreparedStatement (nếu có)
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                st.setString(1, startDate);
                st.setString(2, endDate);
            }

            // Thực thi câu lệnh SQL và nhận kết quả trả về
            ResultSet rs = st.executeQuery();

            // Nếu có kết quả trả về, lấy số lượng người dùng
            if (rs.next()) {
                count = rs.getInt("user_count"); // Lấy giá trị đếm từ cột 'user_count'
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage()); // In ra lỗi nếu có
        }

        return count; // Trả về số lượng người dùng mới
    }

    public double getAverageRating(String startDate, String endDate) {
        double averageRating = 0;
        String sql = "SELECT AVG(rate_Star) AS average_rating FROM feedback WHERE status = 1 ";

        // Kiểm tra nếu startDate và endDate không null hoặc trống
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql += "AND created_date BETWEEN ? AND ? "; // Nếu có startDate và endDate
        } else {
            // Nếu không có startDate và endDate, sử dụng 7 ngày gần nhất
            sql += "AND created_date >= CURDATE() - INTERVAL 7 DAY ";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            // Set start date và end date cho PreparedStatement nếu có
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                st.setString(1, startDate);
                st.setString(2, endDate);
            }

            // Thực thi câu lệnh SQL và nhận kết quả trả về
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                averageRating = rs.getDouble("average_rating");
            }
        } catch (SQLException e) {
            System.out.println(e); // Ghi log nếu có lỗi
        }

        return averageRating;
    }

    public List<FeedbackStatistics> getFeedbackStatistics(String startDate, String endDate) {
        List<FeedbackStatistics> list = new ArrayList<>();

        String sql = "SELECT \n"
                + "    s.service_id, \n"
                + "    s.service_title, \n"
                + "    GROUP_CONCAT(si.image_link) AS image_links, \n"
                + "    AVG(f.rate_Star) AS average_rating, \n"
                + "    COUNT(f.feedback_id) AS feedback_count\n"
                + "FROM feedback f\n"
                + "JOIN service s ON f.service_id = s.service_id\n"
                + "JOIN service_image si ON s.service_id = si.service_id\n"
                + "WHERE f.status = 1 ";

        // Kiểm tra nếu startDate và endDate không null hoặc trống
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql += "AND f.created_date BETWEEN ? AND ? ";
        } else {
            // Nếu không có startDate và endDate, sử dụng 7 ngày gần nhất
            sql += "AND f.created_date >= CURDATE() - INTERVAL 7 DAY ";
        }

        sql += "GROUP BY s.service_id, s.service_title";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            // Set start date và end date cho PreparedStatement
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                st.setString(1, startDate);
                st.setString(2, endDate);
            }

            // Thực thi câu lệnh SQL và nhận kết quả trả về
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                FeedbackStatistics c = new FeedbackStatistics(
                        rs.getInt("service_id"),
                        rs.getString("service_title"),
                        rs.getString("image_links"),
                        rs.getDouble("average_rating"),
                        rs.getInt("feedback_count")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e); // Ghi log nếu có lỗi
        }

        return list;
    }

    public ReservationStatistic getReservationStatistics(String startDate, String endDate) {

        String sql = "SELECT\n"
                + "    COUNT(CASE WHEN r.reservation_id > 0 THEN 1 END) AS total_reservation,  \n"
                + "   COALESCE(AVG(rd.price * rd.quantity), 0) AS avg_cost,  \n"
                + "    COUNT(CASE WHEN r.reservation_status = 3 THEN 1 END) AS success_reservation, \n"
                + "    COUNT(CASE WHEN r.reservation_status = 4 THEN 1 END) AS cancelled_reservation  \n"
                + "FROM reservation r\n"
                + "JOIN reservation_detail rd ON r.reservation_id = rd.reservation_id\n"
                + "WHERE r.reservation_status > 0  ";  // Điều kiện thời gian đã chọn (startDate và endDate)
        
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            sql += "AND r.created_date BETWEEN ? AND ? ";
        } else {
            // Nếu không có startDate và endDate, sử dụng 7 ngày gần nhất
            sql += "AND r.created_date >= CURDATE() - INTERVAL 7 DAY ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, startDate);
            st.setString(2, endDate);

            // Thực thi câu lệnh SQL và nhận kết quả trả về
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                ReservationStatistic stats = new ReservationStatistic(
                        rs.getInt("total_reservation"),
                        rs.getDouble("avg_cost"),
                        rs.getInt("success_reservation"),
                        rs.getInt("cancelled_reservation")
                );
                return stats;
            }

        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi nếu có vấn đề với kết nối hoặc truy vấn
        }

        return null;
    }
}
