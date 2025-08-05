package com.cms.repository;

import com.cms.model.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("Select c from Category c where (:categoryName IS NULL or LOWER(c.categoryName) like lower(concat(:categoryName, '%')) )")
	List<Category> getCategory(@Param("categoryName") String categoryName);

	@Query("Select c from Category c where c.categoryName =:categoryName")
	Category getCategoryName(@Param("categoryName") String categoryName);

}