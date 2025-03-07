package com.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cms.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmailId(String emailId);
	Optional<User> findByPassword(String password);
	
	 @Query("select Count(u) from User u WHERE u.emailId=:emailId")
		int countByEmailId(@Param("emailId") String emailId);
	    
	    @Query("select Count(u) from User u WHERE u.password=:password")
	 		int countByPassword(@Param("password") String password);
	
}
