/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.setting;

/**
 *
 * @author win
 */
public class SettingDAO {

    public setting getsettingById(int i) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from setting\n"
                    + "where setting_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                setting setting = new setting(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getBoolean(5));
                return setting;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        SettingDAO s = new SettingDAO();
        System.out.println(s.getsettingById(1).getSetting_name());
    }

    public void updateSetting(setting setting) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [dbo].[setting]\n"
                    + "   SET [setting_name] = ?\n"
                    + "      ,[setting_value] = ?\n"
                    + "      ,[setting_description] = ?\n"
                    + "      ,[setting_status] = ?\n"
                    + " WHERE setting_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, setting.getSetting_name());
            ps.setInt(2, setting.getSetting_value());
            ps.setString(3, setting.getSetting_description());
            ps.setBoolean(4, setting.isSetting_status());
            ps.setInt(5, setting.getSetting_id());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addNewSetting(setting setting) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [dbo].[setting]\n"
                    + "           ([setting_name]\n"
                    + "           ,[setting_value]\n"
                    + "           ,[setting_description]\n"
                    + "           ,[setting_status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, setting.getSetting_name());
            ps.setInt(2, setting.getSetting_value());
            ps.setString(3, setting.getSetting_description());
            ps.setBoolean(4, setting.isSetting_status());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
