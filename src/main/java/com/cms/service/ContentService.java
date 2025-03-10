package com.cms.service;

import java.util.List;

import com.cms.dto.ContentDTO;
import com.cms.dto.ResponseContentDTO;
import com.cms.model.Content;

public interface ContentService {

//	void deleteContentById(int id);

//	Content saveContent(Content content);

	Content saveContent(Content content);

	ContentDTO getContentById(Long userId, String categoryName);

	Content updateContent(Integer contentId, ContentDTO contentDTO);

	Content deleteContentById(Integer contentId);

	ResponseContentDTO getAllContents();

	ContentDTO findByContentIdAndCategoryId(int contentId, int categoryId);

	List<ContentDTO> getContentsByCategoryId(int categoryId);

	boolean isQuizValid(Integer contentId);

}
