package com.moneymentor.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

@Service
public class MentorService {
    public Map<String, Object> calculateFinances(double partnerAIncome, double partnerBIncome, double totalExpenses) {
        double totalIncome = partnerAIncome + partnerBIncome;
        double remaining = totalIncome - totalExpenses;
        
        Map<String, Object> result = new HashMap<>();
        result.put("totalIncome", totalIncome);
        result.put("totalExpenses", totalExpenses);
        result.put("remaining", remaining);
        
        // Simple advice logic
        if (remaining < 0) {
            result.put("advice", "You are spending more than you earn! Time to review those expenses.");
            result.put("status", "danger");
        } else if (remaining < totalIncome * 0.2) {
            result.put("advice", "You are saving less than 20%. Try to cut some non-essential expenses.");
            result.put("status", "warning");
        } else {
            result.put("advice", "Great job! You have a healthy budget with solid savings potential.");
            result.put("status", "success");
        }
        
        // Proportional expense split suggestion
        if(totalIncome > 0) {
            double splitA = (partnerAIncome / totalIncome) * totalExpenses;
            double splitB = (partnerBIncome / totalIncome) * totalExpenses;
            result.put("splitA", String.format("%.2f", splitA));
            result.put("splitB", String.format("%.2f", splitB));
        } else {
            result.put("splitA", "0.00");
            result.put("splitB", "0.00");
        }
        
        return result;
    }
}
