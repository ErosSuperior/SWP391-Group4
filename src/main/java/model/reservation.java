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
public class reservation {
    private int reservation_id;
    private int user_id;
    private float total_price;
    private String note;
    private int reservation_status;
    private Date created_date;

    public reservation() {
    }

    public reservation(int reservation_id, int user_id, float total_price, String note, int reservation_status, Date created_date) {
        this.reservation_id = reservation_id;
        this.user_id = user_id;
        this.total_price = total_price;
        this.note = note;
        this.reservation_status = reservation_status;
        this.created_date = created_date;
    }
    public reservation(int user_id, float total_price, String note, int reservation_status) {

        this.user_id = user_id;
        this.total_price = total_price;
        this.note = note;
        this.reservation_status = reservation_status;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(int reservation_status) {
        this.reservation_status = reservation_status;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    
}
