package com.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.service.InstagramService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthoruty('Admin')")
public class InstagramController {
	private final InstagramService instagramService;

    public InstagramController(InstagramService instagramService) {
        this.instagramService = instagramService;
    }

    @PostMapping("/postimage")
    public ResponseEntity<String> postImage(@RequestParam String imageUrl, @RequestParam String caption) {
        try {
            String result = instagramService.postImage(imageUrl, caption);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

}
