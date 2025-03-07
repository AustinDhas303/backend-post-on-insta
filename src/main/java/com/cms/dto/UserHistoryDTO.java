package com.cms.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class UserHistoryDTO {
	
	   private Long userHistoryId;
	   private String contentName;
	 
	   private LocalDateTime attemptedDate;
	   private Integer scores;
	   private Integer rewardsPoint;
	   private Integer contentId;
	   private String userName;
	   private Long userId;
	   private List<Map<String, String>> jsonData;
	   
	
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserHistoryId() {
		return userHistoryId;
	}
	public void setUserHistoryId(Long userHistoryId) {
		this.userHistoryId = userHistoryId;
	}
	public LocalDateTime getAttemptedDate() {
		return attemptedDate;
	}
	public void setAttemptedDate(LocalDateTime attemptedDate) {
		this.attemptedDate = attemptedDate;
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
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userid) {
		this.userId = userid;
	}
	public List<Map<String, String>> getJsonData() {
		return jsonData;
	}
	public void setJsonData(List<Map<String, String>> jsonData) {
		this.jsonData = jsonData;
	}	   
	   

}
