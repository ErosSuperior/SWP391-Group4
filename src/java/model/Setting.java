/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Setting {
    private int setting_id;
    private String setting_name,setting_type,setting_description,setting_value,setting_status;

    public Setting() {
    }

    public Setting(int setting_id, String setting_name, String setting_type, String setting_description, String setting_value, String setting_status) {
        this.setting_id = setting_id;
        this.setting_name = setting_name;
        this.setting_type = setting_type;
        this.setting_description = setting_description;
        this.setting_value = setting_value;
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

    public String getSetting_type() {
        return setting_type;
    }

    public void setSetting_type(String setting_type) {
        this.setting_type = setting_type;
    }

    public String getSetting_description() {
        return setting_description;
    }

    public void setSetting_description(String setting_description) {
        this.setting_description = setting_description;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public String getSetting_status() {
        return setting_status;
    }

    public void setSetting_status(String setting_status) {
        this.setting_status = setting_status;
    }
    
    
}
