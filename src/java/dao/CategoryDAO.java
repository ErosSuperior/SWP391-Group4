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
import model.Category;

public class CategoryDAO {

    // Phương thức kiểm tra danh mục đã tồn tại hay chưa dựa trên categoryName
    public Category checkCategoryExist(String categoryName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM category WHERE category_name = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("icon"),
                        rs.getInt("status")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    // Phương thức thêm danh mục mới
    public void addCategory(String categoryName, String icon) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO category (category_name, icon) VALUES (?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setString(2, icon);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Phương thức cập nhật danh mục
    public void updateCategory(int categoryId, String categoryName, String icon) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE category SET category_name = ?, icon = ? WHERE category_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setString(2, icon);
            ps.setInt(3, categoryId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Phương thức xóa danh mục
    public void updateStatusCategory(String categoryId, String status) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "UPDATE `category`\n"
                + "SET\n"
                + "`status` = ?\n"
                + "WHERE `category_id` = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, categoryId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    // Phương thức lấy danh sách danh mục với phân trang và tìm kiếm bằng Id hoặc Name
    public List<Category> getCategories(int offset, int limit, String nameOrId, String sortBy, String sortDir) throws Exception {
        List<Category> categories = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM category WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (category_name LIKE ? OR category_id = ?)");
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
                ps.setString(index++, "%" + nameOrId + "%"); // Tìm kiếm tên với LIKE
                try {
                    int id = Integer.parseInt(nameOrId);
                    ps.setInt(index++, id); // Tìm kiếm ID chính xác
                } catch (NumberFormatException e) {
                    ps.setInt(index++, -1); // Nếu không phải số, đặt ID không hợp lệ
                }
            }
            ps.setInt(index++, limit);
            ps.setInt(index++, offset);

            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("icon"),
                        rs.getInt("status")
                );
                categories.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return categories;
    }

    // Phương thức đếm số lượng danh mục với tìm kiếm bằng Id hoặc Name
    public int countCategories(String nameOrId) throws Exception {
        int count = 0;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM category WHERE 1=1 ");

        if (nameOrId != null && !nameOrId.isEmpty()) {
            query.append(" AND (category_name LIKE ? OR category_id = ?)");
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query.toString());
            if (nameOrId != null && !nameOrId.isEmpty()) {
                ps.setString(1, "%" + nameOrId + "%"); // Tìm kiếm tên với LIKE
                try {
                    int id = Integer.parseInt(nameOrId);
                    ps.setInt(2, id); // Tìm kiếm ID chính xác
                } catch (NumberFormatException e) {
                    ps.setInt(2, -1); // Nếu không phải số, đặt ID không hợp lệ
                }
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return count;
    }

    // Phương thức lấy tất cả danh mục
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM category";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("icon"),
                        rs.getInt("status")
                );
                categories.add(category);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return categories;
    }

    // Phương thức lấy danh mục theo categoryId
    public Category getCategoryById(int categoryId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM category WHERE category_id = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("icon"),
                        rs.getInt("status")
                );
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return null;
    }
}
