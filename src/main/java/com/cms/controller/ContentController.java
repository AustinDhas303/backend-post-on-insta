package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.ContentDTO;
import com.cms.dto.ResponseContentDTO;
import com.cms.model.Content;
import com.cms.service.ContentService;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ContentController {
	
	@Autowired
    private ContentService contentService;

//	@PostMapping("/createcontent")
//	public Content createContent(@RequestBody Content content) {
//	    content.setCreatedDate(java.time.LocalDateTime.now());
//	    content.setUpdatedDate(java.time.LocalDateTime.now());
//	    System.out.println(content.toString());
//	    return contentService.saveContent(content);
//	}


	@PostMapping("/createcontent")
    public ResponseEntity<?> createContent(@RequestBody Content content) {
        try {
            Content savedContent = contentService.saveContent(content);
            return ResponseEntity.ok(savedContent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error saving content: " + e.getMessage());
        }
    }

	@GetMapping("/fetchcontents")
	public ResponseEntity<ResponseContentDTO> getAllContent() {
        ResponseContentDTO responseDTO = contentService.getAllContents();
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
	
	@GetMapping("/user/{userid}/newquiz")
    public ResponseEntity<ContentDTO> getContentById(@PathVariable("userid") Long userId) {
        ContentDTO contentDTO = contentService.getContentById(userId);
        return new ResponseEntity<>(contentDTO, HttpStatus.OK);
    }

    @PutMapping("/updatecontent/{contentId}")
    public ResponseEntity<String> updateContent(
            @PathVariable Integer contentId, 
            @RequestBody ContentDTO contentDTO) {
        Content updatedContent = contentService.updateContent(contentId, contentDTO);
        return new ResponseEntity<>("Content updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deletecontent/{contentId}")
    public ResponseEntity<String> deleteContentById(@PathVariable Integer contentId) {
        Content deletedContent = contentService.deleteContentById(contentId);
        return new ResponseEntity<>("Content deleted successfully", HttpStatus.OK);

    }
    
    @GetMapping("/fetchcontentcategory/{contentId}/{categoryId}")
    public ResponseEntity<ContentDTO> findByContentIdAndCategoryId(@PathVariable("contentId") int contentId,@PathVariable("categoryId") int categoryId ){
        ContentDTO contentDTO = contentService.findByContentIdAndCategoryId(contentId, categoryId);
        return new ResponseEntity<>(contentDTO, HttpStatus.OK);
    }

	@GetMapping("/contentcategory/{categoryId}")
	public ResponseEntity<List<ContentDTO>> getContentsByCategoryId(@PathVariable int categoryId) {
	    List<ContentDTO> contents = contentService.getContentsByCategoryId(categoryId);
	    if (contents.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    }
	    return ResponseEntity.ok(contents);
	}
	
	@PostMapping("/verify-content")
    public Map<String, Object> verifyContent(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Integer contentId = Integer.parseInt(request.get("contentId")); // Convert to Integer
            
            if (contentService.isQuizValid(contentId)) {
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "Quiz is either not available or expired.");
            }
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "Invalid content ID format.");
        }

        return response;
    }	
//	@DeleteMapping("/deleteContent/{id}")
//    public ResponseEntity<String> deleteContent(@PathVariable int id) {
//        try {
//            contentService.deleteContentById(id);
//            return new ResponseEntity<>("Content deleted successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to delete content: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
	
}
