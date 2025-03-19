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
import context.DBContext;
import model.CustomerHistory;
import model.User;

public class CustomerDAO extends DBContext {

    private Connection connection; // Tạo đối tượng connection

    public CustomerDAO() {
        try {
            this.connection = getConnection(); // Khởi tạo kết nối
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public List<User> getAllCustomer(String search, String sort, String by, String status, int page, int pageSize) {
        // Hàm lấy danh sách khách hàng dựa trên các tiêu chí tìm kiếm, sắp xếp, phân trang

        List<User> list = new ArrayList<>(); // Danh sách chứa các đối tượng User
        StringBuilder sql = new StringBuilder("SELECT `users`.`user_id`,\n"
                + "    `users`.`user_fullname`,\n"
                + "    `users`.`user_gender`,\n"
                + "    `users`.`user_address`,\n"
                + "    `users`.`user_password`,\n"
                + "    `users`.`user_email`,\n"
                + "    `users`.`user_phone`,\n"
                + "    `users`.`role_id`,\n"
                + "    `users`.`user_status`,\n"
                + "    `users`.`user_image`\n"
                + "FROM `users`\n"
                + "WHERE `role_id` = 4 "); // Lọc chỉ những người dùng có vai trò khách hàng (role_id = 4)

        // Nếu có giá trị tìm kiếm, thêm điều kiện tìm kiếm vào câu lệnh SQL
        if (search != null && !search.isBlank()) {
            sql.append(" AND (user_fullname LIKE ? OR user_email LIKE ? OR user_phone LIKE ? OR user_address LIKE ? )");
        }

        // Nếu có trạng thái (status), thêm điều kiện lọc trạng thái
        if (status != null && !status.isEmpty()) {
            sql.append(" AND user_status = ?");
        }

        // Nếu có yêu cầu sắp xếp, thêm ORDER BY vào SQL
        if (sort != null && !sort.isEmpty()) {
            sql.append(" ORDER BY ").append(sort);

            // Xác định thứ tự sắp xếp (ASC hoặc DESC)
            if ("DESC".equalsIgnoreCase(by)) {
                sql.append(" DESC");
            } else {
                sql.append(" ASC ");
            }
        }

        // Thêm phân trang: Giới hạn số lượng kết quả trả về (LIMIT) và xác định vị trí bắt đầu (OFFSET)
        sql.append(" LIMIT ? OFFSET ?");

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int index = 1; // Chỉ số tham số trong PreparedStatement

            // Nếu có giá trị tìm kiếm, gán các tham số tìm kiếm vào câu lệnh SQL
            if (search != null && !search.isBlank()) {
                String searchPattern = "%" + search + "%"; // Tạo chuỗi tìm kiếm LIKE
                st.setString(index++, searchPattern);
                st.setString(index++, searchPattern);
                st.setString(index++, searchPattern);
                st.setString(index++, searchPattern);
            }

            // Nếu có trạng thái, gán vào SQL
            if (status != null && !status.isEmpty()) {
                st.setString(index++, status);
            }

            // Gán giá trị phân trang
            st.setInt(index++, pageSize); // Số lượng khách hàng trên mỗi trang
            st.setInt(index++, (page - 1) * pageSize); // OFFSET: vị trí bắt đầu lấy dữ liệu

            ResultSet rs = st.executeQuery(); // Thực thi truy vấn

            // Duyệt qua danh sách kết quả và tạo danh sách User
            while (rs.next()) {
                User c = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_fullname"),
                        rs.getBoolean("user_gender"),
                        rs.getString("user_address"),
                        rs.getString("user_password"),
                        rs.getString("user_email"),
                        rs.getString("user_phone"),
                        rs.getInt("role_id"),
                        rs.getBoolean("user_status"),
                        rs.getString("user_image")
                );
                list.add(c); // Thêm vào danh sách
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); // In lỗi nếu có
        }

        return list; // Trả về danh sách khách hàng
    }

