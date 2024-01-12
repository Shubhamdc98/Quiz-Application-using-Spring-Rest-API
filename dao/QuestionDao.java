package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
	
	List<Question> findByCategory(String category);

	@Query(value = "select q.id from question q where q.category=:category order by RAND() limit :numQ", nativeQuery = true)
	List<Question> createRandomQuestionByCategory(String category, int numQ);
}
