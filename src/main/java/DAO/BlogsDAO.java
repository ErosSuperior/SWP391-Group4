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
import model.PostList;
import model.blog;
import model.service;

/**
 *
 * @author admin
 */
public class BlogsDAO {

    public List<blog> getBlogsIndexNew() {
        List<blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select TOP 9 * from blogs order by blog_created_date";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog blog = new blog(rs.getInt("blog_id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("bi"), rs.getDate("blog_created_date"), rs.getInt("category_id"), rs.getString("detail"), rs.getString("blog_image"), rs.getBoolean("view_able"));
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<blog> getAllBlogs() {
        List<blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from blogs";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog blog = new blog(rs.getInt("blog_id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("bi"), rs.getDate("blog_created_date"), rs.getInt("category_id"), rs.getString("detail"), rs.getString("blog_image"), rs.getBoolean("view_able"));
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<blog> getTop5NewBlogs() {
        List<blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select TOP 5 * from blogs order by blog_created_date";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog blog = new blog(rs.getInt("blog_id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("bi"), rs.getDate("blog_created_date"), rs.getInt("category_id"), rs.getString("detail"), rs.getString("blog_image"), rs.getBoolean("view_able"));
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public blog getBlogsById(int id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from Blogs where blog_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new blog(rs.getInt("blog_id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("bi"), rs.getDate("blog_created_date"), rs.getInt("category_id"), rs.getString("detail"), rs.getString("blog_image"), rs.getBoolean("view_able"));
            }

        } catch (Exception e) {
        }
        return null;
    }



    public static void main(String[] args) {
        BlogsDAO ser = new BlogsDAO();

    }

    public int getQuantityByCateId(int category_id) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select COUNT(*) from [service]\n"
                    + "where category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, category_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return 0;
    }

    public List<blog> getAllBlogsByCateId(int cid) {
        List<blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from blogs where category_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog blog = new blog(rs.getInt("blog_id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("bi"), rs.getDate("blog_created_date"), rs.getInt("category_id"), rs.getString("detail"), rs.getString("blog_image"), rs.getBoolean("view_able"));
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<blog> getAllBlogsSearch(String txt) {
        List<blog> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select * from Blogs\n"
                    + "where title like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                blog blog = new blog(rs.getInt("blog_id"), rs.getInt("user_id"), rs.getString("title"), rs.getString("bi"), rs.getDate("blog_created_date"), rs.getInt("category_id"), rs.getString("detail"), rs.getString("blog_image"), rs.getBoolean("view_able"));
                list.add(blog);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void EditBlogById(int id, String title, String bi, String date, int category, String detail, String image, boolean status) {
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "UPDATE [blogs]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[bi] = ?\n"
                    + "      ,[category_id] = ?\n"
                    + "      ,[detail] = ?\n"
                    + "      ,[blog_image] = ?\n"
                    + "      ,[view_able] = ?\n"
                    + " WHERE blog_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, bi);
            ps.setInt(3, category);
            ps.setString(4, detail);
            ps.setString(5, image);
            ps.setBoolean(6, status);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }
    
    public List<PostList> getPostList() {
        List<PostList> list = new ArrayList<>();
        try {
            Connection conn = new DBContext().getConnection();
            String sql = "select s.blog_id,s.blog_image,s.title,c.category_name,u.user_fullname,s.detail,s.view_able from blogs s join [user] u\n"
                    + "on s.user_id = u.user_id\n"
                    + "join category c\n"
                    + "on s.category_id = c.category_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PostList postlist = new PostList(rs.getInt("blog_id"), rs.getString("blog_image"), rs.getString("title"), rs.getString("category_name"), rs.getString("user_fullname"), rs.getString("detail"), rs.getBoolean("view_able"));
                list.add((postlist));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

}
