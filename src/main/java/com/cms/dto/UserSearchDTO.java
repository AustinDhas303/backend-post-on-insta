package com.cms.dto;

import lombok.Data;

@Data
public class UserSearchDTO {
	private int page;
	private int size;
	private String userName;
	private int status;
}
