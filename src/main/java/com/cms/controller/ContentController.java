package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.ContentDTO;
import com.cms.dto.ContentSearchDTO;
import com.cms.service.ContentService;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
    private ContentService contentService;

	@PostMapping("/createcontent")
    public Map<String, Object> createContent(@RequestBody ContentDTO content) {
        return contentService.saveContent(content);
    }

	@GetMapping("/fetchcontents")
	public Map<String, Object> getAllContent(@RequestBody ContentSearchDTO contentSearchDTO) {
        return contentService.getAllContents(contentSearchDTO);
    }

    @PutMapping("/updatecontent/{contentId}")
    public Map<String, Object> updateContent(
    		@PathVariable Integer contentId,
            @RequestBody ContentDTO contentDTO) {
        return contentService.updateContent(contentId, contentDTO);
    }

    @DeleteMapping("/deletecontent/{contentId}")
    public Map<String, Object> deleteContentById(@PathVariable Integer contentId) {
        return contentService.deleteContentById(contentId);
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
	
}
