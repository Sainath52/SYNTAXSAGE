package com.example.codingmentor.service;

import com.example.codingmentor.model.CodeReview;
import com.example.codingmentor.repository.CodeReviewRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CodeMentorService {

    @Value("${groq.api.url}") // Namma first file application.properties-la vachurundha Groq API URL matrum
                              // unique secret Key-ai intha Java variables-kulla (groqUrl, apiKey) inject
                              // பண்ணுது.
    private String groqUrl;

    @Value("${groq.api.key}")
    private String apiKey;

    private final CodeReviewRepository repository;

    // Constructor Injection: Repository-ai inga connect panrom
    public CodeMentorService(CodeReviewRepository repository) {
        this.repository = repository;
    }

    public CodeReview analyzeCode(String codeSnippet) {
        // 1. HTTP Client Request create பண்ண RestTemplate use panrom
        RestTemplate restTemplate = new RestTemplate(); // standard HTTP client for making RESTful API calls in Spring
                                                        // Boot.

        // 2. Request Headers configuration (Metadata & Security Key pass panrom)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey); // API Key-ai token rules-maathiri network request head-la map
                                                          // panrom.

        // 3. Prompt Engineering Principle (Advanced Analysis with Complexity &
        // Optimization)
        String systemPrompt = "You are an expert Coding Mentor and Algorithm Optimization Specialist. " +
                "Analyze the provided code comprehensively and provide detailed feedback in the following structured format:\n\n"
                +
                "📋 1. CODE QUALITY ASSESSMENT\n" +
                "   - Code readability and maintainability\n" +
                "   - Naming conventions and documentation\n" +
                "   - Code structure and organization\n\n" +
                "⚡ 2. PERFORMANCE ANALYSIS\n" +
                "   - Identify performance bottlenecks\n" +
                "   - Performance issues and anti-patterns\n" +
                "   - Algorithm efficiency\n\n" +
                "⏱️ TIME COMPLEXITY ANALYSIS\n" +
                "   - Current implementation: Provide Big-O notation (e.g., O(n), O(n²), O(log n), etc.)\n" +
                "   - Explain the reasoning behind the complexity\n" +
                "   - Identify operations that contribute to complexity\n\n" +
                "💾 SPACE COMPLEXITY ANALYSIS\n" +
                "   - Current implementation: Provide Big-O notation (e.g., O(1), O(n), O(n²), etc.)\n" +
                "   - Memory usage assessment\n" +
                "   - Identify major memory allocations\n\n" +
                "🔒 SECURITY VULNERABILITIES\n" +
                "   - Identify security issues\n" +
                "   - SQL injection, XSS, or other attack vectors\n" +
                "   - Data validation concerns\n\n" +
                "✅ OPTIMIZED CODE\n" +
                "   - Provide refactored code with improvements\n" +
                "   - Include comments explaining optimizations\n" +
                "   - Show the optimized time complexity: O(...)\n" +
                "   - Show the optimized space complexity: O(...)\n" +
                "   - Explain the improvement strategy\n\n" +
                "📊 COMPLEXITY COMPARISON\n" +
                "   - Original: Time O(...), Space O(...)\n" +
                "   - Optimized: Time O(...), Space O(...)\n" +
                "   - Improvement: X% faster, Y% less memory\n\n" +
                "💡 RECOMMENDATIONS\n" +
                "   - Best practices for this type of code\n" +
                "   - Alternative approaches\n" +
                "   - When to use this pattern vs alternatives\n\n" +
                "Format the response clearly with sections, use code blocks for code examples, and be precise with Big-O notation."; // Prompt
                                                                                                                                     // Engineering
                                                                                                                                     // Instruction!
                                                                                                                                     // LLM
                                                                                                                                     // Model-ku
                                                                                                                                     // namma
                                                                                                                                     // direct-ah
                                                                                                                                     // oru
                                                                                                                                     // strict
                                                                                                                                     // role
                                                                                                                                     // assign
                                                                                                                                     // panrom
                                                                                                                                     // ("You
                                                                                                                                     // are
                                                                                                                                     // an
                                                                                                                                     // expert
                                                                                                                                     // Coding
                                                                                                                                     // Mentor
                                                                                                                                     // Agent").
                                                                                                                                     // Athu
                                                                                                                                     // eppadi
                                                                                                                                     // structure-ah
                                                                                                                                     // feedback
                                                                                                                                     // tharanum
                                                                                                                                     // (Quality,
                                                                                                                                     // Bugs,
                                                                                                                                     // Security,
                                                                                                                                     // Refactoring)
                                                                                                                                     // nu
                                                                                                                                     // guide
                                                                                                                                     // panrom.
                                                                                                                                     // Ithintha
                                                                                                                                     // project
                                                                                                                                     // criteria-oada
                                                                                                                                     // main
                                                                                                                                     // core
                                                                                                                                     // rules!

        // 4. Groq API Structure-ku payload mapping setup
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama-3.1-8b-instant"); // High-speed open-source LLM

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", systemPrompt));
        messages.add(Map.of("role", "user", "content", codeSnippet));
        requestBody.put("messages", messages);
        // Groq cloud platform standard JSON format-ai expect பண்ணும். Athukaaga Java
        // Map & List data structures-ஐ வச்சு JSON format dynamic body payload-ah
        // construct பண்ணி llama-3.1-8b-instant AI engine configuration settings mapping
        // execute panrom.
        // Header and Body are combined into one Entity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // 5. Groq Cloud Server-ku API Request-ai trigger panrom
            ResponseEntity<Map> response = restTemplate.postForEntity(groqUrl, entity, Map.class); // it will forwards
                                                                                                   // the datas as
                                                                                                   // string format to
                                                                                                   // the groq cloud
                                                                                                   // server and it will
                                                                                                   // return the
                                                                                                   // response as string
                                                                                                   // format. So, we
                                                                                                   // need to parse the
                                                                                                   // response to
                                                                                                   // extract the
                                                                                                   // feedback from the
                                                                                                   // AI model.

            // Response parsing algorithms to extract LLM output
            List choices = (List) response.getBody().get("choices");
            Map firstChoice = (Map) choices.get(0);
            Map message = (Map) firstChoice.get("message");
            String feedback = (String) message.get("content");

            // 6. DB saving operation (Raw code & AI output)
            CodeReview review = new CodeReview();
            review.setRawCode(codeSnippet);
            review.setReviewFeedback(feedback);
            return repository.save(review); // Groq thந்த text response text contents matrum original raw user script
                                            // analytics records ready aanathum automatic-ah code_reviews MySQL table
                                            // database-la insert execution context write aydum.

        } catch (Exception e) {
            CodeReview errorReview = new CodeReview();
            errorReview.setRawCode(codeSnippet);
            errorReview.setReviewFeedback("Error communicating with Groq AI API agent: " + e.getMessage());
            return errorReview;
        }
    }
}