package com.cms.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.cms.model.Category;

public class ContentDTO {

//	private int contentId;
//    private List<Map<String, String>> jsonData;
//    private Byte status;
//    private LocalDateTime createdDate;
//    private LocalDateTime updatedDate;
//    private Category category;
//
//    public int getContentId() {
//        return contentId;
//    }
//
//    public void setContentId(int contentId) {
//        this.contentId = contentId;
//    }
//
//    public List<Map<String, String>> getJsonData() {
//        return jsonData;
//    }
//
//    public void setJsonData(List<Map<String, String>> jsonData) {
//        this.jsonData = jsonData;
//    }
//
//    public Byte getStatus() {
//        return status;
//    }
//
//    public void setStatus(Byte status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public LocalDateTime getUpdatedDate() {
//        return updatedDate;
//    }
//
//    public void setUpdatedDate(LocalDateTime updatedDate) {
//        this.updatedDate = updatedDate;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
	
	
	private int contentId;
	private String contentName;
    private List<Map<String, String>> jsonData;
    private Byte status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Category category;
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public List<Map<String, String>> getJsonData() {
		return jsonData;
	}
	public void setJsonData(List<Map<String, String>> list) {
		this.jsonData = list;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
    
    
}
