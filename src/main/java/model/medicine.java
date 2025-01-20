/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author win
 */
public class medicine {
    private int medicine_id;
    private String medicine_name;
    private String medicine_image;
    private double medicine_price;
    private int medicine_unit;
    private int medinice_quantity;

    public medicine() {
    }

    public medicine(int medicine_id, String medicine_name, String medicine_image, double medicine_price, int medicine_unit, int medinice_quantity) {
        this.medicine_id = medicine_id;
        this.medicine_name = medicine_name;
        this.medicine_image = medicine_image;
        this.medicine_price = medicine_price;
        this.medicine_unit = medicine_unit;
        this.medinice_quantity = medinice_quantity;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_image() {
        return medicine_image;
    }

    public void setMedicine_image(String medicine_image) {
        this.medicine_image = medicine_image;
    }

    public double getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(double medicine_price) {
        this.medicine_price = medicine_price;
    }

    public int getMedicine_unit() {
        return medicine_unit;
    }

    public void setMedicine_unit(int medicine_unit) {
        this.medicine_unit = medicine_unit;
    }

    public int getMedinice_quantity() {
        return medinice_quantity;
    }

    public void setMedinice_quantity(int medinice_quantity) {
        this.medinice_quantity = medinice_quantity;
    }
    
}
