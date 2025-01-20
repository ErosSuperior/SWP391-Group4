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
 * @author admin
 */
public class service {
    private int service_id;
    private String service_title;
    private String services_bi;
    private Date service_created_date;
    private int category_id;
    private float service_price;
    private float service_discount;
    private String service_detail;
    public int service_rateStar;
    private int service_vote;
    public List<image> image;

    public service() {
    }

    public service(int service_id, String service_title, String services_bi, Date service_created_date, int category_id, float service_price, float service_discount, String service_detail, int service_rateStar, int service_vote, List<image> image) {
        this.service_id = service_id;
        this.service_title = service_title;
        this.services_bi = services_bi;
        this.service_created_date = service_created_date;
        this.category_id = category_id;
        this.service_price = service_price;
        this.service_discount = service_discount;
        this.service_detail = service_detail;
        this.service_rateStar = service_rateStar;
        this.service_vote = service_vote;
        this.image = image;
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

    public String getServices_bi() {
        return services_bi;
    }

    public void setServices_bi(String services_bi) {
        this.services_bi = services_bi;
    }

    public Date getService_created_date() {
        return service_created_date;
    }

    public void setService_created_date(Date service_created_date) {
        this.service_created_date = service_created_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public String getService_detail() {
        return service_detail;
    }

    public void setService_detail(String service_detail) {
        this.service_detail = service_detail;
    }

    public int getService_rateStar() {
        return service_rateStar;
    }

    public void setService_rateStar(int service_rateStar) {
        this.service_rateStar = service_rateStar;
    }

    public int getService_vote() {
        return service_vote;
    }

    public void setService_vote(int service_vote) {
        this.service_vote = service_vote;
    }

    public List<image> getImage() {
        return image;
    }

    public void setImage(List<image> image) {
        this.image = image;
    }
    
    
}
