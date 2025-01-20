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
import java.util.Map;
import model.CardMedicine;
import model.EditReservationInformation;
import model.InformationReservation;
import model.InformationUserReservation;
import model.ListChildrenReservation;
import model.ListServiceOfReservation;
import model.MyReservation;
import model.ReservationDetail;
import model.ReservationDetailManager;
import model.Reservation_Medical;
import model.RevuBycate;
import model.children;

import model.reservation;
import model.reservation_counts;
import model.reservationdetail1;
import model.reservationlist;

/**
 *
 * @author admin
 */
public class ReservationDAO {

    public List<reservation> getAllReservation() {
        List<reservation> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [reservation]";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reservation rev = new reservation(rs.getInt("reservation_id"), rs.getInt("user_id"), rs.getFloat("total_price"), rs.getString("note"), rs.getInt("reservation_status"), rs.getDate("created_date"));

                list.add(rev);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public reservation getReservationByID(int id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from reservation\n"
                    + "where reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new reservation(rs.getInt("reservation_id"), rs.getInt("user_id"), rs.getFloat("total_price"), rs.getString("note"), rs.getInt("reservation_status"), rs.getDate("created_date"));

            }

        } catch (Exception e) {
        }
        return null;
    }

    public List<ReservationDetail> getAllReservationDetailByReservationID(int id) {
        List<ReservationDetail> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [reservation_detail] where reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReservationDetail rev = new ReservationDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getDate(10), rs.getInt(11), rs.getInt(12));
                list.add(rev);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int CountReservationByStatus(int id) {

        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select COUNT(*) as 'ThanhCong' from reservation where reservation_status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("ThanhCong");
                return n;
            }

        } catch (Exception e) {
        }
        return 0;
    }
    
    public int CountReservationFailByStatus(int id) {

        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select COUNT(*) as 'ThatBai' from reservation where reservation_status = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("ThatBai");
                return n;
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public float RevenuesAll() {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select SUM(price) as 'TotalPrice' from reservation_detail red\n"
                    + "join reservation re\n"
                    + "on red.reservation_id = re.reservation_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("TotalPrice");
                return n;
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public List<Float> RevenuesByCategoryID() {
        List<Float> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select SUM(price) as 'Total', category_id from reservation_detail\n"
                    + "group by category_id order by category_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Float n = rs.getFloat(1);

                list.add(n);
            }
            return list;
        } catch (Exception e) {
        }
        return list;
    }

    public List<reservation_counts> getAllReservation7days() {
        List<reservation_counts> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select re.created_date,COUNT(res.service_id) Count_Service from reservation re\n"
                    + "join reservation_detail res\n"
                    + "on re.reservation_id = res.reservation_id\n"
                    + "group by re.created_date\n"
                    + "order by created_date\n"
                    + "DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reservation_counts rev = new reservation_counts(rs.getDate(1), rs.getInt(2));
                list.add(rev);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<reservation_counts> getAllReservationSuccess7days() {
        List<reservation_counts> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select re.created_date,COUNT(res.service_id) Count_Service from reservation re\n"
                    + "join reservation_detail res\n"
                    + "on re.reservation_id = res.reservation_id\n"
                    + "where re.reservation_status = 1\n"
                    + "group by re.created_date\n"
                    + "order by created_date\n"
                    + "DESC";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reservation_counts rev = new reservation_counts(rs.getDate(1), rs.getInt(2));
                list.add(rev);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public InformationUserReservation getInformationUserByReserId(int i) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select DISTINCT u.user_id, u.user_fullname, u.user_gender, u.user_email, u.user_phone\n"
                    + "from [user] u inner join reservation r on u.user_id = r.user_id\n"
                    + "inner join reservation_detail rt on r.reservation_id=rt.reservation_id\n"
                    + "inner join [service] s on rt.service_id=s.service_id\n"
                    + "inner join service_image si on s.service_id=si.service_id\n"
                    + "where rt.reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new InformationUserReservation(
                        rs.getInt("user_id"),
                        rs.getString("user_fullname"),
                        rs.getBoolean("user_gender"),
                        rs.getString("user_email"),
                        rs.getString("user_phone")
                );
                // list.add(rev);
            }
            //  return list;
        } catch (Exception e) {
        }
        return null;
    }

    public InformationReservation getInformationByReservationId(int i) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select DISTINCT r.reservation_id, r.created_date, rt.begin_time, r.total_price, r.reservation_status, rt.slot\n"
                    + "                    from [user] u inner join reservation r on u.user_id = r.user_id\n"
                    + "                    inner join reservation_detail rt on r.reservation_id=rt.reservation_id\n"
                    + "                    inner join [service] s on rt.service_id=s.service_id\n"
                    + "                    inner join service_image si on s.service_id=si.service_id\n"
                    + "                    where rt.reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new InformationReservation(
                        rs.getInt("reservation_id"),
                        rs.getDate("created_date"),
                        rs.getDate("begin_time"),
                        rs.getFloat("total_price"),
                        rs.getInt("reservation_status"),
                        rs.getInt("slot")
                );
                // list.add(rev);
            }
            //  return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ListServiceOfReservation> getListServiceOfReservation(int i) {
        List<ListServiceOfReservation> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select DISTINCT rt.reservation_detail_id, si.image_link, s.service_title, rt.begin_time, rt.slot, r.reservation_status, cl.children_name,\n"
                    + "                                                                           s.service_price, rt.quantity, \n"
                    + "                                                                      (select user_fullname from [user]\n"
                    + "                                                                          where [user_id] in (rt.doctor_id)) as doctor_name\n"
                    + "                                                                         , (select user_fullname from [user]\n"
                    + "                                      				 where [user_id] in (rt.nurse_id)) as nurse_name\n"
                    + "                                                                         from [user] u inner join reservation r on u.user_id = r.user_id\n"
                    + "                                                                        \n"
                    + "                                                                       inner join reservation_detail rt on r.reservation_id=rt.reservation_id\n"
                    + "																	   join children cl on rt.children_id = cl.children_id\n"
                    + "                                                                   inner join [service] s on rt.service_id=s.service_id\n"
                    + "                                                                      inner join service_image si on s.service_id=si.service_id where rt.reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ListServiceOfReservation listServiceOfReservation = new ListServiceOfReservation(
                        rs.getInt("reservation_detail_id"),
                        rs.getString("image_link"),
                        rs.getString("service_title"),
                        rs.getDate("begin_time"),
                        rs.getInt("slot"),
                        rs.getInt("reservation_status"),
                        rs.getFloat("service_price"),
                        rs.getInt("quantity"),
                        rs.getString("children_name"),
                        rs.getString("doctor_name"),
                        rs.getString("nurse_name")
                );
                list.add(listServiceOfReservation);
            }
            return list;
        } catch (Exception e) {
            System.out.println("loii nay" + e);
        }
        return null;
    }

    public List<MyReservation> getAllReservationByUserID(int user_id) {
        List<MyReservation> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select r.reservation_id,s.service_title,r.created_date,rd.begin_time,rd.num_of_person,r.reservation_status,r.total_price,si.image_link,rd.slot,r.note\n"
                    + "  from reservation r \n"
                    + "  join reservation_detail rd on (r.reservation_id =rd.reservation_id) \n"
                    + "  join [service] s on (s.service_id=rd.service_id)\n"
                    + "  join [service_image] si on(si.service_id=s.service_id)\n"
                    + "  where r.user_id = ? order by r.created_date asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            List<Integer> listId = getAllDiffReservation(user_id);
            for (int i = 0; i < listId.size(); i++) {
                while (rs.next()) {
                    MyReservation myR = new MyReservation(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getString(8), rs.getInt(9), rs.getString(10));
                    if (myR.getReservation_id() == listId.get(i)) {
                        list.add(myR);
                        break;
                    }

                }
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    


    public List<Integer> getAllDiffReservation(int user_id) {
        List<Integer> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select DISTINCT reservation.reservation_id from reservation,reservation_detail\n"
                    + " where reservation.reservation_id=reservation_detail.reservation_id and reservation.user_id = ?\n"
                    + " group by reservation.reservation_id,reservation_detail.service_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("reservation_id");
                list.add(n);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Integer> getAllDiffReservationSubmitted() {
        List<Integer> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select DISTINCT reservation.reservation_id from reservation,reservation_detail\n"
                    + " where reservation.reservation_id=reservation_detail.reservation_id and reservation.reservation_status in (2,4)\n"
                    + " group by reservation.reservation_id,reservation_detail.service_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int n = rs.getInt("reservation_id");
                list.add(n);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int CreateReservationAndReturnId(reservation reser) {

        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [reservation]\n"
                    + "           ([user_id]\n"
                    + "           ,[total_price]\n"
                    + "           ,[note]\n"
                    + "           ,[reservation_status]\n"
                    + "           ,[created_date])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE())";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reser.getUser_id());
            ps.setFloat(2, reser.getTotal_price());
            ps.setString(3, reser.getNote());
            ps.setInt(4, reser.getReservation_status());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void SaveOrderDetail(int n, ReservationDetail reservationDetail) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [reservation_detail]\n"
                    + "           ([reservation_id]\n"
                    + "           ,[service_id]\n"
                    + "           ,[price]\n"
                    + "           ,[quantity]\n"
                    + "           ,[num_of_person]\n"
                    + "           ,[category_id]\n"
                    + "           ,[doctor_id]\n"
                    + "           ,[nurse_id]\n"
                    + "           ,[begin_time]\n"
                    + "           ,[slot]\n"
                    + "           ,[children_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, n);
            ps.setInt(2, reservationDetail.getService_id());
            ps.setFloat(3, reservationDetail.getPrice());
            ps.setInt(4, reservationDetail.getQuantity());
            ps.setInt(5, reservationDetail.getNumOfPerson());
            ps.setInt(6, reservationDetail.getCategory_id());
            ps.setInt(7, reservationDetail.getDoctor_id());
            ps.setInt(8, reservationDetail.getNurse_id());
            ps.setDate(9, reservationDetail.getBegin_time());
            ps.setInt(10, reservationDetail.getSlot());
            ps.setInt(11, reservationDetail.getChildren_id());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deletereservationById(int rid) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "delete from reservation_detail where reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

    public void deletereservationDetailById(int rid) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "delete from reservation where reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.executeQuery();
        } catch (Exception e) {
        }
    }

     public EditReservationInformation getInformationByReservationIdedit(int ridd) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = " select DISTINCT rt.reservation_detail_id, rt.begin_time, rt.slot, rt.quantity, cl.children_name,\n"
                    + "                                                                      (select user_fullname from [user]\n"
                    + "                                                                          where [user_id] in (rt.doctor_id)) as doctor_name\n"
                    + "                                                                         , (select user_fullname from [user]\n"
                    + "                                      				 where [user_id] in (rt.nurse_id)) as nurse_name\n"
                    + "                                                                         from [user] u inner join reservation r on u.user_id = r.user_id\n"
                    + "                                                                        join children cl on u.user_id = cl.user_id\n"
                    + "                                                                       inner join reservation_detail rt on r.reservation_id=rt.reservation_id\n"
                    + "                                                                   inner join [service] s on rt.service_id=s.service_id\n"
                    + "                                                                      inner join service_image si on s.service_id=si.service_id where rt.reservation_detail_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ridd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new EditReservationInformation(
                        rs.getInt("reservation_detail_id"),
                        rs.getDate("begin_time"),
                        rs.getInt("quantity"),
                        rs.getInt("slot"),
                        rs.getString("children_name"),
                        rs.getString("doctor_name"),
                        rs.getString("nurse_name")
                );
                // list.add(rev);
            }
            //  return list;
        } catch (Exception e) {
        }
        return null;
    }
    public void editReservatonInformation(int quantity, int num_of_person, int slot, int reservation_id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE rt set rt.quantity = ?, rt.num_of_person = ?, rt.slot = ?\n"
                    + "                  from [user] u inner join reservation r on u.user_id = r.user_id\n"
                    + "                    inner join reservation_detail rt on r.reservation_id=rt.reservation_id\n"
                    + "                    inner join [service] s on rt.service_id=s.service_id\n"
                    + "                    inner join service_image si on s.service_id=si.service_id\n"
                    + "                   where  rt.reservation_id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, num_of_person);
            ps.setFloat(3, slot);
            ps.setInt(4, reservation_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void changeStatus(int res_id, int status) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [dbo].[reservation]\n"
                    + "   SET [reservation_status] = ?\n"
                    + " WHERE reservation_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, res_id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<MyReservation> getAllReservationSubmitted() {
        List<MyReservation> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select r.reservation_id,s.service_title,r.created_date,rd.begin_time,rd.num_of_person,r.reservation_status,r.total_price,si.image_link,rd.slot,r.note\n"
                    + "from reservation r\n"
                    + "join reservation_detail rd on (r.reservation_id =rd.reservation_id)\n"
                    + "join [service] s on (s.service_id=rd.service_id)\n"
                    + "join [service_image] si on(si.service_id=s.service_id)\n"
                    + "where r.reservation_status in (2,4) order by r.created_date asc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Integer> listId = getAllDiffReservationSubmitted();
            for (int i = 0; i < listId.size(); i++) {
                while (rs.next()) {
                    MyReservation myR = new MyReservation(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getString(8), rs.getInt(9), rs.getString(10));
                    if (myR.getReservation_id() == listId.get(i)) {
                        list.add(myR);
                        break;
                    }
                }
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
    
    

    public List<children> getListChildrenByResId(int res_id, int service_id) {
        List<children> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select c.* from children c\n"
                    + "join reservation_detail r\n"
                    + "on c.children_id = r.children_id\n"
                    + "join reservation re\n"
                    + "on r.reservation_id = re.reservation_id\n"
                    + "where re.reservation_id = ? and r.service_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, res_id);
            ps.setInt(2, service_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                children c = new children(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4), rs.getInt(5));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ListChildrenReservation> getListServiceOfReservationChild(int res_id) {
        List<ListChildrenReservation> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select DISTINCT rt.service_id,si.image_link, s.service_title\n"
                    + "from [user] u inner join reservation r on u.user_id = r.user_id\n"
                    + "inner join reservation_detail rt on r.reservation_id=rt.reservation_id\n"
                    + " inner join [service] s on rt.service_id=s.service_id\n"
                    + " inner join service_image si on s.service_id=si.service_id\n"
                    + " where r.reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, res_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<children> listChild = getListChildrenByResId(res_id, rs.getInt(1));
                ListChildrenReservation listRe = new ListChildrenReservation(res_id, rs.getInt(1), rs.getString(2), rs.getString(3), listChild);
                list.add(listRe);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ReservationDAO re = new ReservationDAO();
        System.out.println(re.getReDetailIdByRSC(10, 1, 1));
    }

    public int getReDetailIdByRSC(int res_id, int service_id, int child_id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from reservation_detail\n"
                    + "where \n"
                    + "reservation_id = ?\n"
                    + "and service_id = ?\n"
                    + "and children_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, res_id);
            ps.setInt(2, service_id);
            ps.setInt(3, child_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public int CreateReservationMedicalReturnId(Reservation_Medical r) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [dbo].[reservation_medical]\n"
                    + "           ([reservation_detail_id]\n"
                    + "           ,[diagnosis]\n"
                    + "           ,[created_date]\n"
                    + "           ,[doctor_id])\n"
                    + "     VALUES\n"
                    + "           (?,?,GETDATE(),?)";
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, r.getReservation_detail_id());
            ps.setString(2, r.getDiagnosis());
            ps.setInt(3, r.getDoctor_id());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void SaveMediCine(int reservationMedical_id, Map<Integer, CardMedicine> carts) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [dbo].[prescription_details]\n"
                    + "           ([medical_id]\n"
                    + "           ,[medicine_id]\n"
                    + "           ,[quantity]\n"
                    + "           ,[price])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reservationMedical_id);
            for (Map.Entry<Integer, CardMedicine> entry : carts.entrySet()) {
                Integer medicine_id = entry.getKey();
                CardMedicine cart = entry.getValue();
                ps.setInt(2, cart.getMedicine().getMedicine_id());
                ps.setInt(3, cart.getQuantity());
                ps.setDouble(4, cart.getMedicine().getMedicine_price() * cart.getQuantity());
                ps.executeUpdate();
            }

        } catch (Exception e) {
        }
    }

    public double getTotalPriceByResId(int res_id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select total_price from reservation\n"
                    + "where reservation_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, res_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public List<reservationlist> getAllReservationList2() {
        List<reservationlist> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select r.reservation_id, r.created_date, u.user_fullname ,s.service_title, d.num_of_person , r.total_price, r.reservation_status from reservation r join [user] u\n"
                    + "on r.user_id = u.user_id\n"
                    + "join reservation_detail d\n"
                    + "on r.reservation_id = d.reservation_id\n"
                    + "join [service] s\n"
                    + "on d.service_id = s.service_id\n"
                    + "where r.reservation_status in (2,3)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reservationlist rev = new reservationlist(rs.getInt("reservation_id"), rs.getDate("created_date"), rs.getString("user_fullname"),
                        rs.getString("service_title"), rs.getInt("num_of_person"), rs.getFloat("total_price"), rs.getInt("reservation_status"));
//rs.getInt("reservation_id"),rs.getDate("created_date"),rs.getString("user_fullname") ,rs.getString("user_fullname")

                list.add(rev);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<ReservationDetailManager> getReservationDetail2() {
        List<ReservationDetailManager> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select d.reservation_id, u.user_fullname, u.user_email, u.user_phone,r.created_date,r.total_price,r.reservation_status,i.image_link, s.service_title, c.category_name,d.price, d.num_of_person from reservation_detail d join reservation r\n"
                    + "ON d.reservation_id = r.reservation_id\n"
                    + "join [service] s\n"
                    + "ON d.service_id = s.service_id\n"
                    + "join service_image i\n"
                    + "ON d.service_id = i.service_id\n"
                    + "join [user] u\n"
                    + "ON r.user_id = u.user_id\n"
                    + "join category c\n"
                    + "ON d.category_id = c.category_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReservationDetailManager rev = new ReservationDetailManager(rs.getInt("reservation_id"), rs.getString("user_fullname"), rs.getString("user_email"), rs.getString("user_phone"), rs.getDate("created_date"), rs.getFloat("total_price"), rs.getInt("reservation_status"), rs.getString("image_link"), rs.getString("service_title"), rs.getString("category_name"), rs.getFloat("price"), rs.getInt("num_of_person"));

                list.add(rev);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void editReservatonInformation(int quantity, String begin_date, int children, int doctor, int nurse, int slot, int reservation_id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [dbo].[reservation_detail]\n"
                    + "   SET \n"
                    + "       [quantity] = ?\n"
                    + "      ,[doctor_id] = ?\n"
                    + "      ,[nurse_id] = ?\n"
                    + "      ,[begin_time] = ?\n"
                    + "      ,[slot] = ?\n"
                    + "      ,[children_id] = ?\n"
                    + " WHERE reservation_detail_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, doctor);
            ps.setFloat(3, nurse);
            ps.setString(4, begin_date);
            ps.setInt(5, slot);
            ps.setInt(6, children);
            ps.setInt(7, reservation_id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
