import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom'; 
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';

/**
 * Register component for user registration.
 * 
 * This component allows users to create a new account by entering a username, password, and password confirmation.
 * The component handles form submission, password visibility toggling, and validation.
 */
function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');
  const [passwordVisible, setPasswordVisible] = useState(false);
  const [confirmPasswordVisible, setConfirmPasswordVisible] = useState(false);
  const navigate = useNavigate();

  /**
   * Handles the registration form submission.
   * Validates the passwords, sends a POST request to the backend, and navigates to the login page upon success.
   * 
   * @param {object} event - The form submission event.
   */
  const handleRegister = async (event) => {
    event.preventDefault();
  
    if (password !== confirmPassword) {
      setError('Passwords do not match');
      return;
    }

    if (password.length < 8 || password.length > 20) {
      setError('Password must be between 8 and 20 characters');
      return;
    }
  
    try {
      const response = await axios.post('http://localhost:8080/auth/register', {
        username,
        password,
      });
  
      if (response.data === "User successfully registered") {
        navigate('/login');
      } else {
        setError('Error registering user');
      }
    } catch (error) {
      setError('Registration error');
      console.error('Error registering user:', error);
    }
  }; 

  /**
   * Toggles the visibility of the password field.
   */
  const togglePasswordVisibility = () => {
    setPasswordVisible(!passwordVisible);
  };

  /**
   * Toggles the visibility of the confirm password field.
   */
  const toggleConfirmPasswordVisibility = () => {
    setConfirmPasswordVisible(!confirmPasswordVisible);
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4" style={{ width: '400px' }}>
        <h2 className="text-center">Register</h2>

        {error && <div className="alert alert-danger">{error}</div>}

        <form onSubmit={handleRegister}>
          <div className="mb-3">
            <label htmlFor="username" className="form-label">Username</label>
            <input
              type="text"
              id="username"
              className="form-control"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          
          <div className="mb-3 position-relative">
            <label htmlFor="password" className="form-label">
              Password
            </label>
            <div className="input-group">
              <input
                type={passwordVisible ? "text" : "password"}
                id="password"
                className="form-control"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                minLength="8"
                maxLength="20"
              />
              
              <span className="input-group-text" onClick={togglePasswordVisibility}>
                <FontAwesomeIcon icon={passwordVisible ? faEyeSlash : faEye} />
              </span>
            </div>
            <div id="emailHelp" className="form-text">Must be between 8 and 20 characters</div>
          </div>
          
          <div className="mb-3 position-relative">
            <label htmlFor="confirmPassword" className="form-label">Confirm Password</label>
            <div className="input-group">
              <input
                type={confirmPasswordVisible ? "text" : "password"}
                id="confirmPassword"
                className="form-control"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
              />
              <span className="input-group-text" onClick={toggleConfirmPasswordVisibility}>
                <FontAwesomeIcon icon={confirmPasswordVisible ? faEyeSlash : faEye} />
              </span>
            </div>
          </div>
          
          <button type="submit" className="btn btn-primary w-100">Register</button>
        </form>

        <p className="text-center mt-3">
          Already have an account? <Link to="/login">Log in here</Link>
        </p>
      </div>
    </div>
  );
}

export default Register;