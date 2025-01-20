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
public class SliderHomeServices {
    private int slider_id;
    private int service_id;
    private String image_link;
    private String service_title;
    private String notes;

    public SliderHomeServices() {
    }

    public SliderHomeServices(int slider_id, int service_id, String image_link, String service_title, String notes) {
        this.slider_id = slider_id;
        this.service_id = service_id;
        this.image_link = image_link;
        this.service_title = service_title;
        this.notes = notes;
    }
    
    

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
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

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

   
    
}