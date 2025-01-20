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
public class feedback {
    private int feedback_id;
    private int user_id;
    private int reservation_id;
    private String content;
    private String name;
    private boolean gender;
    private String email;
    private String mobile;
    private String feedback_image;
    private int rateStar;
    private boolean status;

    public feedback() {
    }
    public feedback( int user_id, int reservation_id, String content, String name, boolean gender, String email, String mobile, String feedback_image, int rateStar, boolean status) {
        this.user_id = user_id;
        this.reservation_id = reservation_id;
        this.content = content;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.feedback_image = feedback_image;
        this.rateStar = rateStar;
        this.status = status;
    }

    public feedback(int feedback_id, int user_id, int reservation_id, String content, String name, boolean gender, String email, String mobile, String feedback_image, int rateStar) {
        this.feedback_id = feedback_id;
        this.user_id = user_id;
        this.reservation_id = reservation_id;
        this.content = content;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.feedback_image = feedback_image;
        this.rateStar = rateStar;
    }

    public feedback(int feedback_id, int user_id, int reservation_id, String content, String name, boolean gender, String email, String mobile, String feedback_image, int rateStar, boolean status) {
        this.feedback_id = feedback_id;
        this.user_id = user_id;
        this.reservation_id = reservation_id;
        this.content = content;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.feedback_image = feedback_image;
        this.rateStar = rateStar;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFeedback_image() {
        return feedback_image;
    }

    public void setFeedback_image(String feedback_image) {
        this.feedback_image = feedback_image;
    }

    public int getRateStar() {
        return rateStar;
    }

    public void setRateStar(int rateStar) {
        this.rateStar = rateStar;
    }

    
}
