@echo off
REM SyntaxSage - Full Stack Development Server Startup Script

echo.
echo ========================================
echo   SyntaxSage AI Startup Script
echo ========================================
echo.

REM Check if both directories exist
if not exist "backend" (
    echo [ERROR] backend directory not found!
    exit /b 1
)

if not exist "frontend" (
    echo [ERROR] frontend directory not found!
    exit /b 1
)

REM Start backend in a new terminal
echo [1] Starting Backend (Spring Boot)...
start "SyntaxSage Backend" cmd /k "cd backend && ..\apache-maven-3.9.6\bin\mvn.cmd spring-boot:run"

REM Wait 5 seconds for backend to start
timeout /t 5 /nobreak

REM Start frontend in a new terminal
echo [2] Starting Frontend (React + Vite)...
start "SyntaxSage Frontend" cmd /k "cd frontend && npm run dev"

echo.
echo ========================================
echo   Servers are starting...
echo ========================================
echo.
echo Backend:  http://localhost:8080
echo Frontend: http://localhost:5173
echo.
echo Press Ctrl+C in each terminal to stop the servers
echo.
