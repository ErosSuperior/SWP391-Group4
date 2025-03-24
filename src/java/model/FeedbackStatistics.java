/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class FeedbackStatistics {

    private int service_id;
    private String service_title, image_links;
    private double average_rating;
    private int vote_count;

    public FeedbackStatistics() {
    }

    public FeedbackStatistics(int service_id, String service_title, String image_links, double average_rating, int vote_count) {
        this.service_id = service_id;
        this.service_title = service_title;
        this.image_links = image_links;
        this.average_rating = average_rating;
        this.vote_count = vote_count;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_title() {
        return service_title;
    }

    public void setService_title(String service_title) {
        this.service_title = service_title;
    }

    public String getImage_links() {
        return image_links;
    }

    public void setImage_links(String image_links) {
        this.image_links = image_links;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(double average_rating) {
        this.average_rating = average_rating;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

}
