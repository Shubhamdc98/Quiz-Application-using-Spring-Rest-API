package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import com.example.demo.model.Wrapper;

@Service
public class QuizService {
	
	//private static final int ResponseEntity = 0;

	@Autowired
	QuizDao quizdao;
	
	@Autowired
	QuestionDao questiondao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		List<Question> questions = questiondao.createRandomQuestionByCategory(category, numQ);
		quiz.setQuestions(questions);
		quizdao.save(quiz);
		
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<Wrapper>> getQuiz(Integer id) {
		Optional<Quiz> quiz = quizdao.findById(id);
		List<Question> question = quiz.get().getQuestions();
		List<Wrapper> queWrapper = new ArrayList<>();
		
		for(Question q: question) {
			Wrapper qw = new Wrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			queWrapper.add(qw);
		}
		 return new ResponseEntity<>(queWrapper, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
		Quiz quiz = quizdao.findById(id).get();
		List<Question> ques = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for(Response r: response) {
			if(r.getResponse().equals(ques.get(i).getRightAnswer()))
				right++;
			
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

	

	


	
	
	
}
