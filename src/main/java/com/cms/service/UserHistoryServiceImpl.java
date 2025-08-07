package com.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cms.dto.UserHistoryResponseDTO;
import com.cms.dto.UserHistoryDTO;
import com.cms.model.Content;
import com.cms.model.User;
import com.cms.model.UserHistory;
import com.cms.repository.ContentRepository;
import com.cms.repository.UserHistoryRepository;
import com.cms.repository.UserRepository;

@Service
public class UserHistoryServiceImpl implements UserHistoryService{

	private final ContentRepository contentRepository;
	
	private final UserRepository userRepository;
	
	private final UserHistoryRepository userHistoryRepository ;
	
	public UserHistoryServiceImpl(ContentRepository contentRepository, UserRepository userRepository,
			UserHistoryRepository userHistoryRepository) {
		super();
		this.contentRepository = contentRepository;
		this.userRepository = userRepository;
		this.userHistoryRepository = userHistoryRepository;
	}


	@Override
	public Map<String, String> createUserHistory(UserHistoryDTO userHistoryDTO) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = userDetails.getUsername();
		User loggedInUser = userRepository.getEmail(emailId);

		Map<String, String> map = new HashMap<String, String>();
		if(loggedInUser.getRole().getRoleId() == 1 || loggedInUser.getRole().getRoleId() == 2) {
			Content content = contentRepository.findById(userHistoryDTO.getContentId())
		            .orElseThrow(() -> new RuntimeException("Content not found"));
		    User user = userRepository.findById(userHistoryDTO.getUserId())
		            .orElseThrow(() -> new RuntimeException("User not found"));

		    System.out.println("///////"+userHistoryDTO.getContentId());
		    System.out.println("///////"+content.getJsonData());
		    
		    Optional<UserHistory> existingAttempt = userHistoryRepository.findByUserIdAndContentId(user.getUserId(), userHistoryDTO.getContentId()); 
		    if (existingAttempt.isPresent()) {
		    	throw new RuntimeException("Error: You have already attempted this quiz."); 
		    	}
		    List<Map<String, String>> storedQuestions = content.getJsonData();
		    List<Map<String, String>> userResponses = userHistoryDTO.getJsonData();

		    int score = 0;
		    int rewardsPoint=10;
		    for (Map<String, String> response : userResponses) {
		        String question = response.get("question");
		        System.out.println(question);
		        String userAnswer = response.get("selectedAnswer");
		        System.out.println(userAnswer);

		        if (question != null && userAnswer != null) {
		        	System.out.println("//////////////");
		            Map<String, String> storedQuestion = storedQuestions.stream()
		                    .filter(q -> question.equals(q.get("question")))
		                    .findFirst()
		                    .orElse(null);
		            
		            System.out.println("quest "+storedQuestion);
		            System.out.println("stored quest"+storedQuestions);

		            
		            if (storedQuestion != null) {
		                String correctAnswer = storedQuestion.get("correctanswer");
		                if (correctAnswer != null && correctAnswer.equals(userAnswer)) {
		               
		                	System.out.println("correctAnswer "+storedQuestion);
		                    score += 10;
		                    System.out.println("Score "+score);
		                    
		                    rewardsPoint+=2;
		                }
		            }
		        }
		    }

//		    int rewardsPoint = score >= 80 ? 10 : score >= 50 ? 5 : 0;
		    System.out.println("Reward point "+rewardsPoint);

		    UserHistory userHistory = new UserHistory();
		    userHistory.setJsonData(userResponses);
		    userHistory.setScores(score);
		    userHistory.setRewardsPoint(rewardsPoint);
		    userHistory.setContent(content);
		    userHistory.setUser(user);
		    userHistoryRepository.save(userHistory);
		    map.put("status", "success");
		    map.put("message", "Quiz attempted successfully");
		}
	    
