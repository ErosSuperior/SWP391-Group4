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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SliderHomeServices;
import model.category;
import model.listslidermanager;
import model.service;
import model.slider;
import model.sliderdetailmanager;

/**
 *
 * @author admin
 */
public class SliderDAO {

    public static int getTotalSlider() {
        List<listslidermanager> list = new ArrayList<>();
        try {
            String sql = "select count(s.slider_id) from slider s join slider_services sl on s.slider_id = sl.slider_id join service sv on sl.service_id = sv.service_id\n"
                    + "					join service_image si on s.service_id = si.service_id ";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public List<listslidermanager> getSliderByCategoryId(int category_id) {
        List<listslidermanager> list = new ArrayList<>();
        try {
            String sql = "select s.slider_id, si.image_link, s.slider_title, s.slider_status \n"
                    + "from slider s join slider_services sl on s.slider_id = sl.slider_id join service sv on sl.service_id = sv.service_id\n"
                    + "join service_image si on s.service_id = si.service_id where s.category_id = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listslidermanager listser = new listslidermanager(
                        rs.getInt("slider_id"),
                        rs.getString("image_link"),
                        rs.getString("slider_title"),
                        rs.getBoolean("slider_status")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public sliderdetailmanager sliderdetailmanagerbyID(int sid) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select s.slider_id, si.image_link, s.slider_title, s.slider_status, s.notes\n"
                    + "from slider s join slider_services sl on s.slider_id = sl.slider_id join service sv on sl.service_id = sv.service_id\n"
                    + "join service_image si on s.service_id = si.service_id where s.slider_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sliderdetailmanager ser = new sliderdetailmanager(
                        rs.getInt("slider_id"),
                        rs.getString("image_link"),
                        rs.getString("slider_title"),
                        rs.getBoolean("slider_status"),
                        rs.getString("notes")
                );
                return ser;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void editsliderDetailmanager(String title, String image, String notes, int sidd) throws Exception {
        try {
            Connection connection = new DBContext().getConnection();
            //       connection.setAutoCommit(false);
            String sql = "UPDATE [dbo].[slider]\n"
                    + "   SET [slider_title] = ?\n"
                    + "      ,[notes] = ?\n"
                    + " WHERE slider_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, notes);
            stm.setInt(3, sidd);
            stm.executeUpdate();

            String sql2 = "UPDATE [dbo].[service_image]\n"
                    + "   SET[image_link] = ?\n"
                    + " WHERE [service_id] = ?";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, image);
            stm2.setInt(2, sidd);
            stm2.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<category> getAllCategories() {
        List<category> list = new ArrayList<>();
        try {
            String sql = "select * from category";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category listser = new category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_name")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {

        }
        return list;
    }

    public List<listslidermanager> getSliderWithPagging(int page, int PAGE_SIZE) {
        List<listslidermanager> list = new ArrayList<>();
        try {
            String sql = "select s.slider_id, si.image_link, s.slider_title, s.slider_status from slider s join slider_services sl on s.slider_id = sl.slider_id join service sv on sl.service_id = sv.service_id\n"
                    + "					join service_image si on s.service_id = si.service_id order by s.slider_id offset (?-1)*? row fetch next ? rows only";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, PAGE_SIZE);
            ps.setInt(3, PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listslidermanager listser = new listslidermanager(
                        rs.getInt("slider_id"),
                        rs.getString("image_link"),
                        rs.getString("slider_title"),
                        rs.getBoolean("slider_status")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {

        }
        return list;
    }

    public void setStatus(int i, int idd) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [dbo].[slider]\n"
                    + "   SET \n"
                    + "      [slider_status] = ?\n"
                    + "      \n"
                    + " WHERE slider_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ps.setInt(2, idd);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    
    public List<listslidermanager> searchslidemanager(String txt) {
        List<listslidermanager> list = new ArrayList<>();
        try {

            String sql = "select s.slider_id, si.image_link, sv.service_title, s.slider_status from slider s join slider_services sl on s.slider_id = sl.slider_id join service sv on sl.service_id = sv.service_id\n"
                    + "					join service_image si on s.service_id = si.service_id \n"
                    + "					where\n"
                    + "					sv.service_title like ?\n"
                    + "					or\n"
                    + "                    freetext(sv.service_title,?)\n"
                    + "                    or\n"
                    + "                    CONTAINS(sv.service_title,?)";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setString(2, "\"" + txt + "*\"");
            ps.setString(3, "\"" + txt + "*\"");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listslidermanager listser = new listslidermanager(
                        rs.getInt("slider_id"),
                        rs.getString("image_link"),
                        rs.getString("service_title"),
                        rs.getBoolean("slider_status")
                );
                list.add(listser);
            }
            return list;
        } catch (Exception ex) {

        }
        return null;
    }

    public List<SliderHomeServices> getAllSliderHomePage() {
        List<SliderHomeServices> list = new ArrayList<>();
        try {
            String sql = "select s.slider_id, sv.service_id, si.image_link, sv.service_title, s.notes from slider s join slider_services sl on s.slider_id = sl.slider_id join service sv on sl.service_id = sv.service_id \n" +
"                  join service_image si on s.service_id = si.service_id where s.slider_status = 1 order by s.slider_id";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SliderHomeServices listser = new SliderHomeServices(
                        rs.getInt("slider_id"),
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("service_title"),
                        rs.getString("notes")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }
    
     public static void main(String[] args) {
        SliderDAO s = new SliderDAO();
        s.setStatus(1,1);
    }
   
}
