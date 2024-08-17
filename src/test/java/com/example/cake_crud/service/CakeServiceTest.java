package com.example.cake_crud.service;

import com.example.cake_crud.model.Cake;
import com.example.cake_crud.repository.CakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CakeServiceTest {

    private CakeService mockCakeService;
    private CakeRepository mockCakeRepository;

    public final Cake input = new Cake(null, "German Chocolate", "chocolate", "brown", 3, true, false, false);
    public final Cake input2 = new Cake(null, "Chantilly", "cream", "white", 4, true, true, false);
    public final Cake recordWithId = new Cake(UUID.randomUUID(), "German Chocolate", "chocolate", "brown", 3, true, false, false);
    public final Cake recordWithId2 = new Cake(recordWithId.getId(), "Chantilly", "cream", "white", 4, true, true, false);

    @BeforeEach
    public void setup() {
        mockCakeRepository = Mockito.mock(CakeRepository.class);
        mockCakeService = new CakeService(mockCakeRepository);
    }

    @Test
    public void create_shouldReturnCreatedCake() {
        Mockito.when(mockCakeRepository.save(Mockito.any())).thenReturn(recordWithId);
        Cake response = mockCakeService.create(input);
        assertEquals(recordWithId, response);
    }

    @Test
    public void getAll_shouldReturnListOfCakes() {
        List<Cake> cakes = new ArrayList<>();
        cakes.add(input);
        cakes.add(input2);
        Mockito.when(mockCakeRepository.findAll()).thenReturn(cakes);
        List<Cake> response = mockCakeService.getAll();
        assertEquals(cakes, response);
    }

    @Test
    public void getById_shouldReturnCake() {
        Mockito.when(mockCakeRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Cake response = mockCakeService.getById(recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void getByType_shouldReturnCake() {
        Mockito.when(mockCakeRepository.findByType(recordWithId.getType())).thenReturn(Optional.of(recordWithId));
        Cake response = mockCakeService.getByType(recordWithId.getType());
        assertEquals(recordWithId, response);
    }

    @Test
    public void delete_shouldReturnDeletedBook() {
        Mockito.when(mockCakeRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Cake response = mockCakeService.delete(recordWithId.getId());
        assertEquals(recordWithId, response);
    }
}