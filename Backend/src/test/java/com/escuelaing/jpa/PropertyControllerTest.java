package com.escuelaing.jpa;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.escuelaing.jpa.controller.PropertyController;
import com.escuelaing.jpa.model.Property;
import com.escuelaing.jpa.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for the PropertyController class, which manages Property entities.
 * It mocks the PropertyRepository to isolate the behavior of the controller methods.
 */
public class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyRepository propertyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Existing tests...

    /**
     * Test to verify behavior when no properties are found.
     */
    @Test
    public void testGetAllProperties_EmptyList() {
        // Arrange: Mock an empty list from the repository
        when(propertyRepository.findAll()).thenReturn(new ArrayList<>());

        // Act: Call the method in the controller
        List<Property> result = propertyController.getAllProperties();

        // Assert: Verify that the list is empty and repository was called
        assertTrue(result.isEmpty());
        verify(propertyRepository, times(1)).findAll();
    }

    /**
     * Test to verify behavior when a property is not found by ID.
     */
    @Test
    public void testGetPropertyById_NotFound() {
        // Arrange: Mock an empty result for a non-existing property ID
        when(propertyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act: Call the method in the controller
        Property result = propertyController.getPropertyById(1L);

        // Assert: Verify that the result is null and repository interaction
        assertNull(result);
        verify(propertyRepository, times(1)).findById(1L);
    }

    /**
     * Test to verify behavior when updating a non-existing property.
     */
    @Test
    public void testUpdateProperty_NotFound() {
        // Arrange: Mock the repository to return empty when searching by ID
        Property updatedProperty = new Property("New Address", 200000.0, 200.0, "New Description");
        when(propertyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act: Call the update method with a non-existing ID
        Property result = propertyController.updateProperty(1L, updatedProperty);

        // Assert: Verify that the result is null and repository interaction
        assertNull(result);
        verify(propertyRepository, times(1)).findById(1L);
        verify(propertyRepository, never()).save(any(Property.class));
    }

    /**
     * Test to verify behavior when deleting a non-existing property.
     */
    @Test
    public void testDeleteProperty_NotFound() {
        // Arrange: Mock the repository to do nothing when deleting a non-existing ID
        doNothing().when(propertyRepository).deleteById(1L);

        // Act: Call the delete method for a non-existing ID
        propertyController.deleteProperty(1L);

        // Assert: Verify that the delete method was called on the repository
        verify(propertyRepository, times(1)).deleteById(1L);
    }

    /**
     * Test to verify behavior when creating a property with null values.
     */
    @Test
    public void testCreateProperty_NullValues() {
        // Arrange: Create a property with null values
        Property property = new Property(null, 0.0, 0.0, null);
        when(propertyRepository.save(property)).thenReturn(property);

        // Act: Call the method in the controller
        Property result = propertyController.createProperty(property);

        // Assert: Verify the saved property and repository interaction
        assertNotNull(result);
        assertNull(result.getAddress());
        assertEquals(0.0, result.getPrice());
        verify(propertyRepository, times(1)).save(property);
    }
}

