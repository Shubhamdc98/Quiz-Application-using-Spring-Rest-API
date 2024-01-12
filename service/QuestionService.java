package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questiondao;
	
	public ResponseEntity<List<Question>> getAllQuestion() {
		try {
		return new ResponseEntity<List<Question>>(questiondao.findAll(), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST); 
			
		}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
		return new ResponseEntity<List<Question>>(questiondao.findByCategory(category), HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
	}
		return new ResponseEntity<List<Question>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		questiondao.save(question);
		try {
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}catch(Exception e) {
		e.printStackTrace();
	}
		return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
	}
	public ResponseEntity<String> dltQuestion(Integer id) {
		questiondao.deleteById(id);
		try {
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed", HttpStatus.BAD_REQUEST);
	}

	/*
	 * public Question updateQuestion(Integer id, Question newQuestion) { return
	 * questiondao.findById(id).map(question -> {
	 * question.setCategory(newQuestion.getCategory());
	 * question.setDifficultyLevel(newQuestion.getDifficultyLevel());
	 * question.setOption1(newQuestion.getOption1());
	 * question.setOption2(newQuestion.getOption2());
	 * question.setOption3(newQuestion.getOption3());
	 * question.setOption4(newQuestion.getOption4());
	 * question.setQuestionTitle(newQuestion.getQuestionTitle());
	 * question.setRightAnswer(newQuestion.getRightAnswer()); }); }
	 */



}
