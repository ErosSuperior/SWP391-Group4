package model;

/**
 * Represents a Category entity.
 */
public class Category {
    private int categoryId;
    private String categoryName;
    private String icon;

    // Constructor
    public Category() {
    }

    public Category(int categoryId, String categoryName, String icon) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.icon = icon;
    }

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}