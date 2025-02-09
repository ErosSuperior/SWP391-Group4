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
import model.userRole;

/**
 *
 * @author win
 */
public class RoleDAO {
    public List<userRole> getAllRole() {
        List<userRole> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from role";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userRole role = new userRole(rs.getInt(1), rs.getString(2));
                list.add(role);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
