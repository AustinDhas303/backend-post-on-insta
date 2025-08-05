package com.cms.service;

import java.util.Map;

import com.cms.dto.CategoryDTO;
import com.cms.dto.CategorySearchDTO;
import com.cms.model.Category;

public interface CategoryService {

    Map<String, Object> getAllCategorys(String categoryName);

    CategoryDTO getCategoryById(int category_Id);

    Map<String, Object> create(CategoryDTO categoryDTO);

    Category deleteCategorybyId(Integer categoryId);

    Category updatecategory(Integer categoryId, CategoryDTO categoryDTO);

    
}
