package nl.vectorit.quiz_service.controller;

import nl.vectorit.quiz_service.model.QuestionForUser;
import nl.vectorit.quiz_service.model.QuizDto;
import nl.vectorit.quiz_service.model.QuizResponse;
import nl.vectorit.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("get/{quizName}")
    public ResponseEntity<List<QuestionForUser>> getQuiz(@PathVariable String quizName) {
        return quizService.getQuizQuestions(quizName);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {

        return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumOfQuestions(), quizDto.getTitle());
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> responses) {
        return quizService.calculateResult(id, responses);
    }
}
