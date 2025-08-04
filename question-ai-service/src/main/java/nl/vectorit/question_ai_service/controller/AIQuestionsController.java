package nl.vectorit.question_ai_service.controller;

import nl.vectorit.question_ai_service.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AIQuestionsController {

    private ChatClient chatClient;

    public AIQuestionsController(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("questions")
    public List<Question> getQuestions(@RequestParam String category, @RequestParam String numOfQ) {
        String message = """
                    Generate {numOfQ} multiple choice questions about {category}.
                    Every question has 4 possible answers, tell me also the correct answer.
                    {format}
                """;

        BeanOutputConverter<List<Question>> outputConverter = new BeanOutputConverter(
                new ParameterizedTypeReference<List<Question>>() {}
        );

        Prompt prompt = new Prompt(
                PromptTemplate.builder()
                        .template(message)
                        .variables(Map.of("category", category, "numOfQ", numOfQ, "format", outputConverter.getFormat()))
                        .build().createMessage()
        );

        return outputConverter.convert(chatClient.prompt(prompt).call().content());
    }
}
