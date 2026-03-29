package com.moneymentor.controller;

import com.moneymentor.service.AIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MentorController {
    
    private final AIService aiService;
    
    public MentorController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam(defaultValue = "0") double incomeA, 
                            @RequestParam(defaultValue = "0") double incomeB, 
                            @RequestParam(defaultValue = "0") double expenses, 
                            Model model) {
        
        double totalIncome = incomeA + incomeB;
        double savings = totalIncome - expenses;
        
        // Fetch AI advice from Gemini API
        String aiAdvice = aiService.getFinancialAdvice(totalIncome, expenses, savings);
        
        boolean lowSavingsAlert = (totalIncome > 0) && (savings < totalIncome * 0.20);
        boolean highExpensesAlert = (totalIncome > 0) && (expenses > totalIncome * 0.70);
        
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("expenses", expenses);
        model.addAttribute("savings", savings);
        model.addAttribute("advice", aiAdvice);
        model.addAttribute("lowSavingsAlert", lowSavingsAlert);
        model.addAttribute("highExpensesAlert", highExpensesAlert);
        
        return "result";
    }
}
