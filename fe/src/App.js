import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import WelcomePage from './welcome-page/WelcomePage';
import Register from './register-page/Register';
import Login from './register-page/Login';
import ContentPage from './ContentPage';


function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<WelcomePage />} />
                <Route path="/register" element={<Register />} />
                <Route path="/login" element={<Login />} />
                <Route path="/content/:userId" element={<ContentPage />} />
            </Routes>
        </Router>
    );
}

export default App;
