/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author truon
 */
public class listservicemanager {
    private int service_id;
    private String image_link;
    private String category_name;
    private String service_title;
    private float service_price;
    private float service_discount;
    private boolean service_status;

    public listservicemanager() {
    }

    public listservicemanager(int service_id, String image_link, String category_name, String service_title, float service_price, float service_discount, boolean service_status) {
        this.service_id = service_id;
        this.image_link = image_link;
        this.category_name = category_name;
        this.service_title = service_title;
        this.service_price = service_price;
        this.service_discount = service_discount;
        this.service_status = service_status;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public float getService_price() {
        return service_price;
    }

    public void setService_price(float service_price) {
        this.service_price = service_price;
    }

    public float getService_discount() {
        return service_discount;
    }

    public void setService_discount(float service_discount) {
        this.service_discount = service_discount;
    }

    public boolean isService_status() {
        return service_status;
    }

    public void setService_status(boolean service_status) {
        this.service_status = service_status;
    }

   
    
}
