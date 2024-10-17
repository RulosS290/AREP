import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit, faTrashAlt } from "@fortawesome/free-solid-svg-icons";
import './Properties.css'; // Importing the CSS file

/**
 * Properties component for managing the property list and form for adding/editing properties.
 * 
 * This component allows the user to:
 * - View a list of properties
 * - Add new properties
 * - Edit existing properties
 * - Delete properties
 * 
 * The user must be authenticated to access this page.
 */
function Properties() {
  const [properties, setProperties] = useState([]);
  const [formData, setFormData] = useState({
    address: "",
    price: "",
    size: "",
    description: "",
  });
  const [editingProperty, setEditingProperty] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (!sessionStorage.getItem("authenticated")) {
      navigate("/login");
    } else {
      fetchProperties();
    }
  }, [navigate]);

  const fetchProperties = async () => {
    try {
      const response = await axios.get("http://localhost:8080/properties");
      setProperties(response.data);
    } catch (error) {
      console.error("Error fetching properties:", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingProperty) {
        await axios.put(
          `http://localhost:8080/properties/${editingProperty.id}`,
          formData
        );
        setEditingProperty(null);
      } else {
        await axios.post("http://localhost:8080/properties", formData);
      }
      fetchProperties();
      setFormData({ address: "", price: "", size: "", description: "" });
    } catch (error) {
      console.error("Error creating or updating property:", error);
    }
  };

  const handleEdit = (property) => {
    setEditingProperty(property);
    setFormData({
      address: property.address,
      price: property.price,
      size: property.size,
      description: property.description,
    });
  };

  const handleDelete = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/properties/${id}`);
      fetchProperties();
    } catch (error) {
      console.error("Error deleting property:", error);
    }
  };

  const handleLogout = () => {
    sessionStorage.removeItem("authenticated");
    navigate("/login");
  };

  return (
    <div className="container mt-5">
      <div className="d-flex justify-content-end mb-4">
        <button onClick={handleLogout} className="btn btn-danger">
          Log Out
        </button>
      </div>
  
      <div className="card p-4 mb-5">
        <h2 className="text-center mb-4">
          {editingProperty ? "Edit Property" : "Add New Property"}
        </h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <input
              type="text"
              name="address"
              className="form-control"
              placeholder="Address"
              value={formData.address}
              onChange={handleInputChange}
              required
              title="Enter the property address"
            />
          </div>
  
          <div className="row">
            <div className="col-md-6 mb-3">
              <input
                type="number"
                name="price"
                className="form-control"
                placeholder="Price"
                value={formData.price}
                onChange={handleInputChange}
                required
                title="Enter the property price"
              />
            </div>
            <div className="col-md-6 mb-3">
              <input
                type="number"
                name="size"
                className="form-control"
                placeholder="Size"
                value={formData.size}
                onChange={handleInputChange}
                required
                title="Enter the property size"
              />
            </div>
          </div>
  
          <div className="mb-3">
            <textarea
              name="description"
              className="form-control"
              placeholder="Description"
              value={formData.description}
              onChange={handleInputChange}
              required
              title="Enter a brief description of the property"
            ></textarea>
          </div>
          <button type="submit" className="btn btn-primary w-100">
            {editingProperty ? "Save Changes" : "Save Property"}
          </button>
        </form>
      </div>
  
      {properties.length > 0 && ( // Only show the table if there are properties
        <>
          <h2 className="text-center mb-4">Property List</h2>
          <div className="table-responsive">
            <table className="table table-striped">
              <thead className="table-dark">
                <tr>
                  <th>Address</th>
                  <th>Price</th>
                  <th>Size </th>
                  <th>Description</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {properties.map((property) => (
                  <tr key={property.id}>
                    <td>{property.address}</td>
                    <td>{property.price}</td>
                    <td>{property.size}</td>
                    <td>{property.description}</td>
                    <td>
                      <button
                        className="btn btn-warning me-2"
                        onClick={() => handleEdit(property)}
                      >
                        <FontAwesomeIcon icon={faEdit} />{" "}
                        <span className="d-none d-md-inline">Edit</span>
                      </button>
                      <button
                        className="btn btn-danger"
                        onClick={() => handleDelete(property.id)}
                      >
                        <FontAwesomeIcon icon={faTrashAlt} />{" "}
                        <span className="d-none d-md-inline">Delete</span>
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </>
      )}
    </div>
  );
}
  
export default Properties;
