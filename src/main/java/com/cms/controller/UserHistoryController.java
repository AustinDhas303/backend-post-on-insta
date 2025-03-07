package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.dto.ResponseUserHistoryDTO;
import com.cms.dto.UserHistoryDTO;
import com.cms.model.UserHistory;
import com.cms.service.UserHistoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserHistoryController {

	 @Autowired
	   private UserHistoryService userHistoryService;

	 @PostMapping("/createuserhistory")
	 public ResponseEntity<Map<String, Object>> createUserHistory(@RequestBody UserHistoryDTO userHistoryDTO) {
	     Map<String, Object> response = new HashMap<>();
	     
	     try {
	         UserHistory userHistory = userHistoryService.createUserHistory(userHistoryDTO);
	         response.put("success", true);
	         response.put("message", "Quiz attempt saved successfully!");
	         response.put("userHistory", userHistory);
	     } catch (RuntimeException e) {
	         response.put("success", false);
	         response.put("message", "User already attempted the Quiz");
	         response.put("userHistory", null);  // Ensuring the response structure remains consistent
	     }

	     return ResponseEntity.ok(response);
	 }
	   
	   @GetMapping("/fetchalluserhistory")
	   public ResponseEntity<ResponseUserHistoryDTO> getAllUserHistory(){
		   ResponseUserHistoryDTO responseUserHistoryDTO =userHistoryService.getAllUserHistory();
		   return new ResponseEntity<>(responseUserHistoryDTO,HttpStatus.OK);
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
}
