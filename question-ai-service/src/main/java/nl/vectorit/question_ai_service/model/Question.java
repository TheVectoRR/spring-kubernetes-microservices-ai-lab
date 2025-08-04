package nl.vectorit.question_ai_service.model;

public record Question(
        String questionTitle,
        String option1,
        String option2,
        String option3,
        String option4,
        String rightAnswer
) {
}
