package com.cms.dto;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCaption() {
        return caption;
    }

}
