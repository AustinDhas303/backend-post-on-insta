package com.cms.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.cms.model.Category;

import lombok.Data;
@Data
public class ContentDTO {	
	
	private int contentId;
	private String contentName;
    private List<Map<String, String>> jsonData;
    private Byte status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Category category;
    
}
