/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author truon
 */
public class comment_load {
    private String user_fullname;
    private String user_image;
    private String content;
    private Date comment_date;

    public comment_load() {
    }

    public comment_load(String user_fullname, String user_image, String content, Date comment_date) {
        this.user_fullname = user_fullname;
        this.user_image = user_image;
        this.content = content;
        this.comment_date = comment_date;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

   
    
}
