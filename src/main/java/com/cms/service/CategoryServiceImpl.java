package com.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cms.dto.CategoryDTO;
import com.cms.dto.CategorySearchDTO;
import com.cms.model.Category;
import com.cms.model.User;
import com.cms.repository.CategoryRepository;
import com.cms.repository.UserRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Object> getAllCategorys(String categoryName) {
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		User user = userRepository.getEmail(email);
		Integer roleId = user.getRole().getRoleId();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Category> list = null;
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		System.out.println(categoryName);
		if(roleId == 1) {
			list = categoryRepository.getCategory(categoryName);
			
			for(Category c:list) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("categoryId", c.getCategoryId());
				map1.put("categoryName", c.getCategoryName());
				map1.put("description", c.getCategoryDescription());
				map1.put("points", c.getPoints());
				list2.add(map1);
			}
			map.put("list", list2);
		}
        
        return map;
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
    public Map<String, Object> create(CategoryDTO categoryDTO) {
    
            // TODO Auto-generated method stub
    	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = userDetails.getUsername();
		User user = userRepository.getEmail(email);
		Integer roleId = user.getRole().getRoleId();
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleId == 1) {
			String categoryName = categoryDTO.getCategoryName();
			if (categoryName == null) {
			    throw new IllegalArgumentException("categoryName is null!");
			}
			System.out.println("Category: "+categoryName);
			Category cat = categoryRepository.getCategoryName(categoryName);
			System.out.println("Category: "+cat);
			if(cat != null && cat.getCategoryName().equals(categoryName)) {
				map.put("error", "Category Name already exists");
				return map;
			}
            Category category=new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setCategoryDescription(categoryDTO.getCategoryDescription());
            category.setPoints(categoryDTO.getPoints());	
            categoryRepository.save(category);
            map.put("status", "success");
            map.put("message", "Category created successfully");
		}else {
			map.put("message", "Admin only create category");
			return map;
		}
        return map;
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
 
