/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author win
 */
public class UserDAO {

    public User checkAccountExit(String username) {
    try {
        String sql = "SELECT * FROM users WHERE user_email = ?"; // Sửa câu lệnh SQL cho MySQL
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
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
        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex); // In lỗi nếu có
    }
    return null; // Trả về null nếu không tìm thấy người dùng
}

   public User login(String user_email, String password) {
        try {
            String sql = "SELECT * FROM users WHERE user_email = ? AND user_password = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

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
        }
        return null; // Return null if no user found or error occurs
    }


public void register(String username, Boolean gender, String address, String password, String email, String phone) {
    String sql = "INSERT INTO user (user_fullname, user_gender, user_address, user_password, user_email, user_phone, role_id, user_status, user_image) "
            + "VALUES (?, ?, ?, ?, ?, ?, 4, 0, null)"; // Sửa bảng và cột theo cú pháp MySQL

    try {
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setBoolean(2, gender);
        ps.setString(3, address);
        ps.setString(4, password);
        ps.setString(5, email);
        ps.setString(6, phone);

        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); // In lỗi nếu có
    }
}


public void changePassword(String user_email, String newpassword) {
    try {
        String sql = "UPDATE user SET user_password = ? WHERE user_email = ?";
        Connection conn = new DBContext().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newpassword); // Set mật khẩu mới
        ps.setString(2, user_email);  // Set email người dùng
        ps.executeUpdate();           // Thực thi câu lệnh update
    } catch (Exception e) {
        System.out.println("Error: " + e); // In lỗi nếu có
    }
}

}