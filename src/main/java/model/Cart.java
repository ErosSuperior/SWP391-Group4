/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author win
 */
public class Cart {
    private service service;
    private int quantity;
    private int num_of_person;
    private List<Integer> children_id;
    private int doctor_id;
    private int nurse_id;
    private int slot;
    private Date begin_time;

    public Cart() {
    }

    public Cart(service service, int quantity, int num_of_person, List<Integer> children_id, int doctor_id, int nurse_id, int slot, Date begin_time) {
        this.service = service;
        this.quantity = quantity;
        this.num_of_person = num_of_person;
        this.children_id = children_id;
        this.doctor_id = doctor_id;
        this.nurse_id = nurse_id;
        this.slot = slot;
        this.begin_time = begin_time;
    }

    public service getService() {
        return service;
    }

    public void setService(service service) {
        this.service = service;
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

    public List<Integer> getChildren_id() {
        return children_id;
    }

    public void setChildren_id(List<Integer> children_id) {
        this.children_id = children_id;
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

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    } 
}