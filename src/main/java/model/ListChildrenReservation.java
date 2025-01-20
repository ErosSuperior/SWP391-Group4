/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author win
 */
public class ListChildrenReservation {
    private int reservation_id;
    private int service_id;
    private String service_image;
    private String service_name;
    private List<children> listChil;

    public ListChildrenReservation() {
    }

    public ListChildrenReservation(int reservation_id, int service_id, String service_image, String service_name, List<children> listChil) {
        this.reservation_id = reservation_id;
        this.service_id = service_id;
        this.service_image = service_image;
        this.service_name = service_name;
        this.listChil = listChil;
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

    public String getService_image() {
        return service_image;
    }

    public void setService_image(String service_image) {
        this.service_image = service_image;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public List<children> getListChil() {
        return listChil;
    }

    public void setListChil(List<children> listChil) {
        this.listChil = listChil;
    }

    
}
