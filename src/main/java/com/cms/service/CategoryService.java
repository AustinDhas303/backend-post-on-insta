package com.cms.service;

import java.util.Map;

import com.cms.dto.CategoryDTO;
import com.cms.dto.CategorySearchDTO;
import com.cms.model.Category;

public interface CategoryService {

    Map<String, Object> getAllCategorys(String categoryName);

    CategoryDTO getCategoryById(int category_Id);

    Map<String, Object> create(CategoryDTO categoryDTO);

    Map<String, String> deleteCategorybyId(Integer categoryId);

    Map<String, String> updatecategory(CategoryDTO categoryDTO);

    
}
