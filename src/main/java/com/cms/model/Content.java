package com.cms.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int contentId;
    
    @Column(name="content_name")
    private String contentName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content", columnDefinition = "json")
    private List<Map<String, String>> jsonData;

    @Column(nullable = false)
    private Byte status;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Content(Integer contentId2) {
		// TODO Auto-generated constructor stub
	}

	@PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
    
    

    public Content(int contentId, String contentName, List<Map<String, String>> jsonData, Byte status,
			LocalDateTime createdDate, LocalDateTime updatedDate, Category category) {
		super();
		this.contentId = contentId;
		this.contentName = contentName;
		this.jsonData = jsonData;
		this.status = status;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.category = category;
	}

	public Content() {
		super();
	}

	// Getters and Setters
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public List<Map<String, String>> getJsonData() {
        return jsonData;
    }

    public void setJsonData(List<Map<String, String>> jsonData) {
        this.jsonData = jsonData;
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
