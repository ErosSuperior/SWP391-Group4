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
import model.Setting;

public class SettingDAO extends DBContext {

    private Connection connection; // Tạo đối tượng connection

    public SettingDAO() {
        try {
            this.connection = getConnection(); // Khởi tạo kết nối
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public List<Setting> getAllSetting() { // Lấy danh sách Setting
        List<Setting> list = new ArrayList<>(); // Tạo list để lưu dữ liệu
        String sql = "SELECT `setting`.`setting_id`,\n"
                + "    `setting`.`setting_name`,\n"
                + "    `setting`.`setting_type`,\n"
                + "    `setting`.`setting_description`,\n"
                + "    `setting`.`setting_value`,\n"
                + "    `setting`.`setting_status`\n"
                + "FROM `setting`;"; // Câu lệnh truy vấn danh sách tất cả Setting

        try {
            PreparedStatement st = connection.prepareStatement(sql); // Thiết lập kết nối

            ResultSet rs = st.executeQuery(); // Thực thi truy vấn
            while (rs.next()) {
                Setting c = new Setting(rs.getInt("setting_id"), // Tạo đối tượng 
                        rs.getString("setting_name"),
                        rs.getString("setting_type"),
                        rs.getString("setting_description"),
                        rs.getString("setting_value"),
                        rs.getString("setting_status"));
                list.add(c); // Lưu dữ liệu
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list; // Trả về danh sách
    }

    public List<Setting> get3Setting() { // Lấy 3 Setting
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT `setting`.`setting_id`,\n"
                + "       `setting`.`setting_name`,\n"
                + "       `setting`.`setting_type`,\n"
                + "       `setting`.`setting_description`,\n"
                + "       `setting`.`setting_value`,\n"
                + "       `setting`.`setting_status`\n"
                + "FROM `setting`\n"
                + "LIMIT 3;"; // Truy vấn 3 Setting

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting c = new Setting(rs.getInt("setting_id"),
                        rs.getString("setting_name"),
                        rs.getString("setting_type"),
                        rs.getString("setting_description"),
                        rs.getString("setting_value"),
                        rs.getString("setting_status"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public Setting getSettingDetail(String setting_id) { // Hàm lấy thông tin chi tiết của 1 Setting
        String sql = "SELECT `setting`.`setting_id`,\n" // Truy vấn thông qua ID
                + "    `setting`.`setting_name`,\n"
                + "    `setting`.`setting_type`,\n"
                + "    `setting`.`setting_description`,\n"
                + "    `setting`.`setting_value`,\n"
                + "    `setting`.`setting_status`\n"
                + "FROM `setting`\n"
                + "WHERE setting_id = ? ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, setting_id); // Thiết lập giá trị cho dấu "?"

            ResultSet rs = st.executeQuery(); // Thực thi
            if (rs.next()) {
                Setting c = new Setting(rs.getInt("setting_id"), // Tạo đối tượng nếu rs.next tim thấy dữ liệu
                        rs.getString("setting_name"),
                        rs.getString("setting_type"),
                        rs.getString("setting_description"),
                        rs.getString("setting_value"),
                        rs.getString("setting_status"));

                return c; // Trả về đối tượng

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null; // Ko tìm thấy trả về null 
    }

    public boolean updateSettingStatus(String setting_id) { // Hàm update Status của Setting
        String sql = "UPDATE setting\n"
                + "SET setting_status = IF(setting_status = 'Active', 'Inactive', 'Active')\n"
                + "WHERE setting_id = ?;"; // Câu lệnh update status

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, setting_id); // Set giá trị cho dấu "?"

            int rowsAffected = st.executeUpdate(); // Lấy số dòng thay đổi

            if (rowsAffected > 0) { // Kiểm tra xem nếu có thay đổi thì trả về true 
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; // Ko thì false 
    }

    public List<String> getAllType() { // Lấy tất cả các type đã tồn tại của Setting List
        List<String> list = new ArrayList<>();
        String sql = "SELECT `setting_type`\n"
                + "FROM `setting`"; // Câu truy vấn

        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("setting_type")); // Lưu dữ liệu
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list; // Trả về danh sách
    }
// Hàm tạo Setting mới
    public boolean insertSetting(String setting_name, String setting_type, String setting_description, String setting_value, String setting_status) {
        String sql = "INSERT INTO `setting`\n"
                + "(`setting_name`,\n"
                + "`setting_type`,\n"
                + "`setting_description`,\n"
                + "`setting_value`,\n"
                + "`setting_status`)\n"
                + "VALUES\n"
                + "(?,?,?,?,?);"; // Câu lệnh insert

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, setting_name); // Set giá trị cho dấu "?"
            st.setString(2, setting_type); // Set giá trị cho dấu "?"
            st.setString(3, setting_description); // Set giá trị cho dấu "?"
            st.setString(4, setting_value); // Set giá trị cho dấu "?"
            st.setString(5, setting_status); // Set giá trị cho dấu "?"

            int rowsAffected = st.executeUpdate(); // Lấy số dòng thay đổi

            if (rowsAffected > 0) { // Kiểm tra xem nếu có thay đổi thì trả về true 
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; // Ko thì false 
    }

    public boolean deleteSetting(String setting_id) { // Hàm xóa 1 Setting 
        String sql = "DELETE FROM `setting`\n"
                + "WHERE setting_id = ?;"; // Câu lệnh xóa

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, setting_id); // Set giá trị cho dấu "?"

            int rowsAffected = st.executeUpdate(); // Lấy số dòng thay đổi

            if (rowsAffected > 0) { // Kiểm tra xem nếu có thay đổi thì trả về true 
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; // Ko thì false 
    }
// Hàm update Setting
    public boolean updateSetting(String setting_id, String setting_name, String setting_type, String setting_description, String setting_value, String setting_status) {
        String sql = "UPDATE `setting`\n"
                + "SET\n"
                + "`setting_name` = ?,\n"
                + "`setting_type` = ?,\n"
                + "`setting_description` = ?,\n"
                + "`setting_value` = ?,\n"
                + "`setting_status` = ?\n"
                + "WHERE `setting_id` = ?;"; // Câu lệnh Update 

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, setting_name); // Set giá trị cho dấu "?"
            st.setString(2, setting_type); // Set giá trị cho dấu "?"
            st.setString(3, setting_description); // Set giá trị cho dấu "?"
            st.setString(4, setting_value); // Set giá trị cho dấu "?"
            st.setString(5, setting_status); // Set giá trị cho dấu "?"
            st.setString(6, setting_id); // Set giá trị cho dấu "?"

            int rowsAffected = st.executeUpdate(); // Lấy số dòng thay đổi

            if (rowsAffected > 0) { // Kiểm tra xem nếu có thay đổi thì trả về true 
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false; // Ko thì false 
    }
}
