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
import model.children;

/**
 *
 * @author admin
 */
public class ChildrenDAO {

    public List<children> getAllChildren() {
        List<children> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [category]";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                children chil = new children(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
                list.add(chil);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public static List<children> getAllChildrenByID(int id) {
        List<children> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from children where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                children chil = new children(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
                list.add(chil);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ChildrenDAO chil = new ChildrenDAO();
        List<children> list = chil.getAllChildrenByID(47);
        System.out.println(list.get(0).getChildren_name());
    }

    public void addNewChildren(children c) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [dbo].[children]\n"
                    + "           ([user_id]\n"
                    + "           ,[children_name]\n"
                    + "           ,[children_gender]\n"
                    + "           ,[children_age])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,(YEAR(GETDATE()))-?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getUser_id());
            ps.setString(2, c.getChildren_name());
            ps.setBoolean(3, c.isChildren_gender());
            ps.setInt(4, c.getChildren_age());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
