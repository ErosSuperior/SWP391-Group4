package dao;

import context.DBContext;
import model.Children;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChildrenDAO {

    // Get all children
    public List<Children> getAllChildren(int userId) {
        List<Children> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "SELECT * FROM children WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Children child = new Children(rs.getInt("children_id"),
                        rs.getInt("user_id"),
                        rs.getString("children_name"),
                        rs.getBoolean("children_gender"),
                        rs.getInt("children_age"));
                list.add(child);
            }
            rs.close();
            ps.close();
            conn.close();
            return list;
        } catch (Exception e) {
            Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list; // Return empty list instead of null
    }

    // Updated getChildren to include userId
    public List<Children> getChildren(int userId, int offset, int limit, String nameOrId, String sortBy, String sortDir) throws Exception {
        List<Children> children = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM children WHERE user_id = ?");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (children_name LIKE ? OR children_id = ?)");
        }

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "children_id";
        }
        if (!sortBy.equalsIgnoreCase("children_id") && !sortBy.equalsIgnoreCase("children_name") && 
            !sortBy.equalsIgnoreCase("children_gender") && !sortBy.equalsIgnoreCase("children_age")) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        if (sortDir == null || sortDir.isEmpty()) {
            sortDir = "ASC";
        }
        if (!sortDir.equalsIgnoreCase("ASC") && !sortDir.equalsIgnoreCase("DESC")) {
            throw new IllegalArgumentException("Invalid sort direction: " + sortDir);
        }

        query.append(" ORDER BY ").append(sortBy).append(" ").append(sortDir);
        query.append(" LIMIT ? OFFSET ?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query.toString());
            int index = 1;
            ps.setInt(index++, userId);
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
                Children child = new Children(rs.getInt("children_id"),
                        rs.getInt("user_id"),
                        rs.getString("children_name"),
                        rs.getBoolean("children_gender"),
                        rs.getInt("children_age"));
                children.add(child);
            }
            return children;
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Database error occurred while fetching children", ex);
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
                Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Updated countChildren to include userId
    public int countChildren(int userId, String nameOrId) throws Exception {
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM children WHERE user_id = ?");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (children_name LIKE ? OR children_id = ?)");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query.toString());
            ps.setInt(1, userId);
            if (nameOrId != null && !nameOrId.isEmpty()) {
                ps.setString(2, "%" + nameOrId + "%");
                try {
                    int id = Integer.parseInt(nameOrId);
                    ps.setInt(3, id);
                } catch (NumberFormatException e) {
                    ps.setInt(3, -1);
                }
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Database error occurred while counting children", ex);
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
                Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Get child by ID
    public Children getChildById(int childId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM children WHERE children_id = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, childId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Children(rs.getInt("children_id"),
                        rs.getInt("user_id"),
                        rs.getString("children_name"),
                        rs.getBoolean("children_gender"),
                        rs.getInt("children_age"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    // Add a new child
    public boolean addChild(Children child) throws Exception {
        if (child == null || child.getUser_id() <= 0 || child.getChildren_name() == null || 
            child.getChildren_name().trim().isEmpty() || child.getChildren_age() < 0 || child.getChildren_age() > 18) {
            return false;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO children (user_id, children_name, children_gender, children_age) VALUES (?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, child.getUser_id());
            ps.setString(2, child.getChildren_name());
            ps.setBoolean(3, child.isChildren_gender());
            ps.setInt(4, child.getChildren_age());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Update a child
    public boolean updateChild(Children child) throws Exception {
        if (child == null || child.getChildren_id() <= 0 || child.getUser_id() <= 0 || 
            child.getChildren_name() == null || child.getChildren_name().trim().isEmpty() || 
            child.getChildren_age() < 0 || child.getChildren_age() > 18) {
            return false;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE children SET children_name = ?, children_gender = ?, children_age = ? WHERE children_id = ? AND user_id = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, child.getChildren_name());
            ps.setBoolean(2, child.isChildren_gender());
            ps.setInt(3, child.getChildren_age());
            ps.setInt(4, child.getChildren_id());
            ps.setInt(5, child.getUser_id());
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Delete a child (soft delete or status update can be added if needed)
    public boolean deleteChild(int childId) throws Exception {
        if (childId <= 0) {
            return false;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM children WHERE children_id = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, childId);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(ChildrenDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Test database connection
    public boolean testConnection() {
        Connection conn = null;
        try {
            conn = new DBContext().getConnection();
            return conn != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}