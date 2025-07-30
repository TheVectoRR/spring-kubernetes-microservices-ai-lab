package nl.vectorit.question_service.repository;

import nl.vectorit.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategoryIgnoreCase(String category);

    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category Order By RANDOM() LIMIT :numOfQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, Integer numOfQuestions);
}
