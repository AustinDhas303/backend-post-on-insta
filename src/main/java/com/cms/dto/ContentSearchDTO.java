package com.cms.dto;

import java.util.Date;

import com.cms.model.Category;

import lombok.Data;

@Data
public class ContentSearchDTO {

	  private Integer page;
	  private Integer size;
	  private Integer contentId;
	  private String contentName;
	  private Category category;
	  private Integer status;
	  
}
