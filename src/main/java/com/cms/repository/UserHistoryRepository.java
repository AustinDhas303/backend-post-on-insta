package com.cms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cms.model.UserHistory;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{
	@Query("SELECT uh FROM UserHistory uh WHERE uh.user.userId = :userId AND uh.content.contentId = :contentId") 
	Optional<UserHistory> findByUserIdAndContentId(@Param("userId") Long userId, @Param("contentId") Integer integer);  

//	Optional<UserHistory> findByUserUserIdAndContentContentId(Long userId, Integer contentId);
//	@Query("select count(uh) from UserHistory uh where uh.user.userId=:userId and uh.content.contentId=:contentId")
//	int userQuestionCount(@Param("userId") long userId  ,@Param("contentId)") int contentId);
	
	@Query("SELECT uh FROM UserHistory uh WHERE uh.user.userId = ?1 AND uh.content.contentId = ?2") 
	UserHistory findByUserUserIdAndContentContentId(Long userId, Integer contentId);

	List<UserHistory> findByUser_UserId(Long userId);

	@Query("Select uh from UserHistory uh where (:userName is null or lower(uh.user.firstName) like lower(concat(:userName, '%')) or"
			+ " lower(uh.user.lastName) like lower(concat(:userName ,'%'))) order by uh.attemptedDate desc")
	List<UserHistory> getAllUserHistory(Pageable pageable, String userName); 
	

}
