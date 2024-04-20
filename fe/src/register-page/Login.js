import React, { useState } from 'react';
import AuthService from '../AuthService';
import { useNavigate } from 'react-router-dom';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const result = await AuthService.login(email, password, navigate);
        if (result && result.error) {
            setError(result.message);
        }
    };

    return (
        <div className="page-container">
            <div className="register-container">
                {error && <div className="error-message">{error}</div>} {}
                <h1 className="register-title">Authenticate</h1>
                <form onSubmit={handleSubmit} className="register-form">
                    <input
                        className="register-input"
                        type="email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        placeholder="Email"
                        required
                    />
                    <input
                        className="register-input"
                        type="password"
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        placeholder="Password"
                        required
                    />
                    <button className="register-button" type="submit">Authenticate</button>
                </form>
            </div>
        </div>
    );
}

export default Login;
