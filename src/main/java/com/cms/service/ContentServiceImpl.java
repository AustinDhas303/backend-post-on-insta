package com.cms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cms.dto.ContentDTO;
import com.cms.dto.ContentSearchDTO;
import com.cms.model.Content;
import com.cms.model.User;
import com.cms.repository.ContentRepository;
import com.cms.repository.UserHistoryRepository;
import com.cms.repository.UserRepository;

@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private UserHistoryRepository userHistoryRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@Override
	public Map<String, Object> saveContent(ContentDTO contentDTO) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		User user = userRepository.getEmail(email);
		System.out.println("role id: "+user.getRole().getRoleId());
		Integer roleId = user.getRole().getRoleId();
		System.out.println("role id: "+roleId);
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleId != null && roleId == 1 ) {
			Content content = new Content();
			content.setContentId(contentDTO.getContentId());
			content.setContentName(contentDTO.getContentName());
			content.setJsonData(contentDTO.getJsonData());
			content.setStatus(contentDTO.getStatus());
			content.setCreatedDate(contentDTO.getCreatedDate());
			content.setUpdatedDate(contentDTO.getUpdatedDate());
			content.setCategory(contentDTO.getCategory());
			contentRepository.save(content);
			map.put("status", "success");
			map.put("meaasage", "Content created successfully");
		}else {
			map.put("message", "Admin only allowed to create content.");
			return map;
		}
		return map;
	}

	@Override
	public ContentDTO getContentById(Long userId, String categoryName) {
		// TODO Auto-generated method stub
		Content content = contentRepository.findContentByDate(new Date(System.currentTimeMillis()), categoryName);
		content = content != null
				&& userHistoryRepository.findByUserUserIdAndContentContentId(userId, content.getContentId()) != null
						? null
						: content;
		ContentDTO contentDTO = new ContentDTO();

		// Fetch the Content entity by ID

		if (content != null) {
			// Set the ContentDTO properties if the Content exists
			contentDTO.setContentId(content.getContentId());
			contentDTO.setContentName(content.getContentName());
			contentDTO.setJsonData(content.getJsonData());
			contentDTO.setStatus(content.getStatus());
			contentDTO.setCategory(content.getCategory());
		}

		return contentDTO;
	}

	@Override
	public Map<String, Object> updateContent(Integer contentId, ContentDTO contentDTO) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		User user = userRepository.getEmail(email);
		Integer roleId = user.getRole().getRoleId();
		Content content = contentRepository.findById(contentId).orElse(null);
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleId != null && roleId == 1) {
		       if (content != null) {
		    	   content.setContentName(contentDTO.getContentName());
		           content.setJsonData(contentDTO.getJsonData());
		           content.setStatus(contentDTO.getStatus());
		           content.setCategory(contentDTO.getCategory());
		           content.setUpdatedDate(contentDTO.getUpdatedDate());
		           contentRepository.save(content);
		           String contentName = contentDTO.getContentName();
		           String c = String.format("Content {} updated successfully", contentName);
		           map.put("message", c);
		       }
		}else {
			map.put("message", "Only admin can update the content");
			return map;
		}

	       return map; 
	}

	@Override
	public Map<String, Object> deleteContentById(Integer contentId) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		User user = userRepository.getEmail(email);
		Integer roleId = user.getRole().getRoleId();
		Content content = contentRepository.findById(contentId).orElse(null);
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleId != null && roleId == 1) {
		       if (content != null) {
		           contentRepository.delete(content);
		           map.put("meassage", "Content deleted successfully");
		           return map;
		       }
		}else {
			map.put("message", "Only admin allowed to delete a content");
			return map;
		}


	       return map; 
	}
	
//	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Map<String, Object> getAllContents(ContentSearchDTO contentSearchDTO) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		User user = userRepository.getEmail(email);
		Integer roleId = user.getRole().getRoleId();
		List<Content> list = null;
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		int page = contentSearchDTO.getPage();
		int size = contentSearchDTO.getSize();
		Pageable pageable = PageRequest.of(page, size);
		String contentName = contentSearchDTO.getContentName();
		String categoryName = contentSearchDTO.getCategoryName();
		
		if(roleId == 1) {
			list = contentRepository.getContents(pageable, contentName, categoryName);
			for(Content c:list) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("contentId", c.getContentId());
				map2.put("contentName", c.getContentName());
				map2.put("jsonData", c.getJsonData());
				map2.put("createdDate", c.getCreatedDate());
				map2.put("categoryName", c.getCategory().getCategoryName());
				map2.put("categoryDescription", c.getCategory().getCategoryDescription());
				map2.put("points", c.getCategory().getPoints());
				list2.add(map2);
			}
		}
		map.put("list", list2);
		map.put("page", contentSearchDTO.getPage());
		map.put("size", contentSearchDTO.getSize());
		return map;
	}

	@Override
	public ContentDTO findByContentIdAndCategoryId(int contentId, int categoryId) {

	    ContentDTO contentDTO = new ContentDTO();

	    Content content = contentRepository.findByContentIdAndCategoryId(contentId, categoryId).orElse(null);
	    if (content != null) {
	        contentDTO.setContentId(content.getContentId());
	        contentDTO.setContentName(content.getContentName());
	        contentDTO.setJsonData(content.getJsonData());
	        contentDTO.setStatus(content.getStatus());
	        contentDTO.setCategory(content.getCategory());
	    }
	    return contentDTO;
	}
	

	@Override
	public List<ContentDTO> getContentsByCategoryId(int categoryId) {
	    List<Content> contents = contentRepository.findAllByCategory_CategoryId(categoryId);
	    return contents.stream().map(content -> {
	        ContentDTO dto = new ContentDTO();
	        dto.setContentId(content.getContentId());
	        dto.setContentName(content.getContentName());
	        dto.setJsonData(content.getJsonData());
	        dto.setStatus(content.getStatus());
	        dto.setCategory(content.getCategory());
	        return dto;
	    }).collect(Collectors.toList());
	}

	public boolean isQuizValid(Integer contentId) {
        Optional<Content> content = contentRepository.findByContentId(contentId);
        if (content.isPresent()) {
            LocalDateTime createdDate = content.get().getCreatedDate();
            return createdDate.equals(LocalDate.now());
        }
        return false;
    }

}
