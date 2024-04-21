import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import WelcomePage from './welcome-page/WelcomePage';
import Register from './register-page/Register';
import Login from './register-page/Login';
import UserContentPage from './user-content-page/UserContentPage';
import Header from './components/Header'

function App() {
    return (
        <div>
            <Header/>
        <Router>
            <Routes>
                <Route path="/" element={<WelcomePage />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                <Route path="/user/profile/:userId" element={<UserContentPage />} />
            </Routes>
        </Router>

            {/* rest of your app components */}
        </div>
    );
}

export default App;
