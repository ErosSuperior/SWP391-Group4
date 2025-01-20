/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class blog {
    private int blog_id;
    private int user_id;
    private String title;
    private String bi;
    private Date blog_created_date;
    private int category_id;
    private String detail;
    private String blog_image;
    private boolean view_able;

    public blog() {
    }

    public blog(int blog_id, int user_id, String title, String bi, Date blog_created_date, int category_id, String detail, String blog_image, boolean view_able) {
        this.blog_id = blog_id;
        this.user_id = user_id;
        this.title = title;
        this.bi = bi;
        this.blog_created_date = blog_created_date;
        this.category_id = category_id;
        this.detail = detail;
        this.blog_image = blog_image;
        this.view_able = view_able;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public Date getBlog_created_date() {
        return blog_created_date;
    }

    public void setBlog_created_date(Date blog_created_date) {
        this.blog_created_date = blog_created_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBlog_image() {
        return blog_image;
    }

    public void setBlog_image(String blog_image) {
        this.blog_image = blog_image;
    }

    public boolean isView_able() {
        return view_able;
    }

    public void setView_able(boolean view_able) {
        this.view_able = view_able;
    }
    
    
}
