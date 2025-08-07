package com.cms.dto;

import lombok.Data;

@Data
public class ContentSearchDTO {

	  private int page;
	  private int size;
	  private String contentName;
	  private String categoryName;
	  
}
