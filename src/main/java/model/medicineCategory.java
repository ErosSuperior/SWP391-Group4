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
public class medicineCategory {
    private int medicineCate_id;
    private String medicineCate_name;

    public medicineCategory() {
    }

    public medicineCategory(int medicineCate_id, String medicineCate_name) {
        this.medicineCate_id = medicineCate_id;
        this.medicineCate_name = medicineCate_name;
    }

    public int getMedicineCate_id() {
        return medicineCate_id;
    }

    public void setMedicineCate_id(int medicineCate_id) {
        this.medicineCate_id = medicineCate_id;
    }

    public String getMedicineCate_name() {
        return medicineCate_name;
    }

    public void setMedicineCate_name(String medicineCate_name) {
        this.medicineCate_name = medicineCate_name;
    }
    
    
}
