/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @authoro admin
 */
public class image {
    public int image_id;
    public int service_id;
    public String image_link;

    public image() {
    }

    public image(int image_id, int service_id, String image_link) {
        this.image_id = image_id;
        this.service_id = service_id;
        this.image_link = image_link;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
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
    
}
