/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class ReservationDetail {

    private int reservation_detail_id, reservation_id, service_id;
    private String service_title;
    private double price;
    private String image_link;
    private int quantity, category_id;
    private String category_name;
    private int staff_id;
    private Date begin_time;
    private int slot, children_id;

    public ReservationDetail() {
    }

    public ReservationDetail(int reservation_detail_id, int reservation_id, int service_id, String service_title, double price, int quantity, int category_id, String category_name, int staff_id, Date begin_time, int slot, int children_id, String image_link) {
        this.reservation_detail_id = reservation_detail_id;
        this.reservation_id = reservation_id;
        this.service_id = service_id;
        this.service_title = service_title;
        this.price = price;
        this.quantity = quantity;
        this.category_id = category_id;
        this.category_name = category_name;
        this.staff_id = staff_id;
        this.begin_time = begin_time;
        this.slot = slot;
        this.children_id = children_id;
        this.image_link = image_link;
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

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
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

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

}
