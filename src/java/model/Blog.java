/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author thang
 */
public class Blog {
    private int blogId;
    private int blogUserId;
    private String blogTitle;
    private String blogBi;
    private java.sql.Date blogCreatedDate;
    private int blogCategory;
    private String blogCategoryName;
    private String blogDetail;
    private String blogImage;
    private int blogStatus;
    private String authorName;
    

    public Blog() {
    }

    public Blog(int blogId, int blogUserId, String blogTitle, String blogBi, Date blogCreatedDate, int blogCategory, String blogDetail, String blogImage, int blogStatus, String authorName) {
        this.blogId = blogId;
        this.blogUserId = blogUserId;
        this.blogTitle = blogTitle;
        this.blogBi = blogBi;
        this.blogCreatedDate = blogCreatedDate;
        this.blogCategory = blogCategory;
        this.blogDetail = blogDetail;
        this.blogImage = blogImage;
        this.blogStatus = blogStatus;
        this.authorName = authorName;
    }
    
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getBlogUserId() {
        return blogUserId;
    }

    public void setBlogUserId(int blogUserId) {
        this.blogUserId = blogUserId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogBi() {
        return blogBi;
    }

    public void setBlogBi(String blogBi) {
        this.blogBi = blogBi;
    }

    public Date getBlogCreatedDate() {
        return blogCreatedDate;
    }

    public void setBlogCreatedDate(Date blogCreatedDate) {
        this.blogCreatedDate = blogCreatedDate;
    }

    public int getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(int blogCategory) {
        this.blogCategory = blogCategory;
    }

    public String getBlogCategoryName() {
        return blogCategoryName;
    }

    public void setBlogCategoryName(String blogCategoryName) {
        this.blogCategoryName = blogCategoryName;
    }

    public String getBlogDetail() {
        return blogDetail;
    }

    public void setBlogDetail(String blogDetail) {
        this.blogDetail = blogDetail;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public int getBlogStatus() {
        return blogStatus;
    }

    public void setBlogStatus(int blogStatus) {
        this.blogStatus = blogStatus;
    }
    
    
}
