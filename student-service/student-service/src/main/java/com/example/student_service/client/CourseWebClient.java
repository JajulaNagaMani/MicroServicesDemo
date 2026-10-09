package com.example.student_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CourseWebClient {

    private final WebClient webClient;

    public CourseWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Course getCourse(Long courseId) {

        return webClient
                .get()
                .uri("http://localhost:8082/courses/{id}",
                        courseId)
                .retrieve()
                .bodyToMono(Course.class)
                .block();
    }
}