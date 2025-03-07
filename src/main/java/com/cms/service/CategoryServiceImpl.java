package com.cms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.dto.CategoryDTO;
import com.cms.dto.ResponseCategoryDTO;
import com.cms.model.Category;
import com.cms.repository.CategoryRepository;




@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;

    

    @Override
    public ResponseCategoryDTO getAllCategorys() {
        ResponseCategoryDTO responseDTO=new ResponseCategoryDTO();
        List<CategoryDTO> categoryDTOs=new ArrayList<>();
        List<Category> categorys=categoryRepository.findAll();
        for(Category c:categorys) {
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setCategoryId(c.getCategoryId());
            categoryDTO.setCategoryName(c.getCategoryName());
            categoryDTO.setPoints(c.getPoints());
            categoryDTO.setCategoryDescription(c.getCategoryDescription());
            categoryDTOs.add(categoryDTO);
            
            }
        responseDTO.setCategoryDTO(categoryDTOs);
        responseDTO.setTotalCategory(categoryRepository .count());
        return responseDTO;
    
        
    }
        
        
    @Override
    public CategoryDTO getCategoryById(int category_Id) {
        CategoryDTO categoryDTO=new CategoryDTO();
        
        Category category=categoryRepository.findById(category_Id).orElse(null);
        if(category!=null) {
            
                categoryDTO.setCategoryId(category.getCategoryId());
                categoryDTO.setCategoryName(category.getCategoryName());
                categoryDTO.setPoints(category.getPoints());
                categoryDTO.setCategoryDescription(category.getCategoryDescription());;
                
            }
            
        return categoryDTO;
    }

    @Override
    public Category create(CategoryDTO categoryDTO) {
    
            // TODO Auto-generated method stub
            Category category=new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setCategoryDescription(categoryDTO.getCategoryDescription());
            category.setPoints(categoryDTO.getPoints());
            return categoryRepository.save(category);
    }
    
    @Override
    public Category deleteCategorybyId(Integer categoryId) {
    Category deleteCategory=categoryRepository.findById(categoryId).orElse(null);
    categoryRepository.deleteById(categoryId);
        return deleteCategory;
    }

    @Override
    public Category updatecategory(Integer categoryId, CategoryDTO categoryDTO) {
        // TODO Auto-generated method stub
                  
                    Category category=categoryRepository.findById(categoryId).orElse(null);
                    if(category!=null) {
                    category.setCategoryName(categoryDTO.getCategoryName());
                    category.setCategoryDescription(categoryDTO.getCategoryDescription());
                    category.setPoints(categoryDTO.getPoints());
                    }
                    return categoryRepository.save(category);
        
    }

}
 
