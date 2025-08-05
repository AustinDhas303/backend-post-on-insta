package com.cms.dto;

import lombok.Data;

@Data
public class InstagramDTO {
	
	private String imageUrl;

    private String accessToken;

    private String caption;
    public InstagramDTO() {}
    
    public InstagramDTO(String imageUrl, String accessToken, String caption) {
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.caption = caption;
    }

}
