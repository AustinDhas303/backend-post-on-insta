package com.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cms.service.CategoryService;
import com.cms.dto.CategoryDTO;
import com.cms.dto.ResponseCategoryDTO;
import com.cms.model.Category;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
        
    @PostMapping("/createcategory")
    public ResponseEntity<String>create(@RequestBody CategoryDTO  categoryDTO ){
        Category category=categoryService.create(categoryDTO);
        return new ResponseEntity<>("Category Saved Sucessfully",HttpStatus.CREATED);
    }
    
    @GetMapping("fetchcategory")
    public ResponseEntity<ResponseCategoryDTO>getAllCategory(){
        ResponseCategoryDTO responseDTO=categoryService.getAllCategorys();
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
    
    @GetMapping("/fetchcategory/{category_Id}")
    public ResponseEntity<CategoryDTO>getCategoryById(@PathVariable("category_Id") int category_Id){
        CategoryDTO categoryDTO=categoryService.getCategoryById(category_Id);
        
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
    @DeleteMapping("deletecategory/{categoryId}")
    public ResponseEntity<String>deleteCategorybyId(@PathVariable Integer categoryId){
        Category deleteCategory=categoryService.deleteCategorybyId(categoryId);
        return new ResponseEntity<>("category deleted sucessfully",HttpStatus.OK);
    }
    @PutMapping("/updatecategory/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer categoryId,@RequestBody CategoryDTO categoryDTO){
        Category updatedCategory=categoryService.updatecategory(categoryId,categoryDTO);
        return new ResponseEntity<>("category updated Successfully",HttpStatus.OK );
    }


}

