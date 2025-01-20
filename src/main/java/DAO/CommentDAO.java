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
import model.comment_load;
import model.comment_servicedetail;
import model.setting;

/**
 *
 * @author truon
 */
public class CommentDAO {

    public List<comment_servicedetail> getAllComment() {
        List<comment_servicedetail> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from comment";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comment_servicedetail settinglist = new comment_servicedetail(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4)
                );
                list.add(settinglist);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        CommentDAO cm = new CommentDAO();
        System.out.println(cm.getAllComment());
       // cm.insetContentComment("Nguyen Ngoc Hoan", "we are ngheo");
    }

    
    public List<comment_load> getAllLoadComment() {
        List<comment_load> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select u.user_fullname, u.user_image, c.comment_content,"
                    + " c.comment_date from comment c inner join [user] u on c.comment_createByUser  = u.user_fullname";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comment_load settinglist = new comment_load(
                        rs.getString("user_fullname"),
                        rs.getString("user_image"),
                        rs.getString("comment_content"),
                        rs.getDate("comment_date")
                );
                list.add(settinglist);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void insetContentComment(String name, String contentComment) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [dbo].[Comment]\n"
                    + "           ([comment_content]\n"
                    + "           ,[comment_date]\n"
                    + "           ,[comment_createByUser])\n"
                    + "     VALUES\n"
                    + "           (?,\n"
                    + "           GETDATE(),\n"
                    + "          ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, contentComment);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

     
}
