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
import model.blog;
import model.setting;

/**
 *
 * @author admin
 */
public class AdminDAO {
    public List<blog> getBlogsIndexNew() {
        List<blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select TOP 9 * from blogs order by blog_created_date";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog blog = new  blog(rs.getInt("blog_id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("bi"), rs.getDate("blog_created_date"), rs.getInt("category_id"), rs.getString("detail"), rs.getString("blog_image"), rs.getBoolean("view_able"));
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<setting> getSettingList() {
        List<setting> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from setting";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                setting settinglist = new setting(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getBoolean(5)
                );
                list.add(settinglist);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    public setting getSettingByID(int id) {
        
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [setting] where setting_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new setting(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getBoolean(5)
                );
                
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void setStatus(int i, int id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE setting\n"
                    + "SET setting_status = ?\n"
                    + "WHERE [setting_id] = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
