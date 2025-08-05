package com.cms.service;

import java.util.List;
import java.util.Map;

import com.cms.dto.ContentDTO;
import com.cms.dto.ContentSearchDTO;

public interface ContentService {

	Map<String, Object> saveContent(ContentDTO contentDTO);

	ContentDTO getContentById(Long userId, String categoryName);

	Map<String, Object> updateContent(Integer contentId, ContentDTO contentDTO);

	Map<String, Object> deleteContentById(Integer contentId);

	Map<String, Object> getAllContents(ContentSearchDTO contentSearchDTO);

	ContentDTO findByContentIdAndCategoryId(int contentId, int categoryId);

	List<ContentDTO> getContentsByCategoryId(int categoryId);

	boolean isQuizValid(Integer contentId);

}
