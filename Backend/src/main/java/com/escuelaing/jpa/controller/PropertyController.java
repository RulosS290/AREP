package com.escuelaing.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.escuelaing.jpa.model.Property;
import com.escuelaing.jpa.repository.PropertyRepository;

import java.util.List;

/**
 * REST Controller for managing property-related requests.
 * Provides endpoints for CRUD operations on properties.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    /**
     * Retrieves a list of all properties.
     *
     * @return A list of Property objects.
     */
    @GetMapping
    public List<Property> getAllProperties() {
        return (List<Property>) propertyRepository.findAll();
    }

    /**
     * Retrieves a property by its ID.
     *
     * @param id The ID of the property to retrieve.
     * @return The Property object if found, or null if not found.
     */
    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new property.
     *
     * @param property The Property object to create.
     * @return The created Property object.
     */
    @PostMapping
    public Property createProperty(@RequestBody Property property) {
        return propertyRepository.save(property);
    }

    /**
     * Updates an existing property.
     *
     * @param id The ID of the property to update.
     * @param property The updated property data.
     * @return The updated Property object, or null if the property was not found.
     */
    @PutMapping("/{id}")
    public Property updateProperty(@PathVariable Long id, @RequestBody Property property) {
        Property existingProperty = propertyRepository.findById(id).orElse(null);
        if (existingProperty != null) {
            existingProperty.setAddress(property.getAddress());
            existingProperty.setPrice(property.getPrice());
            existingProperty.setSize(property.getSize());
            existingProperty.setDescription(property.getDescription());
            return propertyRepository.save(existingProperty);
        }
        return null;
    }

    /**
     * Deletes a property by its ID.
     *
     * @param id The ID of the property to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyRepository.deleteById(id);
    }
}
