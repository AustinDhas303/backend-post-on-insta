package com.cms.model;

	import java.time.LocalDateTime;
	import java.util.List;
	import java.util.Map;

	import org.hibernate.annotations.JdbcTypeCode;
	import org.hibernate.type.SqlTypes;

	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.JoinColumn;
	import jakarta.persistence.ManyToOne;
	import jakarta.persistence.PrePersist;
	import jakarta.persistence.PreUpdate;
	import jakarta.persistence.Table;
import lombok.Data;

	@Data
	@Entity
	@Table(name="usersHistory")
	public class UserHistory {
	      @Id
	      @GeneratedValue(strategy=GenerationType.AUTO)
	      @Column(name = "user_history_id")

	 private Long userHistoryId;
	      @Column(name="attempted_date")
	 private LocalDateTime  attemptedDate;
	      @Column(name="scores",nullable=false)
	 private Integer scores;
	      @Column(name="rewardsPoint", nullable=false)
	 private Integer rewardsPoint;
	 @JdbcTypeCode(SqlTypes.JSON)
	   @Column(name = "responseContent", columnDefinition = "json")
	   private List<Map<String, String>> jsonData;
	 @ManyToOne
	 @JoinColumn(name="content_Id")
	 private Content content;
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id", nullable = false)
	 private User user;

	 
	 @PrePersist
	protected void onCreate() {
	 this.attemptedDate = LocalDateTime.now();
	}

	 @PreUpdate
	   protected void onUpdate() {
	       this.attemptedDate = LocalDateTime.now();
	   }
	
	}
