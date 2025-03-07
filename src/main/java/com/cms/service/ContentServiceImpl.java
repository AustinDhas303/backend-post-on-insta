package com.cms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.dto.ContentDTO;
import com.cms.dto.ResponseContentDTO;
import com.cms.model.Content;
import com.cms.model.UserHistory;
import com.cms.repository.ContentRepository;
import com.cms.repository.UserHistoryRepository;
@Service
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	private ContentRepository contentRepository;
	
	@Autowired
	private UserHistoryRepository userHistoryRepository;

//	@Override
//	public void deleteContentById(int id) {
//		// TODO Auto-generated method stub
//	        if (contentRepository.existsById(id)) {
//	            contentRepository.deleteById(id);
//	        } else {
//	            throw new RuntimeException("Content with id " + id + " does not exist");
//	        }
//	}

	@Override
	public Content saveContent(Content content) {
		// TODO Auto-generated method stub
		return contentRepository.save(content);
	}

	@Override
	public ContentDTO getContentById(Long userId) {
		// TODO Auto-generated method stub
		Content content = contentRepository.findContentByDate(new Date(System.currentTimeMillis()));
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
	public Content updateContent(Integer contentId, ContentDTO contentDTO) {
		// TODO Auto-generated method stub
		Content content = contentRepository.findById(contentId).orElse(null);

	       if (content != null) {
	           // Update the Content entity with new data from contentDTO
	    	   content.setContentName(contentDTO.getContentName());
	           content.setJsonData(contentDTO.getJsonData());
	           content.setStatus(contentDTO.getStatus());
	           content.setCategory(contentDTO.getCategory());

	           // Save the updated Content and return it
	           return contentRepository.save(content);
	       }

	       return null; 
	}

	@Override
	public Content deleteContentById(Integer contentId) {
		// TODO Auto-generated method stub
		Content content = contentRepository.findById(contentId).orElse(null);
		
	       if (content != null) {
	           // Delete the Content entity if it exists
	           contentRepository.delete(content);
	           return content; // Return the deleted Content
	       }

	       return null; 
	}
	
//	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public ResponseContentDTO getAllContents() {
		// TODO Auto-generated method stub
		ResponseContentDTO responseDTO = new ResponseContentDTO();
	       List<ContentDTO> contentDTOs = new ArrayList<>();

	       // Fetch all contents from the repository
	       List<Content> contents = contentRepository.findAll();

	       // Convert Content entities to ContentDTOs
	       for (Content content : contents) {
	           ContentDTO contentDTO = new ContentDTO();
	           contentDTO.setContentId(content.getContentId());
	           contentDTO.setContentName(content.getContentName());
	           contentDTO.setJsonData(content.getJsonData());
//	           contentDTO.setStatus(content.getStatus()); 
//	           contentDTO.setCategory(content.getCategory());
	           contentDTOs.add(contentDTO);
	       }

	       // Set the list of contentDTOs and the total count
	       responseDTO.setMessage("Contents fetched successfully");
	       responseDTO.setContentList(contentDTOs);
	     
	       return responseDTO;

	}

	@Override

	public ContentDTO findByContentIdAndCategoryId(int contentId, int categoryId) {

	    // Initialize ContentDTO to store the result

	    ContentDTO contentDTO = new ContentDTO();



	    // Fetch the Content entity based on contentId and categoryId

	    Content content = contentRepository.findByContentIdAndCategoryId(contentId, categoryId).orElse(null);



	    if (content != null) {

	        // Map the Content entity properties to the ContentDTO

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



	    // Map to DTOs if needed

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
            return createdDate.equals(LocalDate.now()); // Check if created_date is today
        }
        return false; // Quiz not found or expired
    }

}
