class AuthService {
    static API_BASE_URL = 'http://localhost:8085';

    static async register(email, password, firstName, lastName, role, navigate) {
        try {
            const response = await fetch(`${this.API_BASE_URL}/api/v1/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password, firstName, lastName, role }),
            });
            const data = await response.json();
            if (response.ok) {
                this.storeTokens(data.access_token, data.refresh_token);
                console.log(data)
                navigate('/', { state: { message: 'Registration successful! Please log in.' } })
            } else {
                console.error('Registration failed:', data.message);
                // Handle registration errors
            }
        } catch (error) {
            console.error('An error occurred during registration:', error);
        }
    }

    static async login(email, password, navigate) {
        try {
            const response = await fetch(`${this.API_BASE_URL}/api/v1/auth/authenticate`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });
            const data = await response.json();
            if (response.ok) {
                this.storeTokens(data.access_token, data.refresh_token);
                localStorage.setItem('userId', data.userId);
                navigate(`/content/${data.userId}`);
            } else {
                console.error('Login failed:', data.message);
                return { error: true, message: data.message };
            }
        } catch (error) {
            console.error('Login failed:', error.message);
            return { error: true, message: 'Provided wrong creds' };
        }
    }

    static storeTokens(accessToken, refreshToken) {
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
    }

    static getAccessToken() {
        return localStorage.getItem('accessToken');
    }

    static async refreshToken() {
        const refreshToken = localStorage.getItem('refreshToken');
        if (!refreshToken) {
            console.error('No refresh token available');
            // Handle case where there is no refresh token available
            return;
        }

        try {
            const response = await fetch(`${this.API_BASE_URL}/api/v1/auth/refresh-token`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ refreshToken }),
            });
            const data = await response.json();
            if (response.ok) {
                this.storeTokens(data.accessToken, data.refreshToken);
                return data.accessToken;
            } else {
                console.error('Failed to refresh token:', data.message);
                // Handle refresh token errors
            }
        } catch (error) {
            console.error('An error occurred while refreshing the token:', error);
        }
    }

    static async getContent(userId) {
        let accessToken = this.getAccessToken();
        try {
            let response = await fetch(`${this.API_BASE_URL}/api/v1/content/${userId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`,
                },
            });

            if (response.status === 401) {
                accessToken = await this.refreshToken();
                if (accessToken) {
                    response = await fetch(`${this.API_BASE_URL}/api/v1/content/${userId}`, {
                        headers: {
                            'Authorization': `Bearer ${accessToken}`,
                        },
                    });
                    if (!response.ok) {
                        throw new Error('Failed to fetch content after refreshing token');
                    }
                } else {
                    throw new Error('Unable to refresh token');
                }
            }

            const data = await response.json();
            return data;
        } catch (error) {
            console.error('An error occurred while fetching content:', error);
        }
    }

    static async deleteBook(bookId) {
        let accessToken = this.getAccessToken();
        const userId = localStorage.getItem('userId');

        const deleteBookRequest = async () => {
            return fetch(`${this.API_BASE_URL}/api/v1/content/${bookId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`,
                    'USER_ID': userId,
                },
            });
        };

        let response = await deleteBookRequest(accessToken);

        if (response.status === 401) {
            const newAccessToken = await this.refreshToken();
            if (newAccessToken) {
                accessToken = newAccessToken;
                response = await deleteBookRequest(accessToken);
            } else {
                throw new Error('Unable to refresh token and delete book');
            }
        }

        if (!response.ok) {
            throw new Error('Failed to delete book after token refresh');
        }
    }

    static async addBook(author, isbn) {
        let accessToken = this.getAccessToken();
        const userId = localStorage.getItem('userId');

        const addBookRequest = async () => {
            return fetch(`${this.API_BASE_URL}/api/v1/content`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`,
                    'USER_ID': userId,
                },
                body: JSON.stringify({ author, isbn }),
            });
        };

        let response = await addBookRequest();

        if (response.status === 401) { // Если токен истек
            accessToken = await this.refreshToken(); // Пытаемся получить новый токен
            if (accessToken) {
                response = await addBookRequest(); // Повторяем запрос с новым токеном
            } else {
                throw new Error('Unable to refresh token');
            }
        }

        if (response.ok) {
            const newBook = await response.json();
            return newBook;
        } else {
            const errorData = await response.json();
            throw new Error(errorData.message); // Выбрасываем ошибку с сообщением от сервера
        }
    }


}

export default AuthService;

