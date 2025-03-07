package com.cms.service;

import java.util.List;

import com.cms.dto.ResponseUserHistoryDTO;
import com.cms.dto.UserHistoryDTO;
import com.cms.model.UserHistory;

public interface UserHistoryService {

	UserHistory createUserHistory(UserHistoryDTO userHistoryDTO);

	ResponseUserHistoryDTO getAllUserHistory();

	UserHistoryDTO fetchUserHistoryById(Long userHistoryId);

	UserHistory createUserVideo(UserHistoryDTO userHistoryDTO);

	List<UserHistoryDTO> fetchAllUserHistoryByUserId(Long userId);

//	UserHistoryDTO fetchUserHistoryByusercontentId(Long userId, Long contentId);

}
