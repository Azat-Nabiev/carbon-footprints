import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import './WelcomePage.css';

function WelcomePage() {
    const location = useLocation();
    const message = location.state?.message;

    return (
        <div className="welcome-container">
            <h1 className="welcome-header">Welcome to Our carbon footprint calculator</h1>
            {message && <div className="success-message">{message}</div>}
            <p className="eco-info">
                Empower Your Green Journey: Measure, Mitigate, and Motivate Change with Every Click!
            </p>
            <div style={{ display: 'flex', justifyContent: 'center', gap: '10px', marginTop: '20px' }}>
                <Link to="/register" className="link-style">Register</Link>
                <Link to="/login" className="link-style">Login</Link>
            </div>
        </div>
    );
}

export default WelcomePage;
