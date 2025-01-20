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
public class MyReservation {
    public int reservation_id;
    public String service_title;
    public Date created_date;
    public Date begin_time;
    public int number_of_person;
    public int reservation_status;
    public float total_price;
    public String image;
    public int slot;
    public String note;

    public MyReservation() {
    }

    public MyReservation(int reservation_id, String service_title, Date created_date, Date begin_time, int number_of_person, int reservation_status, float total_price, String image, int slot, String note) {
        this.reservation_id = reservation_id;
        this.service_title = service_title;
        this.created_date = created_date;
        this.begin_time = begin_time;
        this.number_of_person = number_of_person;
        this.reservation_status = reservation_status;
        this.total_price = total_price;
        this.image = image;
        this.slot = slot;
        this.note = note;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public int getNumber_of_person() {
        return number_of_person;
    }

    public void setNumber_of_person(int number_of_person) {
        this.number_of_person = number_of_person;
    }

    public int getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(int reservation_status) {
        this.reservation_status = reservation_status;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
