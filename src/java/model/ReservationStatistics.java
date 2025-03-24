/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class ReservationStatistics {
    private String month;
    private int success_count;
    private int cancelled_count;
    private int submitted_count;

    public ReservationStatistics() {
    }

    public ReservationStatistics(String month, int success_count, int cancelled_count, int submitted_count) {
        this.month = month;
        this.success_count = success_count;
        this.cancelled_count = cancelled_count;
        this.submitted_count = submitted_count;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(int success_count) {
        this.success_count = success_count;
    }

    public int getCancelled_count() {
        return cancelled_count;
    }

    public void setCancelled_count(int cancelled_count) {
        this.cancelled_count = cancelled_count;
    }

    public int getSubmitted_count() {
        return submitted_count;
    }

    public void setSubmitted_count(int submitted_count) {
        this.submitted_count = submitted_count;
    }

    
}
