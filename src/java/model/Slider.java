package model;

public class Slider {
    private int sliderId;
    private String title;
    private boolean status;
    private String note;
    private String image;
    private int categoryId;
    private int serviceId;
    
    public Slider(){
        
    }

    public Slider(int sliderId, String title, boolean status, String note, String image, int categoryId, int serviceId) {
        this.sliderId = sliderId;
        this.title = title;
        this.status = status;
        this.note = note;
        this.image = image;
        this.categoryId = categoryId;
        this.serviceId = serviceId;
    }

    public int getSliderId() {
        return sliderId;
    }

    public void setSliderId(int sliderId) {
        this.sliderId = sliderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    
    
}
