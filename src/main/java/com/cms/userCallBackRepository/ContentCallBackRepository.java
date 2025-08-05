package com.cms.userCallBackRepository;

import java.io.Serializable;
import java.util.List;

import com.cms.model.Category;
import com.cms.model.Content;

public interface ContentCallBackRepository{

	List<Content> getContents(int page, int size, Integer contentId, String contentName, Category category, Integer status);

}
