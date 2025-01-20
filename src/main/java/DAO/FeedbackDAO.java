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
import model.feedback;
import model.medicine;

/**
 *
 * @author admin
 */
public class FeedbackDAO {

    public List<medicine> getAllMedicine() {
        List<medicine> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [medicine]";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                medicine m = new medicine(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6));
                list.add(m);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void InsertNewFeedBack(feedback c) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [dbo].[feedback]\n"
                    + "           ([user_id]\n"
                    + "           ,[reservation_id]\n"
                    + "           ,[content]\n"
                    + "           ,[name]\n"
                    + "           ,[gender]\n"
                    + "           ,[email]\n"
                    + "           ,[mobile]\n"
                    + "           ,[feedback_image]\n"
                    + "           ,[rate_Star]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getUser_id());
            ps.setInt(2, c.getReservation_id());
            ps.setString(3, c.getContent());
            ps.setString(4, c.getName());
            ps.setBoolean(5, c.isGender());
            ps.setString(6, c.getEmail());
            ps.setString(7, c.getMobile());
            ps.setString(8, c.getFeedback_image());
            ps.setInt(9, c.getRateStar());
            ps.setBoolean(10, c.isStatus());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<feedback> getAllFeedback() {
        List<feedback> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from feedback";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                feedback feed = new feedback(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getBoolean(11));
                list.add(feed);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        FeedbackDAO d = new FeedbackDAO();
        System.out.println(d.getAllFeedback().get(0).getEmail());
    }

    public feedback getFeedbackByID(int feedbackId) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from feedback where feedback_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new feedback(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getBoolean(11));
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void setFeedbackStatusOn(int feedbackId) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [feedback]\n"
                    + "   SET [status] = 0\n"
                    + " WHERE feedback_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setFeedbackStatusOff(int feedbackId) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [feedback]\n"
                    + "   SET [status] = 1\n"
                    + " WHERE feedback_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feedbackId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
