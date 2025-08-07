package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import com.cms.dto.ContentDTO;
import com.cms.dto.UserHistoryResponseDTO;
import com.cms.dto.UserHistoryDTO;
import com.cms.model.UserHistory;
import com.cms.service.ContentService;
import com.cms.service.UserHistoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/userHistory")
public class UserHistoryController {

	 @Autowired
	   private UserHistoryService userHistoryService;
	 
	 @Autowired
	 private ContentService contentService;

	 @PostMapping("/createuserhistory")
	 public Map<String, String> createUserHistory(@RequestBody UserHistoryDTO userHistoryDTO) {
	     return userHistoryService.createUserHistory(userHistoryDTO);
	 }
	 
	 @GetMapping("/fetchalluserhistory")
	 public ResponseEntity<UserHistoryResponseDTO> getAllUserHistory(@RequestParam int page, @RequestParam int size, @RequestParam String userName){
		 UserHistoryResponseDTO responseUserHistoryDTO =userHistoryService.getAllUserHistory(page, size, userName);
		 return new ResponseEntity<>(responseUserHistoryDTO,HttpStatus.OK);
	 }
	   
	 @GetMapping("/user/{userid}/newquiz")
	    public ResponseEntity<ContentDTO> getContentById(@PathVariable("userid") Long userId, @RequestParam String categoryName) {
	        ContentDTO contentDTO = contentService.getContentById(userId, categoryName);
	        return new ResponseEntity<>(contentDTO, HttpStatus.OK);
	    }

	    @GetMapping("/fetchuserhistory/{userHistoryId}")
	    public ResponseEntity<UserHistoryDTO> fetchUserHistoryById(@PathVariable Long userHistoryId) {
	        UserHistoryDTO userHistoryDTO = userHistoryService.fetchUserHistoryById(userHistoryId);
	        return new ResponseEntity<>(userHistoryDTO, HttpStatus.OK);

	    }
	    
//	    @GetMapping("/fetchbyuseridcontentid")
//	    public ResponseEntity<?> fetchUserHistoryByusercontentId(@RequestParam Long userId,@RequestParam Long contentId) {
//	        UserHistoryDTO userHistoryDTO = userHistoryService.fetchUserHistoryByusercontentId(userId,contentId);
//	        return new ResponseEntity<>(userHistoryDTO, HttpStatus.OK);
//
//	    }
	    
	    @GetMapping("fetchbyuserId/{userId}")
	    public ResponseEntity<List<UserHistoryDTO>> getUserHistory(@PathVariable Long userId) {
	        List<UserHistoryDTO> userHistoryList = userHistoryService.fetchAllUserHistoryByUserId(userId);
	        return ResponseEntity.ok(userHistoryList);
	    }
	    
	    @PostMapping("/createuservideo")
	    public ResponseEntity<?> saveUserVideoHistory(@RequestBody UserHistoryDTO userHistoryDTO) {
	        try {
	            UserHistory savedHistory = userHistoryService.createUserVideo(userHistoryDTO);
	            return ResponseEntity.ok().body(Map.of("message", "Video watch history saved successfully", "data", savedHistory));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Failed to save video history"));
	        }
	    }
	    
	    @GetMapping("/stream")
	    public ResponseEntity<InputStreamResource> streamVideo(@RequestParam String videoPath) throws FileNotFoundException{
	    System.err.println("Eneter Video Get Method ------------------>"+videoPath);
	        File videoFile = new File(videoPath);
	        InputStreamResource resource = new InputStreamResource(new FileInputStream(videoFile));

	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=sample.mp4");
	        headers.setContentType(org.springframework.http.MediaType.valueOf("video/mp4"));

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(videoFile.length())
	                .body(resource);
	    }
		
		@GetMapping("/image")
		public ResponseEntity<InputStreamResource> fetchImage(@RequestParam String imagePath) throws FileNotFoundException {
		    System.err.println("Fetching Image ------------------> " + imagePath);

		    File imageFile = new File(imagePath);
		    if (!imageFile.exists()) {
		        throw new FileNotFoundException("Image not found at path: " + imagePath);
		    }

		    InputStreamResource resource = new InputStreamResource(new FileInputStream(imageFile));

		    HttpHeaders headers = new HttpHeaders();
		    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + imageFile.getName());
		    headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG); // Change to IMAGE_PNG if needed

		    return ResponseEntity.ok()
		            .headers(headers)
		            .contentLength(imageFile.length())
		            .body(resource);
		}
}
