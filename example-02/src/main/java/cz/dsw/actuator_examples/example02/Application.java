package cz.dsw.actuator_examples.example02;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dsw.actuator_examples.example02.entity.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired private ObjectMapper objectMapper;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> logger.info("*** Hello World, greetings from Dwarf ***");
    }

    @Bean Function<Object, String> toJson(ObjectMapper objectMapper) {
        return token -> {
            String result = null;
            try {
                result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(token);
            } catch (JsonProcessingException e) {
                logger.error("Object to JSON mapping failed!", e);
            }
            return result;
        };
    }
}
