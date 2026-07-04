package com.example.codingmentor.controller;

import com.example.codingmentor.model.CodeReview;
import com.example.codingmentor.service.CodeMentorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentor")
@CrossOrigin(origins = "*") // Frontend to Backend Network Error (CORS) varama thaduka
public class CodeMentorController {

    private final CodeMentorService service;

    // Constructor Dependency Injection
    public CodeMentorController(CodeMentorService service) {
        this.service = service;
    }

    @PostMapping("/review")
    public CodeReview getCodeReview(@RequestBody String code) {
        // Core evaluation logic parameters trigger mapping
        return service.analyzeCode(code);
    }
}