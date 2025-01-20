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
public class CardMedicine {
    private medicine medicine;
    private int quantity;

    public CardMedicine() {
    }

    public CardMedicine(medicine medicine, int quantity) {
        this.medicine = medicine;
        this.quantity = quantity;
    }

    public medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
