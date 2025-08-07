package com.cms.service;

import java.util.List;
import java.util.Map;

import com.cms.dto.UserHistoryDTO;
import com.cms.dto.UserHistoryResponseDTO;
import com.cms.model.UserHistory;

public interface UserHistoryService {

	Map<String, String> createUserHistory(UserHistoryDTO userHistoryDTO);

	UserHistoryResponseDTO getAllUserHistory(int page, int size, String userName);

	UserHistoryDTO fetchUserHistoryById(Long userHistoryId);

	UserHistory createUserVideo(UserHistoryDTO userHistoryDTO);

	List<UserHistoryDTO> fetchAllUserHistoryByUserId(Long userId);

//	UserHistoryDTO fetchUserHistoryByusercontentId(Long userId, Long contentId);

}
