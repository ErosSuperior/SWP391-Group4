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
import model.image;
import model.medicine;
import model.medicineCategory;
import model.service;

/**
 *
 * @author win
 */
public class MedicineDAO {

    public static void main(String[] args) {
        MedicineDAO m = new MedicineDAO();
        System.out.println(m.getAllMedicineByCate(1).get(0).getMedicine_name());
    }

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

    public medicine getMedicineById(int id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from medicine\n"
                    + "where medicine_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                medicine m = new medicine(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6));
                return m;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<medicine> getNext3Medicine(int amount) {
        List<medicine> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * from medicine\n"
                    + "ORDER BY medicine_id\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT 3 ROWS ONLY";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, amount);
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

    public List<medicineCategory> getAllCate() {
        List<medicineCategory> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from categoryMedicine";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                medicineCategory mc = new medicineCategory(rs.getInt(1), rs.getString(2));
                list.add(mc);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<medicine> getAllMedicineByCate(int cate_id) {
        List<medicine> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from medicine\n"
                    + "where medicine_cateId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cate_id);
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

    public List<medicine> getAllMedicineByTxt(String txt) {
        List<medicine> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from medicine\n"
                    + "where medicine_name like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%"+txt+"%");
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
}
