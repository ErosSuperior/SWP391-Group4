/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;

public class CustomerBlogDAO extends DBContext {

    private Connection connection; // Tạo đối tượng connection

    public CustomerBlogDAO() {
        try {
            this.connection = getConnection(); // Khởi tạo kết nối
        } catch (Exception e) {
            e.printStackTrace();
            this.connection = null; // Set to null if connection fails
        }
    }

    public List<Blog> getCustomerBlog(String search, String category_id, int page, int pageSize) {
        List<Blog> list = new ArrayList<>();

        // Khởi tạo câu lệnh SQL động bằng StringBuilder
        StringBuilder sql = new StringBuilder("SELECT \n"
                + "    b.blog_id,\n"
                + "    b.user_id,\n"
                + "    b.title,\n"
                + "    b.bi,\n"
                + "    b.blog_created_date,\n"
                + "    b.category_id,\n"
                + "    c.category_name,\n"
                + "    b.detail,\n"
                + "    b.blog_image,\n"
                + "    b.view_able,\n"
                + "    u.user_fullname\n"
                + "FROM \n"
                + "    blogs b\n"
                + "INNER JOIN \n"
                + "    users u ON b.user_id = u.user_id\n"
                + "INNER JOIN \n"
                + "    category c ON b.category_id = c.category_id\n"
                + "WHERE b.view_able = 1 "); // Chỉ lấy blog có thể xem được

        // Kiểm tra nếu có tìm kiếm theo tiêu đề
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND (b.title LIKE ? ) ");
        }

        // Kiểm tra nếu có lọc theo danh mục
        if (category_id != null && !category_id.trim().isEmpty()) {
            sql.append(" AND b.category_id = ? ");
        }

        // Sắp xếp theo ngày tạo (mới nhất trước)
        sql.append(" ORDER BY b.blog_created_date DESC ");

        // Phân trang: Giới hạn số bản ghi hiển thị trên một trang
        sql.append(" LIMIT ? OFFSET ? ");

        try (PreparedStatement st = connection.prepareStatement(sql.toString())) {
            int index = 1;

            // Nếu có tìm kiếm theo tiêu đề, thêm tham số vào câu lệnh SQL
            if (search != null && !search.trim().isEmpty()) {
                st.setString(index++, "%" + search + "%");
            }

            // Nếu có lọc theo danh mục, thêm tham số vào câu lệnh SQL
            if (category_id != null && !category_id.trim().isEmpty()) {
                st.setInt(index++, Integer.parseInt(category_id));
            }

            // Set giới hạn số lượng bài viết trên một trang
            st.setInt(index++, pageSize);

            // Tính toán OFFSET (vị trí bắt đầu lấy dữ liệu)
            st.setInt(index++, (page - 1) * pageSize);

            // Thực thi truy vấn
            ResultSet rs = st.executeQuery();

            // Lặp qua từng dòng dữ liệu và đưa vào danh sách
            while (rs.next()) {
                Blog c = new Blog(
                        rs.getInt("blog_id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("bi"),
                        rs.getDate("blog_created_date"),
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("detail"),
                        rs.getString("blog_image"),
                        rs.getInt("view_able"),
                        rs.getString("user_fullname")
                );
                list.add(c); // Thêm vào danh sách
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return list; // Trả về danh sách blog
    }

    public int countTotalBlogs(String search, String category_id) {
        int total = 0;

        // Xây dựng câu lệnh SQL để đếm tổng số bài blog có thể xem (view_able = 1)
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM blogs WHERE view_able = 1 ");

        // Nếu có từ khóa tìm kiếm (search), thêm điều kiện lọc theo tiêu đề (title)
        if (search != null && !search.trim().isEmpty()) {
            sql.append(" AND title LIKE ? ");
        }

        // Nếu có category_id, thêm điều kiện lọc theo danh mục
        if (category_id != null && !category_id.trim().isEmpty()) {
            sql.append(" AND category_id = ? ");
        }

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement st = connection.prepareStatement(sql.toString());

            int index = 1;

            // Gán giá trị tham số tìm kiếm vào câu lệnh SQL nếu có
            if (search != null && !search.trim().isEmpty()) {
                st.setString(index++, "%" + search + "%");
            }

            // Gán giá trị category_id nếu có
            if (category_id != null && !category_id.trim().isEmpty()) {
                st.setInt(index++, Integer.parseInt(category_id));
            }

            // Thực thi truy vấn
            ResultSet rs = st.executeQuery();

            // Lấy kết quả đếm từ ResultSet
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e); // In ra lỗi nếu có
        }

        return total; // Trả về tổng số bài blog tìm được
    }

