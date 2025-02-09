package model;

import java.util.List;

public class SearchResponse<T> {

    private int totalElements;
    private List<T> content;
    private int role;
    private int pageNo;
    private int pageSize;
    private String sortBy;
    private String sortDir;

    public SearchResponse(int totalElements, List<T> content, int pageNo, int pageSize, String sortBy, String sortDir) {
        this.totalElements = totalElements;
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.sortDir = sortDir;
    }

    public SearchResponse(int totalElements, List<T> content, int pageNo, int pageSize) {
        this.totalElements = totalElements;
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
    
    public SearchResponse(int totalElements, List<T> content, int pageNo, int pageSize, int role) {
        this.totalElements = totalElements;
        this.content = content;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.role = role;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    @Override
    public String toString() {
        return "SearchResponse{" + "totalElements=" + totalElements + ", content=" + content + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", sortBy=" + sortBy + ", sortDir=" + sortDir + '}';
    }
    
    
}
