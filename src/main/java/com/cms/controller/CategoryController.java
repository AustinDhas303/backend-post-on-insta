package com.cms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cms.service.CategoryService;
import com.cms.dto.CategoryDTO;
import com.cms.dto.CategorySearchDTO;
import com.cms.model.Category;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
        
    @PostMapping("/createcategory")
    public Map<String, Object> create(@RequestBody CategoryDTO  categoryDTO ){
        return categoryService.create(categoryDTO);
    }
    
    @GetMapping("fetchcategory")
    public Map<String, Object> getAllCategory(@RequestParam(required = false) String categoryName){
        return categoryService.getAllCategorys(categoryName);
    }
    
    @GetMapping("/fetchcategory/{category_Id}")
    public ResponseEntity<CategoryDTO>getCategoryById(@PathVariable("category_Id") int category_Id){
        CategoryDTO categoryDTO=categoryService.getCategoryById(category_Id);
        
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
    @DeleteMapping("deletecategory/{categoryId}")
    public Map<String, String> deleteCategorybyId(@PathVariable Integer categoryId){
        return categoryService.deleteCategorybyId(categoryId);
    }
    @PutMapping("/updatecategory")
    public Map<String, String> updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.updatecategory(categoryDTO);
    }


}