    public List<Blog> get3RecentPost() {
        List<Blog> list = new ArrayList<>();

        // Xây dựng câu lệnh SQL để lấy 3 bài viết gần nhất
        StringBuilder sql = new StringBuilder("SELECT \n"
                + "    b.blog_id,\n"
                + "    b.user_id,\n"
                + "    b.title,\n"
                + "    b.bi,\n"
                + "    b.blog_created_date,\n"
                + "    b.category_id,\n"
                + "    c.category_name,\n"
                + "    b.detail,\n"
                + "    b.blog_image,\n"
                + "    b.view_able,\n"
                + "    u.user_fullname\n"
                + "FROM \n"
                + "    blogs b\n"
                + "INNER JOIN \n"
                + "    users u ON b.user_id = u.user_id\n"
                + "INNER JOIN \n"
                + "    category c ON b.category_id = c.category_id\n"
                + "WHERE \n"
                + "    b.view_able = 1 \n" // Chỉ lấy các bài viết có thể xem (view_able = 1)
                + "ORDER BY \n"
                + "    b.blog_created_date DESC \n" // Sắp xếp theo ngày tạo giảm dần (bài mới nhất trước)
                + "LIMIT 3 "); // Giới hạn chỉ lấy 3 bài viết

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement st = connection.prepareStatement(sql.toString());

            // Thực thi truy vấn
            ResultSet rs = st.executeQuery();

            // Duyệt qua kết quả trả về
            while (rs.next()) {
                Blog c = new Blog(
                        rs.getInt("blog_id"), // ID bài viết
                        rs.getInt("user_id"), // ID người dùng
                        rs.getString("title"), // Tiêu đề bài viết
                        rs.getString("bi"), // Mô tả ngắn bài viết
                        rs.getDate("blog_created_date"), // Ngày tạo bài viết
                        rs.getInt("category_id"), // ID danh mục bài viết
                        rs.getString("category_name"), // Tên danh mục
                        rs.getString("detail"), // Nội dung chi tiết bài viết
                        rs.getString("blog_image"), // Ảnh bài viết
                        rs.getInt("view_able"), // Trạng thái hiển thị
                        rs.getString("user_fullname") // Tên người đăng bài
                );

                // Thêm bài viết vào danh sách
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e); // In ra lỗi nếu có
        }

