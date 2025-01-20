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
public class InformationUserReservation {

    private int user_id;
    private String user_name;
    private boolean user_gender;
    private String user_email;
    private String user_phone;

    public InformationUserReservation() {
    }

    public InformationUserReservation(int user_id, String user_name, boolean user_gender, String user_email, String user_phone) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_gender = user_gender;
        this.user_email = user_email;
        this.user_phone = user_phone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public boolean isUser_gender() {
        return user_gender;
    }

    public void setUser_gender(boolean user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

}
