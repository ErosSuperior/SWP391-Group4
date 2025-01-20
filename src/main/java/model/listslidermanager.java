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
public class listslidermanager {
    private int slider_id;
    private String image_link;
    private String slider_title;
    private boolean slider_status;

    public listslidermanager() {
    }

    public listslidermanager(int slider_id, String image_link, String slider_title, boolean slider_status) {
        this.slider_id = slider_id;
        this.image_link = image_link;
        this.slider_title = slider_title;
        this.slider_status = slider_status;
    }

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getSlider_title() {
        return slider_title;
    }

    public void setSlider_title(String slider_title) {
        this.slider_title = slider_title;
    }

    public boolean isSlider_status() {
        return slider_status;
    }

    public void setSlider_status(boolean slider_status) {
        this.slider_status = slider_status;
    }


   
}
