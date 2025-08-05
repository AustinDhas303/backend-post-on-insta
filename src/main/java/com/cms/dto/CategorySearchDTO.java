package com.cms.dto;

import com.cms.model.Category;

import lombok.Data;

@Data
public class CategorySearchDTO {

	  private Integer page;
	  private Integer size;
	  private Integer categorytId;
	  private String categoryName;
}
