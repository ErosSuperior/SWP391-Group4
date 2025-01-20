/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author win
 */
public class ReservationDetail {
    private int reservationDetail_id;
    private int reservation_id;
    private int service_id;
    private float price;
    private int quantity;
    private int numOfPerson;
    private int category_id;
    private int doctor_id;
    private int nurse_id;
    private Date begin_time;
    private int slot;
    private int children_id;

    public ReservationDetail() {
        
    }
public ReservationDetail(int reservation_id, int service_id, float price, int quantity, int numOfPerson, int category_id, int doctor_id, int nurse_id, Date begin_time, int slot, int children_id) {
        this.reservationDetail_id = reservationDetail_id;
        this.reservation_id = reservation_id;
        this.service_id = service_id;
        this.price = price;
        this.quantity = quantity;
        this.numOfPerson = numOfPerson;
        this.category_id = category_id;
        this.doctor_id = doctor_id;
        this.nurse_id = nurse_id;
        this.begin_time = begin_time;
        this.slot = slot;
        this.children_id = children_id;
    }
    public ReservationDetail(int reservationDetail_id, int reservation_id, int service_id, float price, int quantity, int numOfPerson, int category_id, int doctor_id, int nurse_id, Date begin_time, int slot, int children_id) {
        this.reservationDetail_id = reservationDetail_id;
        this.reservation_id = reservation_id;
        this.service_id = service_id;
        this.price = price;
        this.quantity = quantity;
        this.numOfPerson = numOfPerson;
        this.category_id = category_id;
        this.doctor_id = doctor_id;
        this.nurse_id = nurse_id;
        this.begin_time = begin_time;
        this.slot = slot;
        this.children_id = children_id;
    }

    public int getReservationDetail_id() {
        return reservationDetail_id;
    }

    public void setReservationDetail_id(int reservationDetail_id) {
        this.reservationDetail_id = reservationDetail_id;
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

    public int getNumOfPerson() {
        return numOfPerson;
    }

    public void setNumOfPerson(int numOfPerson) {
        this.numOfPerson = numOfPerson;
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

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getChildren_id() {
        return children_id;
    }

    public void setChildren_id(int children_id) {
        this.children_id = children_id;
    }
    
    
    
}