/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author win
 */
public class Reservation_Medical {
    private int medical_id;
    private int reservation_detail_id;
    private String diagnosis;
    private Date created_date;
    private int doctor_id;

    public Reservation_Medical() {
    }

    public Reservation_Medical(int medical_id, int reservation_detail_id, String diagnosis, Date created_date, int doctor_id) {
        this.medical_id = medical_id;
        this.reservation_detail_id = reservation_detail_id;
        this.diagnosis = diagnosis;
        this.created_date = created_date;
        this.doctor_id = doctor_id;
    }

    public int getMedical_id() {
        return medical_id;
    }

    public void setMedical_id(int medical_id) {
        this.medical_id = medical_id;
    }

    public int getReservation_detail_id() {
        return reservation_detail_id;
    }

    public void setReservation_detail_id(int reservation_detail_id) {
        this.reservation_detail_id = reservation_detail_id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
}
