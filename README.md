# SyntaxSage AI - Coding Mentor Application

A full-stack AI-powered code review application using Spring Boot backend and React frontend with Groq LLM API integration.

## 📋 Prerequisites

- **Java 17+** - Install from https://adoptopenjdk.net/
- **Maven 3.8+** - Install from https://maven.apache.org/download.cgi
- **Node.js 18+** - Install from https://nodejs.org/
- **MySQL 8.0+** - Install from https://www.mysql.com/downloads/

## 🚀 Quick Start Setup

### Step 1: Setup MySQL Database

```bash
# Open MySQL command line or MySQL Workbench and run:
CREATE DATABASE syntaxsage_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Step 2: Backend Setup

```bash
cd backend

# Install dependencies and build
mvn clean install

# Run the backend server
mvn spring-boot:run
```

**Expected Output:**
```
Started CodingMentorApplication in X seconds
Tomcat started on port(s): 8080
```

### Step 3: Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Run development server (automatically opens http://localhost:5173)
npm run dev
```

## ⚙️ Configuration

### Backend Configuration (application.properties)

Edit `backend/src/main/resources/application.properties`:

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/syntaxsage_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_mysql_password_here

# Groq API Configuration
groq.api.key=YOUR_GROQ_API_KEY
```

**Get Your Groq API Key:**
1. Visit https://console.groq.com/
2. Create account and get your API key
3. Replace `YOUR_GROQ_API_KEY` with your actual key

### MySQL Credentials

If your MySQL setup differs:
- Change `spring.datasource.username` (default: `root`)
- Change `spring.datasource.password` to your MySQL password
- Update the database URL if needed

## 🔧 What Was Fixed

✅ Created `pom.xml` with all required dependencies:
  - Spring Boot Web, Data JPA
  - MySQL Connector
  - Lombok
  - DevTools

✅ Fixed `CodingMentorApplication.java`:
  - Changed parameter from `nullParamsOrArgs` to `args`

✅ Fixed `CodeMentorService.java`:
  - Removed incorrect `java.net.http.HttpHeaders` import
  - Uses correct `org.springframework.http.HttpHeaders`

✅ Created `vite.config.ts`:
  - Configured Vite for React development
  - Added API proxy to backend (localhost:8080)

✅ Created `.eslintrc.cjs`:
  - TypeScript + React ESLint configuration

✅ Updated `package.json`:
  - Added missing devDependencies:
    - `eslint` and plugins
    - `react-refresh`
    - `eslint-plugin-react-hooks`

## 📱 Usage

1. **Open Browser:** http://localhost:5173
2. **Paste Code:** Enter your code snippet in the left textarea
3. **Click Button:** "Get Code Review Analytics"
4. **View Feedback:** Groq AI provides detailed code analysis

The feedback includes:
- Code Quality Assessment
- Performance Issues & Bugs
- Security Vulnerabilities
- Refactored Code Suggestions

## 🏗️ Project Architecture

```
SyntaxSage/
├── backend/                     # Spring Boot Application
│   ├── pom.xml                 # Maven configuration (FIXED ✅)
│   └── src/main/java/
│       └── com/example/codingmentor/
│           ├── CodingMentorApplication.java (FIXED ✅)
│           ├── controller/CodeMentorController.java
│           ├── service/CodeMentorService.java (FIXED ✅)
│           ├── model/CodeReview.java
│           └── repository/CodeReviewRepository.java
│
├── frontend/                    # React + TypeScript + Vite
│   ├── vite.config.ts          # (NEW ✅)
│   ├── .eslintrc.cjs           # (NEW ✅)
│   ├── package.json            # (UPDATED ✅)
│   ├── tsconfig.json
│   ├── index.html
│   └── src/
│       ├── App.tsx
│       ├── App.css
│       ├── main.tsx
│       └── vite-env.d.ts
```

## 🐛 Troubleshooting

### Backend won't start
```
Error: Connection refused - MySQL
→ Ensure MySQL service is running
→ Check credentials in application.properties
→ Verify database syntaxsage_db exists
```

### Frontend can't connect to backend
```
CORS Error / Connection failed
→ Ensure backend is running on port 8080
→ Check API URL: http://localhost:8080/api/mentor/review
```

### Groq API errors
```
Error: Invalid API key
→ Update groq.api.key in application.properties
→ Visit https://console.groq.com to verify key
```

## 🔗 API Endpoints

### POST /api/mentor/review
- **Purpose:** Submit code for review
- **Content-Type:** text/plain
- **Body:** Raw code snippet
- **Response:** JSON with CodeReview object

```json
{
  "id": 1,
  "rawCode": "public class Test {...}",
  "reviewFeedback": "Code quality assessment from Groq AI..."
}
```

## 📦 Build for Production

### Backend
```bash
cd backend
mvn clean package
java -jar target/coding-mentor-app-1.0.0.jar
```

### Frontend
```bash
cd frontend
npm run build
# Output in frontend/dist/
```

## 🚨 Common Issues Fixed

| Issue | Root Cause | Fix |
|-------|-----------|-----|
| pom.xml empty | Missing dependencies | Created complete pom.xml with Spring Boot stack |
| `nullParamsOrArgs` error | Typo in main method | Changed to correct `args` parameter |
| HttpHeaders import error | Wrong package import | Fixed to use `org.springframework.http.HttpHeaders` |
| Vite not configured | Missing config file | Created `vite.config.ts` with React support |
| ESLint missing | Incomplete devDependencies | Updated `package.json` with all required packages |

---

**Status:** ✅ All configurations complete and ready to run!

