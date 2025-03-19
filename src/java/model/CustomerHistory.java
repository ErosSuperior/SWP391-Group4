/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;



public class CustomerHistory {
    private int history_id;         
    private int user_id;               
    private String user_fullname;    
    private boolean user_gender;   
    private String user_address;       
    private String user_phone;         
    private int updated_by_id;         
    private String updated_by_name;
    private Date updated_date; 

    public CustomerHistory() {
    }

    public CustomerHistory(int history_id, int user_id, String user_fullname, boolean user_gender, String user_address, String user_phone, int updated_by_id, String updated_by_name, Date updated_date) {
        this.history_id = history_id;
        this.user_id = user_id;
        this.user_fullname = user_fullname;
        this.user_gender = user_gender;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.updated_by_id = updated_by_id;
        this.updated_by_name = updated_by_name;
        this.updated_date = updated_date;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    public boolean isUser_gender() {
        return user_gender;
    }

    public void setUser_gender(boolean user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public int getUpdated_by_id() {
        return updated_by_id;
    }

    public void setUpdated_by_id(int updated_by_id) {
        this.updated_by_id = updated_by_id;
    }

    public String getUpdated_by_name() {
        return updated_by_name;
    }

    public void setUpdated_by_name(String updated_by_name) {
        this.updated_by_name = updated_by_name;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    
}
