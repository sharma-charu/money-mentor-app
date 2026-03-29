# AI Money Mentor – Couple's Money Planner

**AI Money Mentor** is an intelligent, containerized Spring Boot web application designed specifically to empower couples to plan and track their shared financial goals. The app combines deterministic, rule-based diagnostic alerts with dynamic **Google Gemini AI** to produce curated, actionable financial advice—all presented in a beautiful dark-mode dashboard.

## Features
- **Dynamic Diagnostics**: Automatically calculates your exact savings and throws real-time alerts if your spending is alarmingly high (>70%) or your savings are worryingly low (<20%).
- **Gemini AI Integration**: Uses the Gemini API (`gemini-flash-latest`) to ingest your exact income ratio and generate curated Indian tax-saving tips (HRA/NPS) alongside exact SIP suggestions. 
- **Modern UI**: Full glassmorphic css interface complete with dynamic spacing, highly responsive cards, and distinct color-coded logic thresholds.

## Built With
- **Java 17** 
- **Spring Boot 3.2.x** (Web / MVC)
- **Thymeleaf** (Frontend templating)
- **Google Gemini API** (via `RestTemplate` integration)

## Getting Started

### Prerequisites 
- **Java JDK 17** or above installed.
- **Maven** installed on your terminal path.
- A free **Google Gemini API Key** sourced from [Google AI Studio](https://aistudio.google.com/).

### Installation & Running 
1. **Clone the project** or navigate your terminal into the root directory: `Money-Mentor`.
2. **Inject your API Key**:
   - Open `src/main/java/com/moneymentor/service/AIService.java`.
   - Find the constant `API_KEY` variable and replace `AIza...` / `YOUR_API_KEY` with your actual working key.
3. **Boot the Server**:
   Execute the Spring Boot Maven wrapper to compile and boot the embedded Tomcat server on port 8080.
   ```bash
   mvn spring-boot:run
   ```
4. **Access the Planner**:
   Open a web browser and navigate to: `http://localhost:8080/`

## Project Architecture
- `MentorController.java`: Intercepts form data, executes mathematical rule triggers, structures Thymeleaf models.
- `AIService.java`: Structures the specialized prompt and manages external HTTP calls to the Gemini generative framework.
- `index.html` & `result.html`: Frontend view maps ingesting structured data and AI context directly via Thymeleaf payloads. 
- `style.css`: The engine behind the premium dark-mode aesthetic.

---
*Created as an intelligent financial planning proof-of-concept.*
