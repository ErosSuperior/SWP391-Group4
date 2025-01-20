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
import model.category;
import model.service;


/**
 *
 * @author win
 */
public class CategoryDAO {
    public List<category> getAllCategory() {
        List<category> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [category]";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 category cate = new category(rs.getInt("category_id"), rs.getString("category_name"), rs.getString("icon"));
                 list.add(cate);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    public static void main(String[] args) {
        CategoryDAO ser = new CategoryDAO();
        List<category> list = ser.getAllCategory();
        System.out.println(list.get(0).getCategory_id());
    }
}
