/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class PostList {
    private int blog_id;
    private String blog_image;
    private String title;
    private String category_name;
    private String user_fullname;
    private String detail;
    private boolean view_able;

    public PostList() {
    }

    public PostList(int blog_id, String blog_image, String title, String category_name, String user_fullname, String detail, boolean view_able) {
        this.blog_id = blog_id;
        this.blog_image = blog_image;
        this.title = title;
        this.category_name = category_name;
        this.user_fullname = user_fullname;
        this.detail = detail;
        this.view_able = view_able;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public String getBlog_image() {
        return blog_image;
    }

    public void setBlog_image(String blog_image) {
        this.blog_image = blog_image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isView_able() {
        return view_able;
    }

    public void setView_able(boolean view_able) {
        this.view_able = view_able;
    }
    
    
    
}
