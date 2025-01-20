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
public class reservationdetail1 {

    private int reservation_detail_id;
    private int reservation_id;
    private int service_id;
    private float price;
    private int quantity;
    private int num_of_person;
    private int category_id;
    private int doctor_id;
    private int nurse_id;
    private Date begin_time;

    public reservationdetail1() {
    }

    public reservationdetail1(int reservation_detail_id, int reservation_id, int service_id, float price, int quantity, int num_of_person, int category_id, int doctor_id, int nurse_id, Date begin_time) {
        this.reservation_detail_id = reservation_detail_id;
        this.reservation_id = reservation_id;
        this.service_id = service_id;
        this.price = price;
        this.quantity = quantity;
        this.num_of_person = num_of_person;
        this.category_id = category_id;
        this.doctor_id = doctor_id;
        this.nurse_id = nurse_id;
        this.begin_time = begin_time;
    }

    public int getReservation_detail_id() {
        return reservation_detail_id;
    }

    public void setReservation_detail_id(int reservation_detail_id) {
        this.reservation_detail_id = reservation_detail_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getNum_of_person() {
        return num_of_person;
    }

    public void setNum_of_person(int num_of_person) {
        this.num_of_person = num_of_person;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getNurse_id() {
        return nurse_id;
    }

    public void setNurse_id(int nurse_id) {
        this.nurse_id = nurse_id;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }
    
    

}
