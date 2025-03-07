package com.cms.dto;

import java.util.List;

public class ResponseCategoryDTO {
    private List<CategoryDTO> CategoryDTO;
    private Long totalCategory;
    public List<CategoryDTO> getCategoryDTO() {
        return CategoryDTO;
    }
    public void setCategoryDTO(List<CategoryDTO> categoryDTO) {
        CategoryDTO = categoryDTO;
    }
    public Long getTotalCategory() {
        return totalCategory;
    }
    public void setTotalCategory(long totalCategory) {
        this.totalCategory = totalCategory;
    }
    
    
    

}
