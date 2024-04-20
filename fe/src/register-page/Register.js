import React, { useState } from 'react';
import AuthService from '../AuthService'
import { useNavigate } from 'react-router-dom';
import './Register.css';

function Register() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        await AuthService.register(email, password, firstName, lastName, navigate);
    };

    return (
        <div className="page-container">
            <div className="register-container">
                <h1 className="register-title">Register</h1>
                <form onSubmit={handleSubmit} className="register-form">
                    <input
                        className="register-input"
                        type="text"
                        value={firstName}
                        onChange={e => setFirstName(e.target.value)}
                        placeholder="First Name"
                        required
                    />
                    <input
                        className="register-input"
                        type="text"
                        value={lastName}
                        onChange={e => setLastName(e.target.value)}
                        placeholder="Last Name"
                        required
                    />
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
                    <button className="register-button" type="submit">Register</button>
                </form>
            </div>
        </div>
    );
}

export default Register;
