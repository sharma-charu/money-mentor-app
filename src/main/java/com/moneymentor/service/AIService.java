package com.moneymentor.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import java.util.HashMap;

@Service
public class AIService {

    // REPLACE 'YOUR_API_KEY' WITH YOUR ACTUAL GEMINI API KEY BELOW:
    private static final String API_KEY = "AIzaSyCvftJ_lyEPZ5bZO3BBBlsDP_CJdzO-Yn4";
    private static final String GEMINI_URL ="https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=" + API_KEY;
    public String getFinancialAdvice(double totalIncome, double expenses, double savings) {

    String prompt = String.format(
            "You are an intelligent financial advisor. Analyze this couple in India:\n" +
            "Total Income: %.2f\nExpenses: %.2f\nSavings: %.2f\n\n" +
            "Provide a highly structured and easy to read action plan with bullet points (using simple hyphens '-'). Include exactly these sections:\n" +
            "1. Quick Diagnosis\n" +
            "2. Actionable ways to reduce expenses\n" +
            "3. Actionable ways to increase savings\n" +
            "4. Suggested SIP investment amount\n" +
            "5. Tax-saving tips (e.g. HRA, NPS)",
            totalIncome, expenses, savings);

    try {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> textPart = new HashMap<>();
        textPart.put("text", prompt);

        Map<String, Object> content = new HashMap<>();
        content.put("parts", java.util.List.of(textPart));

        Map<String, Object> body = new HashMap<>();
        body.put("contents", java.util.List.of(content));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(GEMINI_URL, request, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        JsonNode candidates = root.path("candidates");

        if (candidates.isEmpty()) {
            return "No response from AI. Please try again.";
        }

        return candidates.get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();

    } catch (Exception e) {
        return "Error: " + e.getMessage();
    }
}


}
