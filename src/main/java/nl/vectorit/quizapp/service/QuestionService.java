package nl.vectorit.quizapp.service;

import nl.vectorit.quizapp.model.Question;
import nl.vectorit.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
