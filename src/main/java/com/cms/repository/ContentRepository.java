package com.cms.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cms.model.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer>{

	@Query("SELECT c FROM Content c WHERE c.contentId = :contentId AND c.category.categoryId = :categoryId")

	Optional<Content> findByContentIdAndCategoryId(@Param
			("contentId") int contentId, @Param("categoryId") int categoryId);

//@Query("SELECT c FROM Content c WHERE c.category.categoryId = :categoryId")
//
//	List<Content> findAllByCategory_CategoryId(@Param("categoryId") int categoryId);
	
	@Query("SELECT c FROM Content c WHERE c.category.categoryId = :categoryId ORDER BY c.contentId DESC")

	List<Content> findAllByCategory_CategoryId(@Param("categoryId") int categoryId);

	Optional<Content> findByContentId(Integer contentId);
	
	@Query(value = "SELECT c.* FROM content AS c LEFT JOIN category AS ca ON ca.category_id = c.category_id WHERE DATE_FORMAT(updated_date, '%Y-%m-%d') = ?1 AND ca.category_name = ?2 ORDER BY content_id DESC LIMIT 1",nativeQuery = true)
	Content findContentByDate(Date currentDate, String categoryName);

}
