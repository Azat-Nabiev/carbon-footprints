import React, { useEffect, useState } from 'react';
import AuthService from '../AuthService';
import './UserContentPage.css';

function UserContentPage() {
    const [user, setUser] = useState(null);
    const [addresses, setAddress] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const userId = localStorage.getItem('userId');
            if (userId) {
                const data = await AuthService.getUserProfileInfo(userId);
                setUser(data || null);
            }
        };
        fetchData();
    }, []);

    useEffect(() => {
        const fetchData = async () => {
            const userId = localStorage.getItem('userId');
            if (userId) {
                const data = await AuthService.getUserAddresses(userId);
                setAddress(data || []);
            }
        };
        fetchData();
    }, []);


    const formatDate = (dateArray) => {
        const [year, month, day] = dateArray;
        return new Date(year, month - 1, day).toLocaleDateString();
    };

    return (
        <div className="page-container">
            <button className="logout-button">Logout</button>
            <div className="content-container">  {/* Flex container */}
                <div className="profile-container">
                    <h1 className="profile-title">User Profile</h1>
                    {user ? (
                        <div className="user-info">
                            <div className="info-item">
                                <span className="info-label">Name:</span>
                                <span className="info-value">{user.name}</span>
                            </div>
                            <div className="info-item">
                                <span className="info-label">Surname:</span>
                                <span className="info-value">{user.lastName}</span>
                            </div>
                            <div className="info-item">
                                <span className="info-label">Email:</span>
                                <span className="info-value">{user.email}</span>
                            </div>
                            <div className="info-item">
                                <span className="info-label">Member Since:</span>
                                <span className="info-value">{formatDate(user.createdDate)}</span>
                            </div>
                        </div>
                    ) : (
                        <p>Loading user information...</p>
                    )}
                </div>
                <div className="addresses-container">
                    <h1 className="profile-title">Addresses</h1>

                </div>
            </div>
        </div>
    );
}

export default UserContentPage;