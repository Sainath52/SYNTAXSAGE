@echo off
REM Setup script for SyntaxSage - Installs Maven and configures environment

setlocal enabledelayedexpansion

echo ========================================
echo SyntaxSage Setup Script
echo ========================================
echo.

REM Check Java
echo [1/3] Checking Java Installation...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed!
    echo Please install Java 17+ from: https://adoptopenjdk.net/
    pause
    exit /b 1
) else (
    for /f "tokens=3" %%i in ('java -version 2^>^&1 ^| find "version"') do set JAVA_VERSION=%%i
    echo SUCCESS: Java !JAVA_VERSION! found
)
echo.

REM Check if Maven is already installed
echo [2/3] Checking Maven Installation...
mvn -version >nul 2>&1
if not errorlevel 1 (
    for /f "tokens=3" %%i in ('mvn -version 2^>^&1 ^| findstr "Apache Maven"') do set MVN_VERSION=%%i
    echo SUCCESS: Maven !MVN_VERSION! already installed
    goto :frontend_setup
)

REM Download and install Maven
echo Maven not found. Downloading Maven 3.9.6...
echo.

set MAVEN_HOME=C:\maven
set MAVEN_ZIP=%TEMP%\maven.zip
set MAVEN_URL=https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip

REM Create maven directory if it doesn't exist
if not exist "%MAVEN_HOME%" mkdir "%MAVEN_HOME%"

REM Download Maven
powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "$progressPreference = 'silentlyContinue'; ^
   Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '%MAVEN_ZIP%' -ErrorAction Stop; ^
   Write-Host 'Download complete, extracting...'; ^
   Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath '%TEMP%' -Force; ^
   Move-Item -Path '%TEMP%\apache-maven-3.9.6\*' -Destination '%MAVEN_HOME%' -Force; ^
   Remove-Item -Path '%MAVEN_ZIP%' -Force"

if errorlevel 1 (
    echo ERROR: Failed to download/extract Maven
    echo Please download manually from: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Add Maven to PATH permanently
setx Path "!Path!;%MAVEN_HOME%\bin"
set Path=!Path!;%MAVEN_HOME%\bin

echo SUCCESS: Maven installed to %MAVEN_HOME%
echo.

:frontend_setup
echo [3/3] Setting up Frontend Dependencies...

if not exist "%CD%\..\frontend\node_modules" (
    echo Installing npm packages...
    cd ..\frontend
    call npm install >nul 2>&1
    cd ..\backend
    echo SUCCESS: Frontend dependencies installed
) else (
    echo SUCCESS: Frontend dependencies already installed
)
echo.

echo ========================================
echo Setup Complete!
echo ========================================
echo.
echo You can now run:
echo   Backend:  mvn spring-boot:run
echo   Frontend: npm run dev
echo.
pause
