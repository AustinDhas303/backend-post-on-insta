package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.UserHistoryResponseDTO;
import com.cms.dto.UserHistoryDTO;
import com.cms.service.UserHistoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthoruty('Admin')")
public class FetchallUserHistoryController {

	 @Autowired
	   private UserHistoryService userHistoryService;
	 
	 @GetMapping("/fetchalluserhistory")
	   public ResponseEntity<UserHistoryResponseDTO> getAllUserHistory(){
		   UserHistoryResponseDTO responseUserHistoryDTO =userHistoryService.getAllUserHistory();
		   return new ResponseEntity<>(responseUserHistoryDTO,HttpStatus.OK);
	   }
}
