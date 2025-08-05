package com.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public UserHistory createUserHistory(UserHistoryDTO userHistoryDTO) {
		
	    // Fetch content and user
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
	    // Extract stored questions and user responses
	    List<Map<String, String>> storedQuestions = content.getJsonData();
	    List<Map<String, String>> userResponses = userHistoryDTO.getJsonData();

	    // Calculate score
	    int score = 0;
	    int rewardsPoint=10;
	    for (Map<String, String> response : userResponses) {
	        String question = response.get("question");
	        System.out.println(question);
	        String userAnswer = response.get("selectedAnswer");
	        System.out.println(userAnswer);

	        if (question != null && userAnswer != null) {
	            // Find the matching stored question
	        	System.out.println("//////////////");
	            Map<String, String> storedQuestion = storedQuestions.stream()
	                    .filter(q -> question.equals(q.get("question")))
	                    .findFirst()
	                    .orElse(null);
	            
//	            String storedQuestion=
	            
	            System.out.println("quest "+storedQuestion);
	            System.out.println("stored quest"+storedQuestions);

	            
	            if (storedQuestion != null) {
	                String correctAnswer = storedQuestion.get("correctanswer");
	                if (correctAnswer != null && correctAnswer.equals(userAnswer)) {
	               
	                	System.out.println("correctAnswer "+storedQuestion);
	                    score += 10; // Assign points for correct answers
	                    System.out.println("Score "+score);
	                    
	                    rewardsPoint+=2;
	                }
	            }
	        }
	    }

	    // Calculate reward points
//	    int rewardsPoint = score >= 80 ? 10 : score >= 50 ? 5 : 0;
	    System.out.println("Reward point "+rewardsPoint);
	    // Save UserHistory
	    UserHistory userHistory = new UserHistory();
	    userHistory.setJsonData(userResponses);
	    userHistory.setScores(score);
	    userHistory.setRewardsPoint(rewardsPoint);
	    userHistory.setContent(content);
	    userHistory.setUser(user);

	    return userHistoryRepository.save(userHistory);
	}



	@Override
	public UserHistoryResponseDTO getAllUserHistory() {
		// TODO Auto-generated method stub
		UserHistoryResponseDTO responseUserHistoryDTO=new UserHistoryResponseDTO();
		List<UserHistoryDTO> userHistoryDTOs=new ArrayList<>();
		List<UserHistory> userHistories =userHistoryRepository.findAll();
		
		for(UserHistory userHist:userHistories) {
			UserHistoryDTO userHistoryDTO=new UserHistoryDTO();
			userHistoryDTO.setUserHistoryId(userHist.getUserHistoryId());
			userHistoryDTO.setScores(userHist.getScores());
			userHistoryDTO.setRewardsPoint(userHist.getRewardsPoint());
			userHistoryDTO.setJsonData(userHist.getJsonData());
			 // Null check for user
//			userHistoryDTO.setU
			if (userHist.getUser() != null) {
		          //  userHistoryDTO.setUserId(userHist.getUser().getUserId());
		        	userHistoryDTO.setUserName(userHist.getUser().getFirstName() + " " + userHist.getUser().getLastName());
		        } else {
		        //    userHistoryDTO.setUserId(null); // Or handle as per your requirement
		        	userHistoryDTO.setUserName(null);
		        }
		        // Null check for content
		        if (userHist.getContent() != null) {
		         //   userHistoryDTO.setContentId(userHist.getContent().getContentId());
		        	userHistoryDTO.setContentName(userHist.getContent().getContentName());
		        } else {
		          //  userHistoryDTO.setContentId(null); // Or handle as per your requirement
		        	userHistoryDTO.setContentName(null);
		        }
		        // Null check for content
		        if (userHist.getContent() != null) {
		            userHistoryDTO.setContentId(userHist.getContent().getContentId());
//		        	userHistoryDTO.setContentName(userHist.getContent().getContentName());
		        } else {
		        	
		            userHistoryDTO.setContentId(null); // Or handle as per your requirement
		  
//		        	userHistoryDTO.setContentName(null);
		        }
		       
	userHistoryDTOs.add(userHistoryDTO);
			}
			responseUserHistoryDTO.setUserHistoryDTO(userHistoryDTOs);
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



//	@Override
//	public UserHistoryDTO fetchUserHistoryByusercontentId(Long userId, Long contentId) {
//		// TODO Auto-generated method stub
//		UserHistoryDTO userHistoryDTO=userHistoryRepository.findBy(userId, contentId);
//		return null;
//	}
	
	
	
	
}

		

