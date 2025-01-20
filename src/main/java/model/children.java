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
public class children {
    private int children_id;
    private int user_id;
    private String children_name;
    private boolean children_gender;
    private int children_age;

    public children() {
    }

    public children(int children_id, int user_id, String children_name, boolean children_gender, int children_age) {
        this.children_id = children_id;
        this.user_id = user_id;
        this.children_name = children_name;
        this.children_gender = children_gender;
        this.children_age = children_age;
    }

    public int getChildren_id() {
        return children_id;
    }

    public void setChildren_id(int children_id) {
        this.children_id = children_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getChildren_name() {
        return children_name;
    }

    public void setChildren_name(String children_name) {
        this.children_name = children_name;
    }

    public boolean isChildren_gender() {
        return children_gender;
    }

    public void setChildren_gender(boolean children_gender) {
        this.children_gender = children_gender;
    }

    public int getChildren_age() {
        return children_age;
    }

    public void setChildren_age(int children_age) {
        this.children_age = children_age;
    }
    
}
