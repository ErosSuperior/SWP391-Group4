/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ReservationDetailManager {
    private int reservation_id;
    private String user_fullname;
    private String user_email;
    private String user_phone;
    private Date created_date;
    private float total_price;
    private int reservation_status;
    private String image_link;
    private String service_title;
    private String category_name;
    private float price;
    private int num_of_person;

    public ReservationDetailManager() {
    }

    public ReservationDetailManager(int reservation_id, String user_fullname, String user_email, String user_phone, Date created_date, float total_price, int reservation_status, String image_link, String service_title, String category_name, float price, int num_of_person) {
        this.reservation_id = reservation_id;
        this.user_fullname = user_fullname;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.created_date = created_date;
        this.total_price = total_price;
        this.reservation_status = reservation_status;
        this.image_link = image_link;
        this.service_title = service_title;
        this.category_name = category_name;
        this.price = price;
        this.num_of_person = num_of_person;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public int getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(int reservation_status) {
        this.reservation_status = reservation_status;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum_of_person() {
        return num_of_person;
    }
    public void setNum_of_person(int num_of_person) {
        this.num_of_person = num_of_person;
    }
}