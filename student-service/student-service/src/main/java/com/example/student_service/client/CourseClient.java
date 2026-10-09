package com.example.student_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CourseClient {

    private final RestTemplate restTemplate;

    public CourseClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Course getCourse(Long courseId) {

        String url =
            "http://localhost:8082/courses/" + courseId;

        return restTemplate.getForObject(
                url,
                Course.class
        );
    }
}