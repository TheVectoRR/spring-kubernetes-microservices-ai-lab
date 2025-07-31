package nl.vectorit.quiz_service.service;

import nl.vectorit.quiz_service.feign.QuizInterface;
import nl.vectorit.quiz_service.model.QuestionForUser;
import nl.vectorit.quiz_service.model.Quiz;
import nl.vectorit.quiz_service.model.QuizResponse;
import nl.vectorit.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numOfQuestions, String title) {

        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numOfQuestions).getBody();
        System.out.println(questions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionForUser>> getQuizQuestions(String quizName) {
        Optional<Quiz> quiz = quizRepository.findByTitleIgnoreCase(quizName);

        if (quiz.isEmpty()) {
            System.err.println("No Quiz with title name: " + quizName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ResponseEntity<List<QuestionForUser>> questions = quizInterface.getQuestionsFromId(quiz.get().getQuestionsIds());

        if (!questions.getStatusCode().is2xxSuccessful()) {
            System.err.println("Failed to get questions from Ids from question-service: " + questions.getStatusCode());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(questions.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);

        if (!score.getStatusCode().is2xxSuccessful()) {
            System.err.println("Failed to get score from question-service: " + score.getStatusCode());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(score.getBody(), HttpStatus.OK);

    }
}
