package com.cms.service;

import com.cms.dto.CategoryDTO;
import com.cms.dto.ResponseCategoryDTO;
import com.cms.model.Category;

public interface CategoryService {

    

    ResponseCategoryDTO getAllCategorys();

    CategoryDTO getCategoryById(int category_Id);

    Category create(CategoryDTO categoryDTO);

    Category deleteCategorybyId(Integer categoryId);

    Category updatecategory(Integer categoryId, CategoryDTO categoryDTO);

    
}
