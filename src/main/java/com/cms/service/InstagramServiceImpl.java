package com.cms.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InstagramServiceImpl implements InstagramService{

	private static final String GRAPH_API_URL = "https://graph.facebook.com/v21.0/";

    private static final String ACCESS_TOKEN = "EAAPZBBw3DyYoBO9yx0PPnv5UGd7lDB5zrmfZCLs1VYW10CZCaZBqHgepCQ84tVcZAM5aJxUDCujFVweIkJxOQXr07IHrsVrTKZBjLPT9Vfd2eP9n7C2xRSA2n9b3DvsSttmz4ZBcrRccPeUXUFlrzh1kbR906QTnNYZC1XWHSaf5ZBLEbCwlzo08Axs7D"; // Replace with actual access token

    private static final String PAGE_ID = "17841472038884020";
    
	@Override
	public String postImage(String imageUrl, String caption) throws Exception {
		// TODO Auto-generated method stub
		String creationId = createMediaContainer(imageUrl, caption);
		return publishMedia(creationId);
	}
	 private String createMediaContainer(String imageUrl, String caption) throws Exception {
	        String url = GRAPH_API_URL + PAGE_ID + "/media";
	        Map<String, String> params = new HashMap<>();
	        params.put("image_url", imageUrl);
	        params.put("caption", caption);
	        params.put("access_token", ACCESS_TOKEN);
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Map> response = restTemplate.postForEntity(url, params, Map.class);
	        if (response.getBody() != null && response.getBody().containsKey("id")) {
	            return (String) response.getBody().get("id");
	        } else {
	            throw new Exception("Error creating media container: " + response.getBody());
	        }
	    }

	    private String publishMedia(String creationId) throws Exception {
	        String url = GRAPH_API_URL + PAGE_ID + "/media_publish";
	        Map<String, String> params = new HashMap<>();
	        params.put("creation_id", creationId);
	        params.put("access_token", ACCESS_TOKEN);
	        RestTemplate restTemplate = new RestTemplate();
	        ResponseEntity<Map> response = restTemplate.postForEntity(url, params, Map.class);

	        if (response.getBody() != null && response.getBody().containsKey("id")) {
	            return "Image posted successfully" ;
	        } else {
	            throw new Exception(" Post to failed ");
	        }
	    }

}
