package nl.vectorit.quiz_service.service;

import nl.vectorit.quiz_service.model.QuestionForUser;
import nl.vectorit.quiz_service.model.Quiz;
import nl.vectorit.quiz_service.model.QuizResponse;
import nl.vectorit.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public ResponseEntity<String> createQuiz(String category, Integer numOfQuestions, String title) {

//        List<Integer> questions = // RestTemplate http://localhost:8080/question/generate
//
//        Quiz quiz = new Quiz();
//        quiz.setTitle(title);
////        quiz.setQuestions(questions);
//
//        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionForUser>> getQuizQuestions(Integer quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);

//        if (quiz.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
////        List<Question> questionsFromDB = quiz.get().getQuestions();
////        List<QuestionForUser> questionForUsers = questionsFromDB.stream().map(
////                question -> new QuestionForUser(
////                        question.getId(),
////                        question.getQuestionTitle(),
////                        question.getOption1(),
////                        question.getOption2(),
////                        question.getOption3(),
////                        question.getOption4())
////        ).toList();
//
        return new ResponseEntity<>(Arrays.asList(), HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> responses) {
        Optional<Quiz> quiz = quizRepository.findById(id);

//        if (quiz.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        List<Question> questions = quiz.get().getQuestions();
//
//        Long correctAnswers = responses.stream()
//                .filter(response -> response.getAnswer().equals(
//                        questions.stream()
//                                .filter(question -> response.getId().equals(question.getId()))
//                                .findFirst().get()
//                                .getRightAnswer())
//                )
//                .count();

        return new ResponseEntity<>(Math.toIntExact(2), HttpStatus.OK);

    }
}
