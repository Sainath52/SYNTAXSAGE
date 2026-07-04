@echo off
echo.
echo ============================================
echo   SyntaxSage - Docker Deployment Script
echo ============================================
echo.

REM Step 1: Login to Docker Hub
echo [1/4] Logging into Docker Hub as SAINATHKATHIRVEL...
echo SAINATHSAIVSB | docker login --username SAINATHKATHIRVEL --password-stdin
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Docker login failed. Make sure Docker Desktop is running!
    pause
    exit /b 1
)
echo [OK] Logged in successfully!
echo.

REM Step 2: Build images
echo [2/4] Building Docker images (this takes 2-5 minutes)...
docker compose build --no-cache
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Build failed!
    pause
    exit /b 1
)
echo [OK] Images built successfully!
echo.

REM Step 3: Push to Docker Hub
echo [3/4] Pushing images to Docker Hub...
docker push sainathkathirvel/syntaxsage-backend:latest
docker push sainathkathirvel/syntaxsage-frontend:latest
echo [OK] Images pushed to Docker Hub!
echo.

REM Step 4: Run the application
echo [4/4] Starting the application...
docker compose up -d
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to start containers!
    pause
    exit /b 1
)
echo.
echo ============================================
echo   SUCCESS! SyntaxSage is now running!
echo ============================================
echo.
echo   Open your browser at: http://localhost
echo.
echo   Docker Hub: https://hub.docker.com/u/sainathkathirvel
echo.
pause
