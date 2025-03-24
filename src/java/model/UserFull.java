/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;


public class UserFull extends User{
    private Date user_created_date;

    public UserFull() {
    }

    public UserFull( int user_id, String user_fullname, boolean user_gender, String user_address, String user_password, String user_email, String user_phone, int role_id, boolean user_status, String user_image,Date user_created_date) {
        super(user_id, user_fullname, user_gender, user_address, user_password, user_email, user_phone, role_id, user_status, user_image);
        this.user_created_date = user_created_date;
    }

    public Date getUser_created_date() {
        return user_created_date;
    }

    public void setUser_created_date(Date user_created_date) {
        this.user_created_date = user_created_date;
    }

    
}
