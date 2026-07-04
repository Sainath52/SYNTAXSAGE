# ✅ Quick Start Guide - SyntaxSage AI

## Status: Frontend Ready ✅ | Backend Needs Maven Setup

Your frontend is now **error-free and ready to run**!  
For the full application, you need to install Maven for the Java backend.

---

## 🚀 OPTION 1: Run Frontend Only (Quick Demo)

```powershell
cd frontend
npm run dev
```

**Expected:**
- Browser opens: http://localhost:5173
- UI loads with SyntaxSage interface
- ⚠️ Code review won't work (needs backend)

---

## 🛠️ OPTION 2: Full Setup (Frontend + Backend)

### Step 1: Install Maven

**Windows - Using Installer (Recommended):**
1. Visit: https://maven.apache.org/download.cgi
2. Download: **apache-maven-3.9.6-bin.zip**
3. Extract to: `C:\maven` or `C:\Program Files\maven`
4. Add to PATH:
   - Right-click `This PC` → Properties → Advanced system settings
   - Click `Environment Variables`
   - Under `System variables`, click `New`
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\maven`
   - Edit `Path` → Add `;%MAVEN_HOME%\bin`
5. Restart PowerShell and verify:
   ```powershell
   mvn -version
   ```

**Alternative - Using Scoop (if installed):**
```powershell
scoop install maven
```

**Alternative - Using Chocolatey (if installed):**
```powershell
choco install maven
```

### Step 2: Configure Database & API Key

Edit: `backend/src/main/resources/application.properties`

```properties
# Update MySQL password
spring.datasource.password=YOUR_PASSWORD_HERE

# Get Groq API Key from https://console.groq.com/ and add it
groq.api.key=YOUR_GROQ_API_KEY_HERE
```

### Step 3: Create MySQL Database

```bash
mysql -u root -p

# Then in MySQL:
CREATE DATABASE syntaxsage_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
EXIT;
```

### Step 4: Run Backend

```powershell
cd backend
mvn spring-boot:run
```

**Expected output:**
```
Started CodingMentorApplication in 5.234 seconds
Tomcat started on port(s): 8080
```

### Step 5: Run Frontend (New Terminal)

```powershell
cd frontend
npm run dev
```

### Step 6: Use Application

1. Open: http://localhost:5173
2. Paste code in left panel
3. Click "Get Code Review Analytics"
4. View AI feedback on right panel

---

## ⚡ Quick Build (Without Maven)

If you want to see the frontend without backend:

```powershell
cd frontend
npm run build
npm run preview
```

This shows compiled production build at http://localhost:5173

---

## 🐛 Troubleshooting

### "mvn is not recognized"
✅ Maven not installed or PATH not updated
- See "Install Maven" steps above
- **Restart PowerShell after updating PATH**

### MySQL connection error
✅ Database not created or wrong password
```bash
mysql -u root -p
CREATE DATABASE syntaxsage_db;
```

### Groq API error
✅ API key missing or incorrect
- Get key: https://console.groq.com/
- Update in `application.properties`

### Cannot install npm packages
```powershell
cd frontend
rm node_modules -r
npm install
```

---

## 📝 Files Status

| File | Status | Status |
|------|--------|--------|
| `frontend/package.json` | ✅ | Packages installed |
| `frontend/vite.config.ts` | ✅ | Configured |
| `frontend/src/App.tsx` | ✅ | Errors cleared |
| `frontend/src/main.tsx` | ✅ | Errors cleared |
| `backend/pom.xml` | ✅ | Ready (needs Maven) |
| `backend/application.properties` | ⚠️ | Needs credentials |

---

## 🎯 Next Steps

1. **Install Maven** (see above)
2. **Update credentials** in `application.properties`
3. **Create MySQL database**
4. **Run backend**: `mvn spring-boot:run`
5. **Run frontend**: `npm run dev`

---

For detailed setup, see [SETUP_GUIDE.md](SETUP_GUIDE.md)
