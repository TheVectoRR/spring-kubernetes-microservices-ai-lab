package nl.vectorit.question_service.service;

import nl.vectorit.question_service.model.Question;
import nl.vectorit.question_service.model.QuestionForUser;
import nl.vectorit.question_service.model.QuizResponse;
import nl.vectorit.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        return new ResponseEntity<>(questionRepository.findByCategoryIgnoreCase(category), HttpStatus.OK);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
    }

    public void DeleteQuestion(Integer questionId) {
        questionRepository.deleteById(questionId);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numOfQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(category, numOfQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionForUser>> getQuestionsFromIDs(List<Integer> questionIds) {
        List<QuestionForUser> questionsForUser = questionIds.stream()
                        .map(id -> questionRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(question -> new QuestionForUser(
                        question.getId(),
                        question.getQuestionTitle(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<QuizResponse> responses) {
        Long correctAnswers = responses.stream()
                .filter(response -> response.getAnswer().equals(
                        questionRepository.findById(response.getId()).get().getRightAnswer())
                )
                .count();
        return new ResponseEntity<>(Math.toIntExact(correctAnswers), HttpStatus.OK);
    }
}
