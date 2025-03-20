/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author thang
 */
public class Reservation {

    private int reservation_id;
    private int user_id;
    private float total_price;
    private String note;
    private int status;
    private Date created_date;
    private int detail_id;
    private int service_id;
    private float price;
    private int quantity;
    private int category;
    private Date begin_time;
    private int slot;
    private int children_id;
    private int staff_id;
    private int payment_status;
    private String receiver_address;
    private String receiver_number;
    private String receiver_email;
    private String receiver_name;

    public Reservation() {
    }

    public Reservation(int reservation_id, int user_id, float total_price, String note, int status, int payment_status, Date created_date, String receiver_address, String receiver_number, String receiver_email, String receiver_name) {
        this.reservation_id = reservation_id;
        this.user_id = user_id;
        this.total_price = total_price;
        this.note = note;
        this.status = status;
        this.payment_status = payment_status;
        this.created_date = created_date;
        this.receiver_address = receiver_address;
        this.receiver_number = receiver_number;
        this.receiver_email = receiver_email;
        this.receiver_name = receiver_name;
    }

    public Reservation(int reservation_id, int user_id, float total_price, String note, int status, Date created_date) {
        this.reservation_id = reservation_id;
        this.user_id = user_id;
        this.total_price = total_price;
        this.note = note;
        this.status = status;
        this.created_date = created_date;
    }

    public Reservation(int reservation_id, int user_id, int detail_id, int service_id, float price, int quantity, int category, Date begin_time, int slot, int children_id) {
        this.reservation_id = reservation_id;
        this.user_id = user_id;
        this.detail_id = detail_id;
        this.service_id = service_id;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.begin_time = begin_time;
        this.slot = slot;
        this.children_id = children_id;
    }

    public Reservation(int user_id, Date created_date, int detail_id, int medical_id, String diagnosis) {
        this.user_id = user_id;
        this.created_date = created_date;
        this.detail_id = detail_id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(int payment_status) {
        this.payment_status = payment_status;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_number() {
        return receiver_number;
    }

    public void setReceiver_number(String receiver_number) {
        this.receiver_number = receiver_number;
    }

    public String getReceiver_email() {
        return receiver_email;
    }

    public void setReceiver_email(String receiver_email) {
        this.receiver_email = receiver_email;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

}
