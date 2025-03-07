package com.cms.dto;

import java.util.List;

public class ResponseContentDTO {

	private String message;
    private List<ContentDTO> contentList;
    private int totalCount;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ContentDTO> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentDTO> contentList) {
        this.contentList = contentList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
