#!/bin/bash
# SyntaxSage - Full Stack Development Server Startup Script

echo ""
echo "========================================"
echo "   SyntaxSage AI Startup Script"
echo "========================================"
echo ""

# Check if both directories exist
if [ ! -d "backend" ]; then
    echo "[ERROR] backend directory not found!"
    exit 1
fi

if [ ! -d "frontend" ]; then
    echo "[ERROR] frontend directory not found!"
    exit 1
fi

# Start backend in background
echo "[1] Starting Backend (Spring Boot)..."
cd backend
mvn spring-boot:run &
BACKEND_PID=$!
cd ..

# Wait 5 seconds for backend to start
sleep 5

# Start frontend
echo "[2] Starting Frontend (React + Vite)..."
cd frontend
npm run dev &
FRONTEND_PID=$!
cd ..

echo ""
echo "========================================"
echo "   Servers are starting..."
echo "========================================"
echo ""
echo "Backend:  http://localhost:8080"
echo "Frontend: http://localhost:5173"
echo ""
echo "Press Ctrl+C to stop the servers"
echo ""

# Wait for both processes
wait $BACKEND_PID $FRONTEND_PID
