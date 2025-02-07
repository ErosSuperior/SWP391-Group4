/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.user;

/**
 *
 * @author win
 */
public class UserDAO {

    public user getUserById(int id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [user]\n"
                    + "where [user_id] = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                return u;
            }

        } catch (Exception e) {
        }
        return null;
    }

    public void EditUser(user edit) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [dbo].[user]\n"
                    + "   SET [user_fullname] = ?\n"
                    + "      ,[user_gender] = ?\n"
                    + "      ,[user_address] = ?\n"
                    + "      ,[user_password] = ?\n"
                    + "      ,[user_email] = ?\n"
                    + "      ,[user_phone] = ?\n"
                    + "      ,[role_id] = ?\n"
                    + "      ,[user_status] = ?\n"
                    + "      ,[user_image] = ?\n"
                    + " WHERE [user_id] = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, edit.getUser_name());
            ps.setBoolean(2, edit.isUser_gender());
            ps.setString(3, edit.getUser_address());
            ps.setString(4, edit.getUser_password());
            ps.setString(5, edit.getUser_email());
            ps.setString(6, edit.getUser_phone());
            ps.setInt(7, edit.getUser_role());
            ps.setBoolean(8, edit.isUser_status());
            ps.setString(9, edit.getUser_image());
            ps.setInt(10, edit.getUser_id());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addNewUser(user u) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "INSERT INTO [dbo].[user]\n"
                    + "           ([user_fullname]\n"
                    + "           ,[user_gender]\n"
                    + "           ,[user_address]\n"
                    + "           ,[user_password]\n"
                    + "           ,[user_email]\n"
                    + "           ,[user_phone]\n"
                    + "           ,[role_id]\n"
                    + "           ,[user_status]\n"
                    + "           ,[user_image])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUser_name());
            ps.setBoolean(2, u.isUser_gender());
            ps.setString(3, u.getUser_address());
            ps.setString(4, u.getUser_password());
            ps.setString(5, u.getUser_email());
            ps.setString(6, u.getUser_phone());
            ps.setInt(7, u.getUser_role());
            ps.setBoolean(8, u.isUser_status());
            ps.setString(9, u.getUser_image());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<user> listUserWithPagging(int page) {
        List<user> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [user]\n"
                    + "order by [user_id]\n"
                    + "offset (?-1)*8 row fetch next 8 rows only";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int countTotalUser() {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select count (*) from [user]";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<user> getUserBySearch(String search) {
        List<user> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "Select * from [user] where user_fullname like ? or user_email like ? or user_phone like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            ps.setString(2, "%" + search + "%");
            ps.setString(3, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<user> getCustomerBySearch(String search) {
        List<user> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [user]\n"
                    + "where\n"
                    + "[role_id] = 4 and (\n"
                    + "CONTAINS(user_email,?)\n"
                    + "or\n"
                    + "CONTAINS(user_fullname,?)\n"
                    + "or\n"
                    + "CONTAINS(user_phone,?)\n"
                    + "or\n"
                    + "freetext(user_email,?)\n"
                    + "or\n"
                    + "freetext(user_fullname,?)\n"
                    + "or\n"
                    + "freetext(user_phone,?))";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "\"" + search + "*\"");
            ps.setString(2, "\"" + search + "*\"");
            ps.setString(3, "\"" + search + "*\"");
            ps.setString(4, "\"" + search + "*\"");
            ps.setString(5, "\"" + search + "*\"");
            ps.setString(6, "\"" + search + "*\"");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<user> getUserByFilter(int[] role, int[] status, int[] gender, String mess, int page) {
        List<user> list = new ArrayList<>();
        int count = 0;
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [User]\nwhere 1=1 ";
            if (role != null) {
                sql += " and role_id in ( ";
                for (int i = 0; i < role.length; i++) {
                    if (i != role.length - 1) {
                        sql += " ?, ";
                    } else {
                        sql += " ? ) ";
                    }
                }
            }
            if (status != null) {
                sql += " and user_status in (";
                for (int i = 0; i < status.length; i++) {
                    if (i != status.length - 1) {
                        sql += " ?, ";
                    } else {
                        sql += " ?) ";
                    }
                }
            }
            if (gender != null) {
                sql += " and user_gender in (";
                for (int i = 0; i < gender.length; i++) {
                    if (i != gender.length - 1) {
                        sql += " ?, ";
                    } else {
                        sql += " ?) ";
                    }
                }
            }
            sql += "\n" + mess;
            sql += "\noffset (?-1)*8 row fetch next 8 rows only";
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            if (role == null && gender == null && status == null) {
                ps.setInt(1, page);
            } else {
                if (role != null) {
                    for (int i = 0; i < role.length; i++) {
                        ps.setInt(i + 1, role[i]);
                    }
                    count += role.length;
                    if (status != null) {
                        for (int i = 0; i < status.length; i++) {
                            ps.setInt(role.length + 1 + i, status[i]);
                        }
                        count += status.length;
                        if (gender != null) {
                            for (int i = 0; i < gender.length; i++) {
                                ps.setInt(role.length + status.length + 1 + i, gender[i]);
                            }
                            count += gender.length;
                        }
                    }
                    ps.setInt(count + 1, page);
                } else {
                    if (status != null) {
                        for (int i = 0; i < status.length; i++) {
                            ps.setInt(i + 1, status[i]);
                        }
                        count += status.length;
                        if (gender != null) {
                            for (int i = 0; i < gender.length; i++) {
                                ps.setInt(status.length + 1 + i, gender[i]);
                            }
                            count += gender.length;
                        }
                        ps.setInt(count + 1, page);
                    } else {
                        if (gender != null) {
                            for (int i = 0; i < gender.length; i++) {
                                ps.setInt(i + 1, gender[i]);
                            }
                        }
                        count += gender.length;
                        ps.setInt(count + 1, page);
                    }
                }
            }
            System.out.println("count: " + (count + 1));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int countTotalUserFilter(int[] role) {
        String role_sql = "";
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select count(*) from [User]\n"
                    + "where role_id in (";
            for (int i = 0; i < role.length; i++) {
                if (i != role.length - 1) {
                    role_sql += "?,";
                } else {
                    role_sql += "?)";
                }
            }
            sql += role_sql;
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < role.length; i++) {
                ps.setInt(i + 1, role[i]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        UserDAO u = new UserDAO();
        for(int i = 0;i<u.getCustomerBySearch("nguyen").size();i++){
            System.out.println(i);
        }
    }

    public List<user> getAllUser() {
        List<user> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [user]";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    static final String regex1 = "(0[3|5|7|8|9])+([0-9]{8})";
    static final String regex2 = "(84[3|5|7|8|9])+([0-9]{8})";

    public boolean checkPhone(String str) {
        if (str.matches(regex1) || str.matches(regex2)) {
            return true;
        }
        return false;
    }

    public void changePassword(user user) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "update [user]\n"
                    + "set user_password = ?\n"
                    + "where [user_id] = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUser_password());
            ps.setInt(2, user.getUser_id());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public user login(String user_email, String password) {
        try {
            String sql = "select * from [user] where user_email = ? and user_password = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user account = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                return account;
            }
        } catch (Exception ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public user checkAccountExit(String username) {
        try {
            String sql = "select * from [user] where user_email = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user account = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                return account;
            }
        } catch (Exception ex) {
            Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void register(String username, Boolean gender, String address, String password, String email, String phone) {
        String sql = "INSERT INTO [dbo].[user]\n"
                + "           ([user_fullname]\n"
                + "           ,[user_gender]\n"
                + "           ,[user_address]\n"
                + "           ,[user_password]\n"
                + "           ,[user_email]\n"
                + "           ,[user_phone]\n"
                + "           ,[role_id]\n"
                + "           ,[user_status]\n"
                + "           ,[user_image])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,4\n"
                + "           ,0\n"
                + "           ,null)";

        try {
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setBoolean(2, gender);
            ps.setString(3, address);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.setString(6, phone);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void changePasword(String user_email, String newpassword) {
        //To change body of generated methods, choose Tools | Templates.
        try {
            String sql = "UPDATE [dbo].[user] SET[user_password] = ? WHERE user_email = ?";
            Connection conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newpassword);
            ps.setString(2, user_email);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public user getUserbyEmail(String email) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [user]\n"
                    + "where user_email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                return u;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<user> getAllUserByRoleId(int id) {
        List<user> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [user] where role_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public int countTotalCustomer() {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select count (*) from [user] where role_id = 4";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<user> listCustomerWithPagging(int page) {
        List<user> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [user]\n where role_id = 4"
                    + "order by [user_id]\n"
                    + "offset (?-1)*8 row fetch next 8 rows only";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<user> getCustomerByFilter(int[] role, int[] status, int[] gender, String mess, int page) {
        List<user> list = new ArrayList<>();
        int count = 0;
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from [User]\nwhere 1=1 and role_id = 4";
            if (role != null) {
                sql += " and role_id in ( ";
                for (int i = 0; i < role.length; i++) {
                    if (i != role.length - 1) {
                        sql += " ?, ";
                    } else {
                        sql += " ? ) ";
                    }
                }
            }
            if (status != null) {
                sql += " and user_status in (";
                for (int i = 0; i < status.length; i++) {
                    if (i != status.length - 1) {
                        sql += " ?, ";
                    } else {
                        sql += " ?) ";
                    }
                }
            }
            if (gender != null) {
                sql += " and user_gender in (";
                for (int i = 0; i < gender.length; i++) {
                    if (i != gender.length - 1) {
                        sql += " ?, ";
                    } else {
                        sql += " ?) ";
                    }
                }
            }
            sql += "\n" + mess;
            sql += "\noffset (?-1)*8 row fetch next 8 rows only";
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            if (role == null && gender == null && status == null) {
                ps.setInt(1, page);
            } else {
                if (role != null) {
                    for (int i = 0; i < role.length; i++) {
                        ps.setInt(i + 1, role[i]);
                    }
                    count += role.length;
                    if (status != null) {
                        for (int i = 0; i < status.length; i++) {
                            ps.setInt(role.length + 1 + i, status[i]);
                        }
                        count += status.length;
                        if (gender != null) {
                            for (int i = 0; i < gender.length; i++) {
                                ps.setInt(role.length + status.length + 1 + i, gender[i]);
                            }
                            count += gender.length;
                        }
                    }
                    ps.setInt(count + 1, page);
                } else {
                    if (status != null) {
                        for (int i = 0; i < status.length; i++) {
                            ps.setInt(i + 1, status[i]);
                        }
                        count += status.length;
                        if (gender != null) {
                            for (int i = 0; i < gender.length; i++) {
                                ps.setInt(status.length + 1 + i, gender[i]);
                            }
                            count += gender.length;
                        }
                        ps.setInt(count + 1, page);
                    } else {
                        if (gender != null) {
                            for (int i = 0; i < gender.length; i++) {
                                ps.setInt(i + 1, gender[i]);
                            }
                        }
                        count += gender.length;
                        ps.setInt(count + 1, page);
                    }
                }
            }
            System.out.println("count: " + (count + 1));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user u = new user(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10));
                list.add(u);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
