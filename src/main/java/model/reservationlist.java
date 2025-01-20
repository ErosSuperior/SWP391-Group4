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
public class reservationlist {
    private int reservation_id;
    private Date created_date;
    private  String user_fullname;
    private String service_title;
    private int num_of_person;
    private float total_price;
    private int reservation_status;

    public reservationlist() {
    }

    public reservationlist(int reservation_id, Date created_date, String user_fullname, String service_title, int num_of_person, float total_price, int reservation_status) {
        this.reservation_id = reservation_id;
        this.created_date = created_date;
        this.user_fullname = user_fullname;
        this.service_title = service_title;
        this.num_of_person = num_of_person;
        this.total_price = total_price;
        this.reservation_status = reservation_status;
    }

    public reservationlist(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public reservationlist(int aInt, Date date, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public int getNum_of_person() {
        return num_of_person;
    }

    public void setNum_of_person(int num_of_person) {
        this.num_of_person = num_of_person;
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

   
}
