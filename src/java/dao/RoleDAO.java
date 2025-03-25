package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.userRole;

public class RoleDAO {

    public List<userRole> getAllRole() {
        List<userRole> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM role";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userRole role = new userRole(rs.getInt(1), rs.getString(2), rs.getInt(3));
                list.add(role);
            }
            rs.close();
            ps.close();
            conn.close();
            return list;
        } catch (Exception e) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    // Phương thức lấy danh sách vai trò với phân trang và tìm kiếm bằng Id hoặc Name
    public List<userRole> getRoles(int offset, int limit, String nameOrId, String sortBy, String sortDir) throws Exception {
        List<userRole> roles = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM role WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (role_name LIKE ? OR role_id = ?)");
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir.equalsIgnoreCase("ASC") ? "ASC" : "DESC");
        query.append(" LIMIT ? OFFSET ?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query.toString());
            int index = 1;
            if (nameOrId != null && !nameOrId.isEmpty()) {
                ps.setString(index++, "%" + nameOrId + "%");
                try {
                    int id = Integer.parseInt(nameOrId);
                    ps.setInt(index++, id);
                } catch (NumberFormatException e) {
                    ps.setInt(index++, -1);
                }
            }
            ps.setInt(index++, limit);
            ps.setInt(index++, offset);

            rs = ps.executeQuery();
            while (rs.next()) {
                userRole role = new userRole(rs.getInt("role_id"), rs.getString("role_name"), rs.getInt("status"));
                roles.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return roles;
    }

    // Phương thức đếm số lượng vai trò với tìm kiếm bằng Id hoặc Name
    public int countRoles(String nameOrId) throws Exception {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM role WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (role_name LIKE ? OR role_id = ?)");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query.toString());
            if (nameOrId != null && !nameOrId.isEmpty()) {
                ps.setString(1, "%" + nameOrId + "%");
                try {
                    int id = Integer.parseInt(nameOrId);
                    ps.setInt(2, id);
                } catch (NumberFormatException e) {
                    ps.setInt(2, -1);
                }
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return count;
    }

    // Phương thức lấy vai trò theo roleId
    public userRole getRoleById(int roleId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM role WHERE role_id = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new userRole(rs.getInt("role_id"), rs.getString("role_name"), rs.getInt("status"));
            }
        } catch (Exception ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    // Phương thức thêm vai trò mới
    public void addRole(String roleName) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO role (role_name) VALUES (?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, roleName);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Phương thức cập nhật vai trò
    public void updateRole(int roleId, String roleName) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE role SET role_name = ? WHERE role_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, roleName);
            ps.setInt(2, roleId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Phương thức xóa vai trò
    public void updateStautsRole(String roleId, String status) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE `role`\n"
                + "SET\n"
                + "`status` = ?\n"
                + "WHERE `role_id` = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,status );
            ps.setString(2, roleId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
