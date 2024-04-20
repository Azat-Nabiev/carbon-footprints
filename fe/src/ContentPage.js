import React, { useEffect, useState } from 'react';
import AuthService from './AuthService';
import './ContentPage.css';

function ContentPage() {
    const [books, setBooks] = useState([]);
    const [author, setAuthor] = useState('');
    const [isbn, setIsbn] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            const userId = localStorage.getItem('userId');
            if (userId) {
                const data = await AuthService.getContent(userId);
                setBooks(data || []);
            }
        };
        fetchData();
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const newBook = await AuthService.addBook(author, isbn);
            setBooks([...books, newBook]);
            setAuthor('');
            setIsbn('');
        } catch (error) {
            console.error('Error adding book:', error.message);
        }
    };

    const handleDelete = async (bookId) => {
        try {
            await AuthService.deleteBook(bookId);
            const updatedBooks = books.filter(book => book.id !== bookId);
            setBooks(updatedBooks);
        } catch (error) {
            console.error('Error deleting book:', error.message);
        }
    };

    return (
        <div className="content-page">
            <div className="book-list">
                <h1>Protected Content</h1>
                <div className="books">
                    {books.map((book) => (
                        <div key={book.id} className="book-item">
                            <div className="book-details">
                                <p>Author: {book.author}</p>
                                <p>ISBN: {book.isbn}</p>
                                <p>ID: {book.id}</p>
                            </div>
                            <button className="delete-button" onClick={() => handleDelete(book.id)}>Delete</button>
                        </div>
                    ))}
                </div>
            </div>
            <div className="form-container">
                <form onSubmit={handleSubmit} className="book-form">
                    <label>
                        Author:
                        <input type="text" value={author} onChange={e => setAuthor(e.target.value)} required />
                    </label>
                    <label>
                        ISBN:
                        <input type="text" value={isbn} onChange={e => setIsbn(e.target.value)} required />
                    </label>
                    <button type="submit">Add Book</button>
                </form>
            </div>
        </div>
    );
}

export default ContentPage;
