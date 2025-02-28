package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDAO {

    // Phương thức kiểm tra tài khoản đã tồn tại hay chưa
    public User checkAccountExit(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM users WHERE user_email = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                User account = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getString(10)
                );
                return account;
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    // Phương thức đăng nhập
    public User login(String user_email, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM users WHERE user_email = ? AND user_password = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user_email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                User account = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getString(10)
                );
                return account;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Database login error", ex);
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    // Phương thức đăng ký người dùng mới
    public void register(String fullname, boolean gender, String address, String password,
            String email, String phone, int roleId, boolean status, String image) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO users (user_fullname, user_gender, user_address, user_password, user_email, user_phone, role_id, user_status, user_image) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setBoolean(2, gender);
            ps.setString(3, address);
            ps.setString(4, password); // Không sử dụng mã hóa mật khẩu
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setInt(7, roleId);
            ps.setBoolean(8, status);
            ps.setString(9, image);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User registered successfully!");
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void changePassword(String user_email, String newpassword) {
        try {
            String sql = "UPDATE users SET user_password = ? WHERE user_email = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newpassword); // Set mật khẩu mới
            ps.setString(2, user_email);  // Set email người dùng
            ps.executeUpdate();           // Thực thi câu lệnh update
        } catch (Exception e) {
            System.out.println("Error: " + e); // In lỗi nếu có
        }
    }

    public List<User> getAllStaffNotBusy() {
        List<User> staffList = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role_id = 2 AND user_status != 3";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setUser_fullname(rs.getString("user_fullname"));
                user.setUser_gender(rs.getBoolean("user_gender"));
                user.setUser_address(rs.getString("user_address"));
                user.setUser_phone(rs.getString("user_phone"));
                user.setRole_id(rs.getInt("role_id"));
                user.setUser_status(rs.getBoolean("user_status"));
                user.setUser_image(rs.getString("user_image"));
                staffList.add(user);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return staffList;
    }

}
