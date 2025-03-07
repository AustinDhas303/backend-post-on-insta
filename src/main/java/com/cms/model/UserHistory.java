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

	public Long getUserHistoryId() {
		return userHistoryId;
	}

	public void setUserHistoryId(Long userHistoryId) {
		this.userHistoryId = userHistoryId;
	}

	public Integer getScores() {
		return scores;
	}

	public void setScores(Integer scores) {
		this.scores = scores;
	}

	public Integer getRewardsPoint() {
		return rewardsPoint;
	}

	public void setRewardsPoint(Integer rewardsPoint) {
		this.rewardsPoint = rewardsPoint;
	}

	public LocalDateTime getAttemptedDate() {
	return attemptedDate;
	}

	public void setAttemptedDate(LocalDateTime attemptedDate) {
	this.attemptedDate = attemptedDate;
	}


	public List<Map<String, String>> getJsonData() {
	return jsonData;
	}

	public void setJsonData(List<Map<String, String>> jsonData) {
	this.jsonData = jsonData;
	}

	public Content getContent() {
	return content;
	}

	public void setContent(Content content) {
	this.content = content;
	}

	public User getUser() {
	return user;
	}

	public void setUser(User user) {
	this.user = user;
	}
	 
	
	}
