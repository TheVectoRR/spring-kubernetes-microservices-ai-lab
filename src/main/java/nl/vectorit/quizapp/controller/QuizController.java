package nl.vectorit.quizapp.controller;

import nl.vectorit.quizapp.model.QuestionForUser;
import nl.vectorit.quizapp.model.QuizResponse;
import nl.vectorit.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("get/{quizId}")
    public ResponseEntity<List<QuestionForUser>> getQuiz(@PathVariable Integer quizId) {
        return quizService.getQuizQuestions(quizId);
    }

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam Integer noOfQuestions,
                                             @RequestParam String title) {

        return quizService.createQuiz(category, noOfQuestions, title);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponse> responses) {
        return quizService.calculateResult(id, responses);
    }
}
