package com.cms.model;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
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

    private Byte status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

//    public Content(Integer contentId2) {
		// TODO Auto-generated constructor stub
//	}

	@PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
    
    

//    public Content(int contentId, String contentName, List<Map<String, String>> jsonData, Byte status,
//			LocalDateTime createdDate, LocalDateTime updatedDate, Category category) {
//		super();
//		this.contentId = contentId;
//		this.contentName = contentName;
//		this.jsonData = jsonData;
//		this.status = status;
//		this.createdDate = createdDate;
//		this.updatedDate = updatedDate;
//		this.category = category;
//	}
//
//	public Content() {
//		super();
//	}   
}