	    return map;
	}



	@Override
	public UserHistoryResponseDTO getAllUserHistory(int page, int size, String userName) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = userDetails.getUsername();
		User loggedInUser = userRepository.getEmail(emailId);

		UserHistoryResponseDTO responseUserHistoryDTO=new UserHistoryResponseDTO();
		if(loggedInUser.getRole().getRoleId() == 1) {
			List<UserHistoryDTO> userHistoryDTOs=new ArrayList<>();
			Pageable pageable = PageRequest.of(page, size);
			List<UserHistory> userHistories =userHistoryRepository.getAllUserHistory(pageable, userName);
			
			for(UserHistory userHist:userHistories) {
				UserHistoryDTO userHistoryDTO=new UserHistoryDTO();
				userHistoryDTO.setUserHistoryId(userHist.getUserHistoryId());
				userHistoryDTO.setScores(userHist.getScores());
				userHistoryDTO.setRewardsPoint(userHist.getRewardsPoint());
				userHistoryDTO.setJsonData(userHist.getJsonData());
				 
				if (userHist.getUser() != null) {
			        	userHistoryDTO.setUserName(userHist.getUser().getFirstName() + " " + userHist.getUser().getLastName());
			    } else {
			        	userHistoryDTO.setUserName(null);
			    }
			    if (userHist.getContent() != null) {
			       	userHistoryDTO.setContentName(userHist.getContent().getContentName());
			    } else {
			       	userHistoryDTO.setContentName(null);
			    }
			    if (userHist.getContent() != null) {
			    	userHistoryDTO.setContentId(userHist.getContent().getContentId());
			    } else {
			        	
			    	userHistoryDTO.setContentId(null);
			    }
			        userHistoryDTOs.add(userHistoryDTO);
				}
				responseUserHistoryDTO.setUserHistoryDTO(userHistoryDTOs);
		}
		return responseUserHistoryDTO;
	}


	@Override
	public UserHistoryDTO fetchUserHistoryById(Long userHistoryId) {
		// TODO Auto-generated method stub
		UserHistory userHistory = userHistoryRepository.findById(userHistoryId)
                .orElseThrow(() -> new RuntimeException("UserHistory not found with ID: " + userHistoryId));

        UserHistoryDTO dto = new UserHistoryDTO();
        dto.setUserHistoryId(userHistory.getUserHistoryId());
        dto.setScores(userHistory.getScores());
        dto.setRewardsPoint(userHistory.getRewardsPoint());
        dto.setJsonData(userHistory.getJsonData());
        dto.setAttemptedDate(userHistory.getAttemptedDate());

        if (userHistory.getContent() != null) {
            dto.setContentId(userHistory.getContent().getContentId());
        }

        if (userHistory.getUser() != null) {
            dto.setUserId(userHistory.getUser().getUserId());
        } else {
            dto.setUserId(null); // Or handle as per your business logic
        }
        if(userHistory.getUser()!=null) {
        	dto.setUserName(userHistory.getUser().getFirstName()+" "+userHistory.getUser().getLastName());
        }
        else {
        	dto.setUserName(null);
        }
        if(userHistory.getContent()!=null){
        	dto.setContentName(userHistory.getContent().getContentName());
        }
        else {
        	dto.setContentName(null);
        }

//        if (userHistory.getUser() != null) {
//            dto.setUserId(userHistory.getUser().getUserId());
//        }
        return dto;
	}



	@Override
	public UserHistory createUserVideo(UserHistoryDTO userHistoryDTO) {
		// TODO Auto-generated method stub
		Content content = contentRepository.findById(userHistoryDTO.getContentId())
	            .orElseThrow(() -> new RuntimeException("Content not found"));
	    User user = userRepository.findById(userHistoryDTO.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    System.out.println("///////"+userHistoryDTO.getContentId());
	    System.out.println("///////"+content.getJsonData());
	    
	    List<Map<String, String>> userResponses = userHistoryDTO.getJsonData();

	    int score=10;
	    int rewardsPoint=20;
	    UserHistory userHistory = new UserHistory();
	    userHistory.setJsonData(userResponses);
	    userHistory.setScores(score);
	    userHistory.setRewardsPoint(rewardsPoint);
	    userHistory.setContent(content);
	    userHistory.setUser(user);

	    return userHistoryRepository.save(userHistory);

	}



	@Override
	public List<UserHistoryDTO> fetchAllUserHistoryByUserId(Long userId) {
		// TODO Auto-generated method stub
	    List<UserHistory> userHistoryList = userHistoryRepository.findByUser_UserId(userId);

	    // Convert entity list to DTO list
	    return userHistoryList.stream().map(userHistory -> {
	        UserHistoryDTO dto = new UserHistoryDTO();
	        dto.setUserHistoryId(userHistory.getUserHistoryId());
	        dto.setScores(userHistory.getScores());
	        dto.setRewardsPoint(userHistory.getRewardsPoint());
	        dto.setJsonData(userHistory.getJsonData());
	        dto.setAttemptedDate(userHistory.getAttemptedDate());

	        if (userHistory.getContent() != null) {
	            dto.setContentId(userHistory.getContent().getContentId());
	            dto.setContentName(userHistory.getContent().getContentName());
	        }

	        if (userHistory.getUser() != null) {
	            dto.setUserId(userHistory.getUser().getUserId());
	            dto.setUserName(userHistory.getUser().getFirstName() + " " + userHistory.getUser().getLastName());
	        }

	        return dto;
	    }).collect(Collectors.toList());
	}

	
}

		

