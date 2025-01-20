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
public class hotservices {
    private int count;
    private int services_id;

    public hotservices() {
    }

    public hotservices(int count, int services_id) {
        this.count = count;
        this.services_id = services_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getServices_id() {
        return services_id;
    }

    public void setServices_id(int services_id) {
        this.services_id = services_id;
    }
    
}
