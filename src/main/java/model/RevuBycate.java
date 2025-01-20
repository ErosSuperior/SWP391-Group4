/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author admin
 */
public class RevuBycate {
    public float total;
    public int cateid;

    public RevuBycate() {
    }

    public RevuBycate(float total, int cateid) {
        this.total = total;
        this.cateid = cateid;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }
    
}