        return list; // Trả về danh sách 3 bài viết gần nhất
    }

    public List<Blog> get5RecentPost() { // 
        List<Blog> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT \n"
                + "    b.blog_id,\n"
                + "    b.user_id,\n"
                + "    b.title,\n"
                + "    b.bi,\n"
                + "    b.blog_created_date,\n"
                + "    b.category_id,\n"
                + "    c.category_name,\n"
                + "    b.detail,\n"
                + "    b.blog_image,\n"
                + "    b.view_able,\n"
                + "	u.user_fullname\n"
                + "FROM \n"
                + "    blogs b\n"
                + "INNER JOIN \n"
                + "    users u ON b.user_id = u.user_id\n"
                + "INNER JOIN \n"
                + "    category c ON b.category_id = c.category_id\n"
                + "WHERE     \n"
                + "    b.view_able = 1 \n"
                + "ORDER BY     \n"
                + "     b.blog_created_date DESC \n"
                + "LIMIT 5 ");

        try {
            PreparedStatement st = connection.prepareStatement(sql.toString());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = new Blog(rs.getInt("blog_id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("bi"),
                        rs.getDate("blog_created_date"),
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("detail"),
                        rs.getString("blog_image"),
                        rs.getInt("view_able"),
                        rs.getString("user_fullname")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        return list;
    }

    public Blog getBlogDetail(String blogId) {
        // Câu lệnh SQL để lấy chi tiết bài viết dựa trên blogId
        String sql = "SELECT \n"
                + "    b.blog_id,\n"
                + "    b.user_id,\n"
                + "    b.title,\n"
                + "    b.bi,\n"
                + "    b.blog_created_date,\n"
                + "    b.category_id,\n"
                + "    c.category_name,\n"
                + "    b.detail,\n"
                + "    b.blog_image,\n"
                + "    b.view_able,\n"
                + "    u.user_fullname\n"
                + "FROM blogs b\n"
                + "INNER JOIN users u ON b.user_id = u.user_id\n"
                + "INNER JOIN category c ON b.category_id = c.category_id\n"
                + "WHERE b.blog_id = ? ;"; // Điều kiện tìm kiếm bài viết theo blog_id

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, blogId); // Gán tham số blogId vào câu lệnh SQL

            // Thực thi truy vấn
            ResultSet rs = st.executeQuery();

            // Kiểm tra xem có dữ liệu hay không
            if (rs.next()) {
                // Tạo đối tượng Blog từ dữ liệu trong ResultSet
                Blog c = new Blog(
                        rs.getInt("blog_id"), // ID bài viết
                        rs.getInt("user_id"), // ID người đăng bài
                        rs.getString("title"), // Tiêu đề bài viết
                        rs.getString("bi"), // Mô tả ngắn
                        rs.getDate("blog_created_date"), // Ngày tạo bài viết
                        rs.getInt("category_id"), // ID danh mục bài viết
                        rs.getString("category_name"), // Tên danh mục
                        rs.getString("detail"), // Nội dung chi tiết bài viết
                        rs.getString("blog_image"), // Ảnh bài viết
                        rs.getInt("view_able"), // Trạng thái hiển thị
                        rs.getString("user_fullname") // Tên người đăng bài
                );

                return c; // Trả về bài viết tìm thấy
            }
        } catch (SQLException e) {
            System.out.println(e); // In ra lỗi nếu có
        }

        return null; // Trả về null nếu không tìm thấy bài viết hoặc xảy ra lỗi
    }

    public List<Category> getAllCategory() {
        // Tạo danh sách để lưu trữ các danh mục
        List<Category> list = new ArrayList<>();

        // Câu lệnh SQL để lấy tất cả danh mục từ bảng 'category'
        String sql = "SELECT \n"
                + "    `category`.`category_id`,\n"
                + "    `category`.`category_name`,\n"
                + "    `category`.`icon` AS `category_icon`,\n"
                + "    `category`.`status`\n"
                + "FROM `category`;";

        try {
            // Chuẩn bị câu lệnh SQL
            PreparedStatement st = connection.prepareStatement(sql);

            // Thực thi truy vấn và nhận kết quả
            ResultSet rs = st.executeQuery();

            // Duyệt qua từng dòng dữ liệu trong ResultSet
            while (rs.next()) {
                // Tạo đối tượng Category từ dữ liệu đọc được
                Category c = new Category(
                        rs.getInt("category_id"), // Lấy ID danh mục
                        rs.getString("category_name"), // Lấy tên danh mục
                        rs.getString("category_icon"), // Lấy biểu tượng danh mục
                        rs.getInt("status")
                );

                // Thêm đối tượng Category vào danh sách
                list.add(c);
            }
        } catch (SQLException e) {
            System.err.println(e); // In lỗi ra màn hình nếu có lỗi xảy ra
        }

        // Trả về danh sách danh mục đã lấy được
        return list;
    }

}
