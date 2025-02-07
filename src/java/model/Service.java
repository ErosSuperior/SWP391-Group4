/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author thang
 */
public class Service {

    private int serviceId;
    private String serviceTitle;
    private String serviceBi;
    private Date serviceCreatedDate;
    private int categoryId;
    private double servicePrice;
    private double serviceDiscount;
    private String serviceDetail;
    private double serviceRateStar;
    private int serviceVote;
    private int serviceStatus;
    private String serviceImage;
    private String categoryTitle;
    private String categoryIcon;

    
    
    public Service() {
    }

    public Service(int serviceId, String serviceTitle, String serviceBi, Date serviceCreatedDate, int categoryId, double servicePrice, double serviceDiscount, String serviceDetail, double serviceRateStar, int serviceVote, int serviceStatus, String serviceImage) {
        this.serviceId = serviceId;
        this.serviceTitle = serviceTitle;
        this.serviceBi = serviceBi;
        this.serviceCreatedDate = serviceCreatedDate;
        this.categoryId = categoryId;
        this.servicePrice = servicePrice;
        this.serviceDiscount = serviceDiscount;
        this.serviceDetail = serviceDetail;
        this.serviceRateStar = serviceRateStar;
        this.serviceVote = serviceVote;
        this.serviceStatus = serviceStatus;
        this.serviceImage = serviceImage;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceBi() {
        return serviceBi;
    }

    public void setServiceBi(String serviceBi) {
        this.serviceBi = serviceBi;
    }

    public Date getServiceCreatedDate() {
        return serviceCreatedDate;
    }

    public void setServiceCreatedDate(Date serviceCreatedDate) {
        this.serviceCreatedDate = serviceCreatedDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public double getServiceDiscount() {
        return serviceDiscount;
    }

    public void setServiceDiscount(double serviceDiscount) {
        this.serviceDiscount = serviceDiscount;
    }

    public String getServiceDetail() {
        return serviceDetail;
    }

    public void setServiceDetail(String serviceDetail) {
        this.serviceDetail = serviceDetail;
    }

    public double getServiceRateStar() {
        return serviceRateStar;
    }

    public void setServiceRateStar(double serviceRateStar) {
        this.serviceRateStar = serviceRateStar;
    }

    public int getServiceVote() {
        return serviceVote;
    }

    public void setServiceVote(int serviceVote) {
        this.serviceVote = serviceVote;
    }

    public int getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }
}
