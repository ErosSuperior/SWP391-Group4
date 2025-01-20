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
import model.hotservices;
import model.image;
import model.listservicemanager;
import model.service;
import model.servicedetailmanager;

/**
 *
 * @author admin
 */
public class ServicesDAO extends DBContext {

    public List<service> getAllServices() {
        List<service> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from service\n"
                    + "order by service_title";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                list.add(ser);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<image> getAllIMGbyServiceID(int id) {
        List<image> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from service_image where service_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                image image = new image(rs.getInt("image_id"), rs.getInt("service_id"), rs.getString("image_link"));
                list.add(image);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<service> getServicesByCategoryIDforIndex(int id) {
        List<service> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select TOP 3 * from [service] where category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                list.add(ser);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public service getServiceById(int id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [service]\n"
                    + "where service_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                return ser;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public service getServicesByServicesID(int id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select TOP 3 * from [service] where service_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                return ser;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<hotservices> getHotServices() {
        List<hotservices> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select Distinct top 3 COUNT(*) 'HotService',service_id from reservation_detail\n"
                    + "group by (service_id)\n"
                    + "order by (COUNT(*))\n"
                    + "DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hotservices hotser = new hotservices(rs.getInt("HotService"), rs.getInt("service_id"));
                list.add(hotser);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<service> getAllServicesByTitle(String txt) {
        List<service> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [service] s\n"
                    + "join service_status ss\n"
                    + "on s.service_id = ss.service_id\n"
                    + "where\n"
                    + "ss.service_status = 1 and (\n"
                    + "freetext(service_title,?)\n"
                    + "or\n"
                    + "CONTAINS(service_title,?)\n"
                    + "or\n"
                    + "service_title like ?\n"
                    + ")\n"
                    + "order by service_title";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "\"" + txt + "*\"");
            ps.setString(2, "\"" + txt + "*\"");
            ps.setString(3, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                list.add(ser);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<service> getTop3Services() {
        List<service> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select top 3 * from [service] s\n"
                    + "join service_status ss\n"
                    + "on s.service_id = ss.service_id\n"
                    + "where ss.service_status = 1\n"
                    + "order by service_created_date";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                list.add(ser);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<service> getNext3Services(int amount) {
        List<service> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [service] s\n"
                    + "join service_status ss\n"
                    + "on s.service_id = ss.service_id\n"
                    + "where ss.service_status = 1\n"
                    + "order by service_created_date\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT 3 ROWS ONLY";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, amount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                list.add(ser);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<service> getAllServicesByCate(int cate) {
        List<service> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [service] s\n"
                    + "join service_status ss\n"
                    + "on s.service_id = ss.service_id\n"
                    + "where s.category_id = ? and ss.service_status = 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<image> listX = getAllIMGbyServiceID(rs.getInt("service_id"));
                service ser = new service(rs.getInt("service_id"), rs.getString("service_title"), rs.getString("service_bi"), rs.getDate("service_created_date"), rs.getInt("category_id"), rs.getFloat("service_price"), rs.getFloat("service_discount"), rs.getString("service_detail"), rs.getInt("service_rateStar"), rs.getInt("service_vote"), listX);
                list.add(ser);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void adnewservice(String title, String brief, String detail, float prices, float discounts, int categoryidd, String image) throws Exception {
        try {
            Connection connection = new DBContext().getConnection();
            //       connection.setAutoCommit(false);
            String sql = "INSERT INTO [dbo].[service]\n"
                    + "           ([service_title]\n"
                    + "           ,[service_bi]\n"
                    + "           ,[service_created_date]\n"
                    + "           ,[category_id]\n"
                    + "           ,[service_price]\n"
                    + "           ,[service_discount]\n"
                    + "           ,[service_detail]\n"
                    + "           ,[service_rateStar]\n"
                    + "           ,[service_vote])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,GETDATE()\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,0\n"
                    + "           ,0\n"
                    + "           )";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, brief);
            stm.setInt(3, categoryidd);
            stm.setFloat(4, prices);
            stm.setFloat(5, discounts);
            stm.setString(6, detail);
            stm.executeUpdate();
// insert service_image
            String sql2 = "select top 1 service_id from service order by service_id desc";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            ResultSet rs = stm2.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String sql3 = "INSERT INTO [service_image]\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?);";
                PreparedStatement stm3 = connection.prepareStatement(sql3);
                stm3.setInt(1, id);
                stm3.setString(2, image);
                stm3.executeUpdate();
            }

            // inser service_status
            String sql4 = "select top 1 service_id from service order by service_id desc";
            PreparedStatement stm4 = connection.prepareStatement(sql4);
            ResultSet rs1 = stm4.executeQuery();
            while (rs1.next()) {
                int idd = rs1.getInt(1);
                String sql5 = "INSERT INTO [dbo].[service_status]\n"
                        + "                                ([status_id],\n"
                        + "								[service_id]\n"
                        + "                                 ,[service_status])\n"
                        + "                          VALUES\n"
                        + "                                  (?,\n"
                        + "								  ?\n"
                        + "                                  ,0)";
                PreparedStatement stm5 = connection.prepareStatement(sql5);
                stm5.setInt(1, idd);
                stm5.setInt(2, idd);
                stm5.executeUpdate();

            }
        } catch (SQLException ex) {
            System.out.println("day la loi: " + ex);
        }
    }

    public static void main(String[] args) {
        ServicesDAO ser = new ServicesDAO();
        System.out.println(ser.getServiceById(1).getService_price());
    }

    public List<listservicemanager> getServiceWithPaggingByCategory(int category_id, int page, int PAGE_SIZE) {
        List<listservicemanager> list = new ArrayList<>();
        try {
            String sql = "select s.service_id, si.image_link, c.category_name, s.service_title,\n"
                    + "                                                      s.service_price, s.service_discount, st.service_status\n"
                    + "                                                from category as c inner join service as s on c.category_id = s.category_id\n"
                    + "                                                        inner join service_image as si on s.service_id = si.service_id \n"
                    + "                                     			inner join service_status st on s.service_id = st.service_id where s.category_id = ?\n"
                    + "                                       order by s.service_id \n"
                    + "									   offset (?-1)*? row fetch next ? rows only";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ps.setInt(2, page);
            ps.setInt(3, PAGE_SIZE);
            ps.setInt(4, PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listservicemanager listser = new listservicemanager(
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("category_name"),
                        rs.getString("service_title"),
                        rs.getFloat("service_price"),
                        rs.getFloat("service_discount"),
                        rs.getBoolean("service_status")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {

        }
        return list;
    }

    public int getTotalService() {
        List<listservicemanager> list = new ArrayList<>();
        try {
            String sql = "select count(s.service_id) from category as c inner join service as s on c.category_id = s.category_id\n"
                    + "                     inner join service_image as si on s.service_id = si.service_id";
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

    public servicedetailmanager servicedetailmanagerbyID(int sid) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select s.service_id, si.image_link, c.category_name, s.service_title, s.service_bi, s.service_detail, s.service_price, s.service_discount, ss.service_status\n"
                    + "                    				from category as c inner join service as s on c.category_id = s.category_id inner join service_status as ss on s.service_id = ss.service_id\n"
                    + "                    				inner join service_image as si on s.service_id = si.service_id where s.service_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                servicedetailmanager ser = new servicedetailmanager(
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("category_name"),
                        rs.getString("service_title"),
                        rs.getString("service_bi"),
                        rs.getString("service_detail"),
                        rs.getFloat("service_price"),
                        rs.getFloat("service_discount"),
                        rs.getBoolean("service_status")
                );
                return ser;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editservicemanager(String title, String brief, String detail, float prices, float discounts, int categoryidd, String image, int sidd) throws Exception {
        try {
            Connection connection = new DBContext().getConnection();
            //       connection.setAutoCommit(false);
            String sql = "UPDATE [dbo].[service]\n"
                    + "   SET [service_title] = ?\n"
                    + "      ,[service_bi] = ?\n"
                    + "      ,[service_detail] = ?\n"
                    + "      ,[service_price] = ?\n"
                    + "      ,[service_discount] = ?\n"
                    + "      ,[category_id] = ?\n"
                    + "\n"
                    + " WHERE service_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, brief);
            stm.setString(3, detail);
            stm.setFloat(4, prices);
            stm.setFloat(5, discounts);
            stm.setInt(6, categoryidd);
            stm.setInt(7, sidd);
            stm.executeUpdate();

            String sql2 = "UPDATE [dbo].[service_image]\n"
                    + "   SET[image_link] = ?\n"
                    + " WHERE [service_id] = ?";
            PreparedStatement stm2 = connection.prepareStatement(sql2);
            stm2.setString(1, image);
            stm2.setInt(2, sidd);
            stm2.executeUpdate();

        } catch (SQLException ex) {

        }
    }

    public List<listservicemanager> searchservicemanager(String txt) {
        List<listservicemanager> list = new ArrayList<>();
        try {

            String sql = "select s.service_id, s.service_title, s.service_detail, s.service_price, s.service_discount, s.service_bi,si.image_link, c.category_name,"
                    + "                                 ss.service_status \n"
                    + "					from category as c inner join service as s on c.category_id = s.category_id \n"
                    + "					inner join service_image as si on s.service_id = si.service_id"
                    + "                                 inner join service_status as ss on s.service_id = ss.service_id \n"
                    + "					where\n"
                    + "					s.service_title like ?\n"
                    + "					or\n"
                    + "                    freetext(s.service_title,?)\n"
                    + "                    or\n"
                    + "                    CONTAINS(s.service_title,?)\n"
                    + "                    or\n"
                    + "                    s.service_bi like ?\n"
                    + "					or\n"
                    + "                    freetext(s.service_bi,?)\n"
                    + "                    or\n"
                    + "                    CONTAINS(s.service_bi,?)\n"
                    + "					";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ps.setString(2, "\"" + txt + "*\"");
            ps.setString(3, "\"" + txt + "*\"");
            ps.setString(4, "%" + txt + "%");
            ps.setString(5, "\"" + txt + "*\"");
            ps.setString(6, "\"" + txt + "*\"");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listservicemanager listser = new listservicemanager(
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("category_name"),
                        rs.getString("service_title"),
                        rs.getFloat("service_price"),
                        rs.getFloat("service_discount"),
                        rs.getBoolean("service_status")
                );
                list.add(listser);
            }
            return list;
        } catch (Exception ex) {
            System.out.println("search: " + ex);
        }
        return null;
    }

    public List<listservicemanager> getServiceWithPagging(int page, int PAGE_SIZE) {
        List<listservicemanager> list = new ArrayList<>();
        try {
            String sql = "select s.service_id, si.image_link, c.category_name, s.service_title,\n"
                    + "                                   s.service_price, s.service_discount, st.service_status\n"
                    + "                              from category as c inner join service as s on c.category_id = s.category_id\n"
                    + "                                     inner join service_image as si on s.service_id = si.service_id \n"
                    + "                   			inner join service_status st on s.service_id = st.service_id\n"
                    + "                    order by s.service_id  offset (?-1)*? row fetch next ? rows only";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, PAGE_SIZE);
            ps.setInt(3, PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listservicemanager listser = new listservicemanager(
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("category_name"),
                        rs.getString("service_title"),
                        rs.getFloat("service_price"),
                        rs.getFloat("service_discount"),
                        rs.getBoolean("service_status")
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
            String sql = "UPDATE [dbo].[service_status]\n"
                    + "   SET [service_status] = ?\n"
                    + " WHERE service_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ps.setInt(2, idd);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<listservicemanager> getServiceWithPaggingSortPrice(int page, int PAGE_SIZE) {
        List<listservicemanager> list = new ArrayList<>();
        try {
            String sql = "select s.service_id, si.image_link, c.category_name, s.service_title,\n"
                    + "                                   s.service_price, s.service_discount, st.service_status\n"
                    + "                              from category as c inner join service as s on c.category_id = s.category_id\n"
                    + "                                     inner join service_image as si on s.service_id = si.service_id \n"
                    + "                   			inner join service_status st on s.service_id = st.service_id\n"
                    + "                    order by s.service_price  offset (?-1)*? row fetch next ? rows only";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, PAGE_SIZE);
            ps.setInt(3, PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listservicemanager listser = new listservicemanager(
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("category_name"),
                        rs.getString("service_title"),
                        rs.getFloat("service_price"),
                        rs.getFloat("service_discount"),
                        rs.getBoolean("service_status")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {

        }
        return list;
    }

    public List<listservicemanager> getServiceWithPaggingSortTitle(int page, int PAGE_SIZE) {
        List<listservicemanager> list = new ArrayList<>();
        try {
            String sql = "select s.service_id, si.image_link, c.category_name, s.service_title,\n"
                    + "                                   s.service_price, s.service_discount, st.service_status\n"
                    + "                              from category as c inner join service as s on c.category_id = s.category_id\n"
                    + "                                     inner join service_image as si on s.service_id = si.service_id \n"
                    + "                   			inner join service_status st on s.service_id = st.service_id\n"
                    + "                    order by s.service_title offset (?-1)*? row fetch next ? rows only";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, PAGE_SIZE);
            ps.setInt(3, PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listservicemanager listser = new listservicemanager(
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("category_name"),
                        rs.getString("service_title"),
                        rs.getFloat("service_price"),
                        rs.getFloat("service_discount"),
                        rs.getBoolean("service_status")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {

        }
        return list;
    }

    public List<listservicemanager> getServiceWithPaggingSortDiscount(int page, int PAGE_SIZE) {
        List<listservicemanager> list = new ArrayList<>();
        try {
            String sql = "select s.service_id, si.image_link, c.category_name, s.service_title,\n"
                    + "                                   s.service_price, s.service_discount, st.service_status\n"
                    + "                              from category as c inner join service as s on c.category_id = s.category_id\n"
                    + "                                     inner join service_image as si on s.service_id = si.service_id \n"
                    + "                   			inner join service_status st on s.service_id = st.service_id\n"
                    + "                    order by s.service_discount offset (?-1)*? row fetch next ? rows only";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ps.setInt(2, PAGE_SIZE);
            ps.setInt(3, PAGE_SIZE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listservicemanager listser = new listservicemanager(
                        rs.getInt("service_id"),
                        rs.getString("image_link"),
                        rs.getString("category_name"),
                        rs.getString("service_title"),
                        rs.getFloat("service_price"),
                        rs.getFloat("service_discount"),
                        rs.getBoolean("service_status")
                );
                list.add(listser);
            }
            //  return list;
        } catch (Exception ex) {

        }
        return list;
    }
}
