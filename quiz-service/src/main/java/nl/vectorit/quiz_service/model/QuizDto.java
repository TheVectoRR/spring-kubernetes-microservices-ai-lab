package nl.vectorit.quiz_service.model;

import lombok.Data;

@Data
public class QuizDto {
    String category;
    Integer numOfQuestions;
    String title;
}
