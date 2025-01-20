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
public class Prescription {
    private int prescription_details_id;
    private int medical_id;
    private int medicine_id;
    private int quantity;
    private double price;

    public Prescription() {
    }

    public Prescription(int prescription_details_id, int medical_id, int medicine_id, int quantity, double price) {
        this.prescription_details_id = prescription_details_id;
        this.medical_id = medical_id;
        this.medicine_id = medicine_id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getPrescription_details_id() {
        return prescription_details_id;
    }

    public void setPrescription_details_id(int prescription_details_id) {
        this.prescription_details_id = prescription_details_id;
    }

    public int getMedical_id() {
        return medical_id;
    }

    public void setMedical_id(int medical_id) {
        this.medical_id = medical_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
