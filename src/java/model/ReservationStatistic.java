/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class ReservationStatistic {
    private int total_reservation;
    private double avg_cost;
    private int success_reservation,cancelled_reservation;

    public ReservationStatistic() {
    }

    public ReservationStatistic(int total_reservation, double avg_cost, int success_reservation, int cancelled_reservation) {
        this.total_reservation = total_reservation;
        this.avg_cost = avg_cost;
        this.success_reservation = success_reservation;
        this.cancelled_reservation = cancelled_reservation;
    }

    public int getTotal_reservation() {
        return total_reservation;
    }

    public void setTotal_reservation(int total_reservation) {
        this.total_reservation = total_reservation;
    }

    public double getAvg_cost() {
        return avg_cost;
    }

    public void setAvg_cost(double avg_cost) {
        this.avg_cost = avg_cost;
    }

    public int getSuccess_reservation() {
        return success_reservation;
    }

    public void setSuccess_reservation(int success_reservation) {
        this.success_reservation = success_reservation;
    }

    public int getCancelled_reservation() {
        return cancelled_reservation;
    }

    public void setCancelled_reservation(int cancelled_reservation) {
        this.cancelled_reservation = cancelled_reservation;
    }
    
    
}