    public int countCustomer(String search, String status) {
        // Hàm đếm số lượng khách hàng theo các tiêu chí tìm kiếm và trạng thái

        int total = 0; // Biến lưu tổng số khách hàng
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM users WHERE role_id = 4 ");
        // Lọc những người dùng có vai trò khách hàng (role_id = 4)

        // Nếu có tìm kiếm, thêm điều kiện tìm kiếm vào câu lệnh SQL
        if (search != null && !search.isBlank()) {
            sql.append(" AND (user_fullname LIKE ? OR user_email LIKE ? OR user_phone LIKE ? OR user_address LIKE ? )");
        }

        // Nếu có trạng thái, thêm điều kiện lọc theo trạng thái
        if (status != null && !status.isEmpty()) {
            sql.append(" AND user_status = ?");
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());
            int index = 1; // Chỉ số tham số trong PreparedStatement

            // Nếu có tìm kiếm, gán giá trị tìm kiếm vào câu lệnh SQL
            if (search != null && !search.isBlank()) {
                String searchPattern = "%" + search + "%"; // Mẫu tìm kiếm với LIKE
                st.setString(index++, searchPattern);
                st.setString(index++, searchPattern);
                st.setString(index++, searchPattern);
                st.setString(index++, searchPattern);
            }

            // Nếu có trạng thái, gán giá trị vào SQL
            if (status != null && !status.isEmpty()) {
                st.setString(index++, status);
            }

            ResultSet rs = st.executeQuery(); // Thực thi truy vấn

            // Lấy kết quả đếm số lượng khách hàng
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage()); // In lỗi nếu có
        }

        return total; // Trả về tổng số khách hàng
    }

    public boolean addCustomer(String fullName, String gender, String address, String password, String email, String phone, String image) {
        // Hàm thêm khách hàng mới vào cơ sở dữ liệu

        String sql = "INSERT INTO `users`\n" // Chèn dữ liệu vào bảng users
                + "(`user_fullname`,\n"
                + "`user_gender`,\n"
                + "`user_address`,\n"
                + "`user_password`,\n"
                + "`user_email`,\n"
                + "`user_phone`,\n"
                + "`role_id`,\n"
                + "`user_status`,\n"
                + "`user_image`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,4,0,?);";
        // `role_id = 4` => Xác định đây là khách hàng
        // `user_status = 0` => Mặc định là chưa kích hoạt tài khoản

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Gán giá trị vào các tham số trong câu lệnh SQL
            st.setString(1, fullName);
            st.setString(2, gender);
            st.setString(3, address);
            st.setString(4, password); // Nên mã hóa mật khẩu trước khi lưu vào DB
            st.setString(5, email);
            st.setString(6, phone);
            st.setString(7, image); // Ảnh đại diện của người dùng

            int rowsInserted = st.executeUpdate(); // Thực hiện lệnh INSERT

            return rowsInserted > 0; // Nếu có dòng nào được chèn, trả về true

        } catch (SQLException e) {
            System.err.println(e.getMessage()); // In lỗi SQL nếu có
            return false; // Trả về false nếu thêm khách hàng thất bại
        }
    }

    public boolean isEmailExists(String email) {
        // Hàm kiểm tra xem email đã tồn tại trong bảng users hay chưa

        String sql = "SELECT COUNT(*) FROM users WHERE user_email = ?";
        // Đếm số lượng bản ghi có email trùng khớp

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email); // Gán giá trị email vào câu truy vấn
            ResultSet rs = st.executeQuery(); // Thực thi truy vấn

            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu COUNT(*) > 0 tức là email đã tồn tại
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage()); // In lỗi SQL nếu có
        }

        return false; // Nếu có lỗi hoặc không tìm thấy, trả về false
    }

    public boolean deleteCustomer(String user_id) {
        // Hàm xóa khách hàng dựa trên user_id

        String sql = "DELETE FROM users WHERE user_id = ?";
        // Câu truy vấn xóa người dùng có user_id tương ứng

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user_id); // Gán giá trị user_id vào câu truy vấn

            int rowsAffected = st.executeUpdate(); // Thực thi câu lệnh DELETE
            return rowsAffected > 0; // Nếu có ít nhất 1 dòng bị xóa, trả về true

        } catch (SQLException e) {
            System.err.println(e.getMessage()); // In ra lỗi SQL nếu có
        }

        return false; // Trả về false nếu không thể xóa
    }

    public User getCustomerDetail(String user_id) {
        // Hàm lấy thông tin chi tiết của một khách hàng dựa trên user_id

        String sql = "SELECT `users`.`user_id`,\n"
                + "    `users`.`user_fullname`,\n"
                + "    `users`.`user_gender`,\n"
                + "    `users`.`user_address`,\n"
                + "    `users`.`user_password`,\n"
                + "    `users`.`user_email`,\n"
                + "    `users`.`user_phone`,\n"
                + "    `users`.`role_id`,\n"
                + "    `users`.`user_status`,\n"
                + "    `users`.`user_image`\n"
                + "FROM `users`\n"
                + "WHERE user_id = ? ";

        try {
            // Tạo PreparedStatement để tránh SQL Injection
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user_id); // Gán giá trị user_id vào truy vấn SQL

            ResultSet rs = st.executeQuery(); // Thực thi truy vấn

            if (rs.next()) {
                // Nếu có dữ liệu, tạo đối tượng User từ kết quả truy vấn
                User c = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_fullname"),
                        rs.getBoolean("user_gender"),
                        rs.getString("user_address"),
                        rs.getString("user_password"),
                        rs.getString("user_email"),
                        rs.getString("user_phone"),
                        rs.getInt("role_id"),
                        rs.getBoolean("user_status"),
                        rs.getString("user_image")
                );
                return c; // Trả về đối tượng User chứa thông tin khách hàng
            }
        } catch (SQLException e) {
            System.out.println(e); // Bắt lỗi SQL và in ra console
        }

        return null; // Trả về null nếu không tìm thấy user hoặc có lỗi xảy ra
    }

    public boolean updateCustomer(String userId, String fullName, String gender, String address, String phone) {
        // Hàm cập nhật thông tin khách hàng dựa trên user_id

        String sql = "UPDATE `users` SET "
                + "`user_fullname` = ?, " // Cập nhật tên khách hàng
                + "`user_gender` = ?, " // Cập nhật giới tính
                + "`user_address` = ?, " // Cập nhật địa chỉ
                + "`user_phone` = ? " // Cập nhật số điện thoại
                + "WHERE `user_id` = ?";   // Điều kiện: user_id tương ứng

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Gán giá trị vào các tham số trong câu lệnh SQL
            st.setString(1, fullName);    // Gán giá trị tên đầy đủ
            st.setString(2, gender);      // Gán giá trị giới tính (0 hoặc 1)
            st.setString(3, address);     // Gán giá trị địa chỉ
            st.setString(4, phone);       // Gán giá trị số điện thoại
            st.setString(5, userId);      // Gán giá trị user_id vào điều kiện WHERE

            // Thực hiện câu lệnh UPDATE, trả về số dòng bị ảnh hưởng
            int rowsUpdated = st.executeUpdate();

            // Nếu ít nhất một dòng bị ảnh hưởng thì cập nhật thành công
            return rowsUpdated > 0;

        } catch (SQLException e) {
            // Xử lý lỗi SQL và in ra thông báo lỗi
            System.err.println(e);
            return false;
        }
    }

    public List<CustomerHistory> getCustomerHistoryByUserId(String user_id) {
        // Hàm lấy danh sách lịch sử chỉnh sửa của một khách hàng dựa vào user_id

        List<CustomerHistory> list = new ArrayList<>();  // Danh sách chứa lịch sử chỉnh sửa

        // Câu lệnh SQL lấy thông tin lịch sử chỉnh sửa của khách hàng
        String sql = "SELECT \n"
                + "    customer_history.history_id,\n" // ID của lịch sử
                + "    customer_history.user_id,\n" // ID của người dùng
                + "    customer_history.user_fullname,\n" // Họ và tên của khách hàng tại thời điểm chỉnh sửa
                + "    customer_history.user_gender,\n" // Giới tính của khách hàng
                + "    customer_history.user_address,\n" // Địa chỉ của khách hàng
                + "    customer_history.user_phone,\n" // Số điện thoại khách hàng
                + "    customer_history.updated_by_id,  \n" // ID người thực hiện cập nhật
                + "    users.user_fullname AS updated_by_name,  \n" // Tên người cập nhật
                + "    customer_history.updated_date\n" // Ngày cập nhật
                + "FROM \n"
                + "    customer_history\n"
                + "JOIN \n"
                + "    users ON customer_history.updated_by_id = users.user_id \n" // Kết hợp bảng `users` để lấy tên người cập nhật
                + "WHERE \n"
                + "    customer_history.user_id = ?;";  // Lọc theo user_id được truyền vào

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Gán tham số user_id vào PreparedStatement để tránh SQL Injection
            st.setString(1, user_id);

            ResultSet rs = st.executeQuery();  // Thực thi câu truy vấn

            // Duyệt qua từng dòng dữ liệu trong kết quả trả về
            while (rs.next()) {
                CustomerHistory c = new CustomerHistory(
                        rs.getInt("history_id"), // ID lịch sử thay đổi
                        rs.getInt("user_id"), // ID người dùng
                        rs.getString("user_fullname"), // Họ tên người dùng tại thời điểm cập nhật
                        rs.getBoolean("user_gender"), // Giới tính
                        rs.getString("user_address"), // Địa chỉ
                        rs.getString("user_phone"), // Số điện thoại
                        rs.getInt("updated_by_id"), // ID người thực hiện chỉnh sửa
                        rs.getString("updated_by_name"),// Tên người cập nhật
                        rs.getDate("updated_date") // Ngày cập nhật
                );

                // Thêm đối tượng vào danh sách kết quả
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println( e);
        }

        return list;  // Trả về danh sách lịch sử chỉnh sửa của khách hàng
    }

    public boolean updateHistory(String user_id, String name, String gender, String phone, String address, int updated_by_id) {
        // Hàm lưu lịch sử thay đổi của khách hàng vào bảng customer_history.

        String sql = "INSERT INTO `customer_history`\n"
                + "(`user_id`,\n" // ID khách hàng
                + "`user_fullname`,\n" // Họ tên khách hàng (tại thời điểm cập nhật)
                + "`user_gender`,\n" // Giới tính khách hàng
                + "`user_address`,\n" // Địa chỉ khách hàng
                + "`user_phone`,\n" // Số điện thoại khách hàng
                + "`updated_by_id`,\n" // ID của người thực hiện cập nhật
                + "`updated_date`)\n" // Ngày cập nhật (CURRENT_DATE lấy ngày hiện tại)
                + "VALUES\n"
                + "(?,?,?,?,?,?,CURRENT_DATE);"; // `CURRENT_DATE` tự động lấy ngày hiện tại

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            // Set các giá trị vào câu lệnh SQL (PreparedStatement giúp tránh SQL Injection)
            st.setString(1, user_id);        // Gán user_id của khách hàng
            st.setString(2, name);           // Gán tên khách hàng
            st.setString(3, gender);         // Gán giới tính (0 - Nữ, 1 - Nam)
            st.setString(4, address);        // Gán địa chỉ khách hàng
            st.setString(5, phone);          // Gán số điện thoại
            st.setInt(6, updated_by_id);     // Gán ID người thực hiện cập nhật

            // Thực hiện câu lệnh INSERT và kiểm tra số dòng được thêm vào
            int rowsInserted = st.executeUpdate();
            return rowsInserted > 0;  // Nếu có ít nhất một dòng được thêm, trả về true

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;  // Trả về false nếu có lỗi xảy ra
        }
    }

}
