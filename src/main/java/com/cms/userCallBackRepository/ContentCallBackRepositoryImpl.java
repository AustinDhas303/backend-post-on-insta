package com.cms.userCallBackRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cms.model.Category;
import com.cms.model.Content;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

@Repository
public class ContentCallBackRepositoryImpl implements ContentCallBackRepository{
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public List<Content> getContents(int page, int size, Integer contentId, String contentName, Category category, Integer status) {
		// TODO Auto-generated method stub
		EntityManager em = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Content> cq = cb.createQuery(Content.class);
		Root<Content> root = cq.from(Content.class);
		
		Join<Content, Category> categoryJoin = root.join("category");
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.equal(categoryJoin.get("contentId"), contentId));
		predicates.add(cb.equal(categoryJoin.get("contentName"), contentName));
		predicates.add(cb.equal(categoryJoin.get("categoryId"), category));
		predicates.add(cb.equal(categoryJoin.get("categoryName"), category));

		cq.where(cb.and(predicates.toArray(new Predicate[0])));
		cq.orderBy(cb.desc(root.get("createdDate")));
		
		TypedQuery<Content> query = em.createQuery(cq);
		query.setFirstResult(page);
		query.setMaxResults(size);
		
		List<Content> result = query.getResultList();
		em.close();
		return result;
	}

}
