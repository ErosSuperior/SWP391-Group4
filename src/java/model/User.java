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
public class User {
    private int user_id;
    private String user_fullname;
    private boolean user_gender;
    private String user_address;
    private String user_password;
    private String user_email;
    private String user_phone;
    private int role_id;
    private boolean user_status;
    private String user_image;

    public User() {
    }

    public User(int user_id, String user_fullname, boolean user_gender, String user_address, String user_password, String user_email, String user_phone, int user_role, boolean user_status, String user_image) {
        this.user_id = user_id;
        this.user_fullname = user_fullname;
        this.user_gender = user_gender;
        this.user_address = user_address;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_phone = user_phone;
        this.role_id = role_id;
        this.user_status = user_status;
        this.user_image = user_image;
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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
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

    public int getUser_role() {
        return role_id;
    }

    public void setUser_role(int role_id) {
        this.role_id = role_id;
    }

    public boolean isUser_status() {
        return user_status;
    }

    public void setUser_status(boolean user_status) {
        this.user_status = user_status;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    @Override
    public String toString() {
        return "user{" + "user_id=" + user_id + ", user_fullname=" + user_fullname + ", user_gender=" + user_gender + ", user_address=" + user_address + ", user_password=" + user_password + ", user_email=" + user_email + ", user_phone=" + user_phone + ", role_id=" + role_id + ", user_status=" + user_status + ", user_image=" + user_image + '}';
    }
}
