package com.cms.dto;

import java.util.List;

public class ResponseUserHistoryDTO {
	private List<UserHistoryDTO> userHistoryDTO ;

	public List<UserHistoryDTO> getUserHistoryDTO() {
		return userHistoryDTO;
	}

	public void setUserHistoryDTO(List<UserHistoryDTO> userHistoryDTO) {
		this.userHistoryDTO = userHistoryDTO;
	}	

}
