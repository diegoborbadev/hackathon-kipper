package dev.diegoborba.hackathonkipper;

import dev.diegoborba.hackathonkipper.model.BaseModel;
import dev.diegoborba.hackathonkipper.service.CrudService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractCrudTest<S extends CrudService<T, Long>, T extends BaseModel<Long>> {

    @Autowired
    private S service;

    @Test
    public void testCrudOperations() {
        testCreateOperations();
        testReadOperations();
        testUpdateOperations();
        testDeleteOperations();
    }

    // Create
    private void testCreateOperations() {
        // Create element
        T element = createTestElement();
        element = service.createElement(element);
        assertNotNull(element.getId());

        // Create Elements
        List<T> elements = List.of(createTestElement(), createTestElement(), createTestElement());
        elements = service.createElements(elements);
        elements.forEach(e -> assertNotNull(e.getId()));
    }

    // Read
    private void testReadOperations() {
        // Count
        long elementCount = service.count();
        assertEquals(4, elementCount);

        // Find all
        List<T> elements = service.findAll();
        assertFalse(elements.isEmpty());
        assertEquals(4, elements.size());

        // Find by id
        Optional<T> element = service.findById(elements.get(0).getId());
        assertTrue(element.isPresent());

        // Find all by ids
        List<T> elementsById = service.findAllById(List.of(elements.get(0).getId()));
        assertFalse(elementsById.isEmpty());

        // Find all pageable
        Page<T> elementsPageable = service.findAll(PageRequest.of(0, 2));
        assertFalse(elementsPageable.isEmpty());
    }

    // Update
    private void testUpdateOperations() {
        // Update element
        List<T> elements = service.findAll();
        T element = elements.get(0);
        T elementToUpdate = elements.get(1);
        assertEquals(element, elementToUpdate);

        updateTestElement(elementToUpdate);
        assertNotEquals(element, elementToUpdate);

        T updatedElement = service.updateElement(elementToUpdate).orElseThrow();
        assertEquals(elementToUpdate, updatedElement);

        // Update element by id
        element = service.updateElement(element.getId(), elementToUpdate).orElseThrow();
        assertEquals(element, elementToUpdate);
    }

    // Delete
    private void testDeleteOperations() {
        // Delete element
        List<T> elements = service.findAll();
        T element = elements.get(0);
        assertTrue(service.deleteElement(element));

        // Delete element by id
        assertFalse(service.deleteElement(element.getId()));
        assertTrue(service.deleteElement(elements.get(1).getId()));

        // Delete all
        service.deleteAll();
        assertEquals(0, service.count());
    }

    protected abstract T createTestElement();

    protected abstract void updateTestElement(T element);
}