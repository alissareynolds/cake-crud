package com.example.cake_crud.controller;

import com.example.cake_crud.model.Cake;
import com.example.cake_crud.service.CakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CakeControllerTest {

    private CakeController mockCakeController;
    private CakeService mockCakeService;

    public final Cake input = new Cake(null, "German Chocolate", "chocolate", "brown", 3, true, false, false);
    public final Cake input2 = new Cake(null, "Chantilly", "cream", "white", 4, true, true, false);
    public final Cake recordWithId = new Cake(UUID.randomUUID(), "German Chocolate", "chocolate", "brown", 3, true, false, false);
    public final Cake recordWithId2 = new Cake(recordWithId.getId(), "Chantilly", "cream", "white", 4, true, true, false);

    @BeforeEach
    public void setup() {
        mockCakeService = Mockito.mock(CakeService.class);
        mockCakeController = new CakeController(mockCakeService);
    }

    @Test
    public void createCake_shouldReturnCakeAndCREATEDHttpStatus() {
        Mockito.when(mockCakeService.create(Mockito.any())).thenReturn(recordWithId);
        ResponseEntity<Cake> response = mockCakeController.createCake(input);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getAllCakes_shouldReturnListOfCakesAndOKHttpStatus() {
        List<Cake> cakes = new ArrayList<>();
        cakes.add(input);
        cakes.add(input2);
        Mockito.when(mockCakeService.getAll()).thenReturn(cakes);
        ResponseEntity<List<Cake>> response = mockCakeController.getAllCakes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cakes, response.getBody());
    }

    @Test
    public void getCakeById_shouldReturnCakeAndOKHttpStatus() {
        Mockito.when(mockCakeService.getById(recordWithId.getId())).thenReturn(recordWithId);
        ResponseEntity<Cake> response = mockCakeController.getCakeById(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void getCakeByType_shouldReturnCakeAndOKHttpStatus() {
        Mockito.when(mockCakeService.getByType(recordWithId.getType())).thenReturn(recordWithId);
        ResponseEntity<Cake> response = mockCakeController.getCakeByType(recordWithId.getType());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

    @Test
    public void updateCake_shouldReturnCakeAndOKHttpStatus() {
        Mockito.when(mockCakeService.update(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Cake> response = mockCakeController.updateCake(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void patchCake_shouldReturnCakeAndOKHttpStatus() {
        Mockito.when(mockCakeService.patch(input2, recordWithId.getId())).thenReturn(recordWithId2);
        ResponseEntity<Cake> response = mockCakeController.patchCake(input2, recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId2, response.getBody());
    }

    @Test
    public void deleteCake_shouldReturnCakeAndOKHttpStatus() {
        Mockito.when(mockCakeService.delete(recordWithId.getId())).thenReturn(recordWithId);
        ResponseEntity<Cake> response = mockCakeController.deleteCake(recordWithId.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recordWithId, response.getBody());
    }

}