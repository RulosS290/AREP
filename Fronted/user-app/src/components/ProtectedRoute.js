import React from 'react';
import { Navigate } from 'react-router-dom';

/**
 * ProtectedRoute component to guard routes that require authentication.
 * 
 * This component checks if the user is authenticated (stored in sessionStorage).
 * If authenticated, it renders the child components; otherwise, it redirects to the login page.
 * 
 * @param {object} children - The child components to render if authenticated.
 * @returns {JSX.Element} The child components or a redirect to the login page.
 */
const ProtectedRoute = ({ children }) => {
  const isAuthenticated = sessionStorage.getItem('authenticated');

  return isAuthenticated ? children : <Navigate to="/login" />;
};

export default ProtectedRoute;
