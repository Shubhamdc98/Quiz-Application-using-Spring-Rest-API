package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Question;
import com.example.demo.model.Response;
import com.example.demo.model.Wrapper;
import com.example.demo.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;
	
	@PostMapping("/createQuix")
	public ResponseEntity<String> createQuiz(@RequestParam String category,
			@RequestParam int numQ, @RequestParam String title){
				return quizService.createQuiz(category, numQ, title);
		
	}
	
	@GetMapping("/getQuiz/{id}")
	public ResponseEntity<List<Wrapper>> getMethodName(@PathVariable Integer id) {
		
		return quizService.getQuiz(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
		return quizService.calculateResult(id, response);
	}
	
}
