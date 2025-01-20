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
public class setting {
    private int setting_id;
    private String setting_name;
    private int setting_value;
    private String setting_description;
    private boolean setting_status;

    public setting() {
    }

    public setting(int setting_id, String setting_name, int setting_value, String setting_description, boolean setting_status) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
        this.setting_value = setting_value;
        this.setting_description = setting_description;
        this.setting_status = setting_status;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public String getSetting_name() {
        return setting_name;
    }

    public void setSetting_name(String setting_name) {
        this.setting_name = setting_name;
    }

    public int getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(int setting_value) {
        this.setting_value = setting_value;
    }

    public String getSetting_description() {
        return setting_description;
    }

    public void setSetting_description(String setting_description) {
        this.setting_description = setting_description;
    }

    public boolean isSetting_status() {
        return setting_status;
    }

    public void setSetting_status(boolean setting_status) {
        this.setting_status = setting_status;
    }
    
    
}
