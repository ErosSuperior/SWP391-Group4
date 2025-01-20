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
public class EditReservationInformation {
    private int reservation_detail_id;
    private Date begin_time;
    private int quantity;
    private int slot;
    private String children_name;
    private String doctor_name;
    private String nurse_name;

    public EditReservationInformation() {
    }

    public EditReservationInformation(int reservation_detail_id, Date begin_time, int quantity, int slot, String children_name, String doctor_name, String nurse_name) {
        this.reservation_detail_id = reservation_detail_id;
        this.begin_time = begin_time;
        this.quantity = quantity;
        this.slot = slot;
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

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
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


