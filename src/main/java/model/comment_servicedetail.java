/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author truon
 */
public class comment_servicedetail {

    private int comment_id;
    private String comment_content;
    private Date comment_date;
    private String comment_creatByUser;

    public comment_servicedetail() {
    }

    public comment_servicedetail(int comment_id, String comment_content, Date comment_date, String comment_creatByUser) {
        this.comment_id = comment_id;
        this.comment_content = comment_content;
        this.comment_date = comment_date;
        this.comment_creatByUser = comment_creatByUser;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_creatByUser() {
        return comment_creatByUser;
    }

    public void setComment_creatByUser(String comment_creatByUser) {
        this.comment_creatByUser = comment_creatByUser;
    }

  
}
