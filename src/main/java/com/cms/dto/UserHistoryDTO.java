package com.cms.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
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

}
