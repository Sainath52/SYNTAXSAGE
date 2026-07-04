import { useState } from 'react';
import './App.css';

function App() {
  const [codeSnippet, setCodeSnippet] = useState<string>('');
  const [reviewResult, setReviewResult] = useState<string>('');
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>('');
  const [charCount, setCharCount] = useState<number>(0);

  const handleCodeChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setCodeSnippet(e.target.value);
    setCharCount(e.target.value.length);
  };

  const handleReviewSubmit = async () => {
    setError('');
    
    if (!codeSnippet.trim()) {
      setError("Please paste your code snippet to get started! 👨‍💻");
      return;
    }

    setIsLoading(true);
    setReviewResult('');

    try {
      const response = await fetch('/api/mentor/review', {
        method: 'POST',
        headers: {
          'Content-Type': 'text/plain',
        },
        body: codeSnippet,
      });

      if (!response.ok) {
        throw new Error("Backend server unavailable. Please ensure Spring Boot is running on port 8080.");
      }

      const data = await response.json();
      setReviewResult(data.reviewFeedback);
    } catch (err) {
      setError("❌ Connection failed. Make sure your Spring Boot backend is running!");
      console.error('Error:', err);
    } finally {
      setIsLoading(false);
    }
  };

  const copyToClipboard = () => {
    if (reviewResult) {
      navigator.clipboard.writeText(reviewResult);
      alert('✓ Feedback copied to clipboard!');
    }
  };

  const clearCode = () => {
    setCodeSnippet('');
    setReviewResult('');
    setError('');
    setCharCount(0);
  };

  return (
    <div className="app-container">
      {/* Animated Background */}
      <div className="animated-bg"></div>
      
      <div className="app-main-layout">
        {/* Header */}
        <header className="header-branding">
          <div className="header-content">
            <h1 className="title">✨ SyntaxSage AI</h1>
            <p className="subtitle">Your intelligent coding mentor powered by Groq's Llama 3 LLM</p>
            <div className="badge-container">
              <span className="badge">AI-Powered</span>
              <span className="badge">Real-time Analysis</span>
              <span className="badge">Code Quality</span>
            </div>
          </div>
        </header>

        {/* Main Content */}
        <div className="workspace-grid">
          {/* Left Panel - Code Editor */}
          <section className="editor-card">
            <div className="card-header">
              <h3>📝 Your Code</h3>
              <span className="char-count">{charCount} chars</span>
            </div>
            
            <textarea
              value={codeSnippet}
              onChange={handleCodeChange}
              placeholder="Paste your code here... (Java, Python, JavaScript, etc.)"
              className="code-textarea"
              spellCheck="false"
            />

            <div className="button-group">
              <button 
                onClick={handleReviewSubmit} 
                disabled={isLoading || !codeSnippet.trim()}
                className={`action-btn primary-btn ${isLoading ? 'loading' : ''}`}
              >
                {isLoading ? (
                  <>
                    <span className="spinner"></span>
                    Analyzing your code...
                  </>
                ) : (
                  <>
                    <span>⚡</span> Get Code Review
                  </>
                )}
              </button>
              
              <button 
                onClick={clearCode}
                className="action-btn secondary-btn"
              >
                🔄 Clear
              </button>
            </div>
          </section>

          {/* Right Panel - Results */}
          <section className="results-card">
            <div className="card-header">
              <h3>🎯 AI Feedback</h3>
              {reviewResult && (
                <button onClick={copyToClipboard} className="copy-btn" title="Copy feedback">
                  📋 Copy
                </button>
              )}
            </div>

            {error && (
              <div className="error-message">
                <p>{error}</p>
              </div>
            )}

            {isLoading ? (
              <div className="loading-state">
                <div className="loading-animation">
                  <div className="ai-thinking"></div>
                  <p>Groq AI is analyzing your code...</p>
                  <p className="loading-subtitle">Analyzing: Time/Space Complexity, Performance & Optimizations</p>
                </div>
              </div>
            ) : reviewResult ? (
              <div className="feedback-content">
                <div className="feedback-text">
                  <pre>{reviewResult}</pre>
                </div>
                <div className="feedback-meta">
                  <span>✓ Full analysis complete: Quality, Complexity, Security & Optimizations</span>
                </div>
              </div>
            ) : (
              <div className="empty-state">
                <div className="empty-icon">📊</div>
                <h4>No analysis yet</h4>
                <p>Submit code to get AI analysis including:</p>
                <div className="feature-list">
                  <span>⏱️ Time Complexity</span>
                  <span>💾 Space Complexity</span>
                  <span>✅ Optimized Code</span>
                  <span>🔒 Security Review</span>
                </div>
              </div>
            )}
          </section>
        </div>

        {/* Footer */}
        <footer className="app-footer">
          <p>SyntaxSage AI • Powered by <strong>Groq</strong> • Built with React & Spring Boot</p>
          <p className="footer-note">Always review AI suggestions before implementing</p>
        </footer>
      </div>
    </div>
  );
}

export default App;