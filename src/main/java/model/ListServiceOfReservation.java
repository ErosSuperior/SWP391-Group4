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
public class ListServiceOfReservation {
    private int reservation_detail_id;
    private String image;
    private String service_title;
    private Date begin_time;
    private int slot;
    private int reservation_status;
    private float service_price;
    private int quantity;
    private String children_name;
    private String doctor_name;
    private String nurse_name;

    public ListServiceOfReservation() {
    }

    public ListServiceOfReservation(int reservation_detail_id, String image, String service_title, Date begin_time, int slot, int reservation_status, float service_price, int quantity, String children_name, String doctor_name, String nurse_name) {
        this.reservation_detail_id = reservation_detail_id;
        this.image = image;
        this.service_title = service_title;
        this.begin_time = begin_time;
        this.slot = slot;
        this.reservation_status = reservation_status;
        this.service_price = service_price;
        this.quantity = quantity;
        this.children_name = children_name;
        this.doctor_name = doctor_name;
        this.nurse_name = nurse_name;
    }

    public int getReservation_detail_id() {
        return reservation_detail_id;
    }

    public void setReservation_detail_id(int reservation_detail_id) {
        this.reservation_detail_id = reservation_detail_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(int reservation_status) {
        this.reservation_status = reservation_status;
    }

    public float getService_price() {
        return service_price;
    }

    public void setService_price(float service_price) {
        this.service_price = service_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getChildren_name() {
        return children_name;
    }

    public void setChildren_name(String children_name) {
        this.children_name = children_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getNurse_name() {
        return nurse_name;
    }

    public void setNurse_name(String nurse_name) {
        this.nurse_name = nurse_name;
    }

   
}


