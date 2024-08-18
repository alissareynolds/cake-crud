package com.example.cake_crud.service;

import com.example.cake_crud.exception.CakeNotFoundException;
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

    private CakeService cakeService;
    private CakeRepository mockCakeRepository;

    public final Cake input = new Cake(null, "German Chocolate", "chocolate", "brown", 3, true, false, false);
    public final Cake input2 = new Cake(null, "Chantilly", "cream", "white", 4, true, true, false);
    public final Cake recordWithId = new Cake(UUID.randomUUID(), "German Chocolate", "chocolate", "brown", 3, true, false, false);
    public final Cake recordWithId2 = new Cake(recordWithId.getId(), "Chantilly", "cream", "white", 4, true, true, false);

    public final UUID id = UUID.fromString("59c47568-fde0-4dd7-9aef-03db6a962810");

    @BeforeEach
    public void setup() {
        mockCakeRepository = Mockito.mock(CakeRepository.class);
        cakeService = new CakeService(mockCakeRepository);
    }

    @Test
    public void create_shouldReturnCreatedCake() {
        Mockito.when(mockCakeRepository.save(Mockito.any())).thenReturn(recordWithId);
        Cake response = cakeService.create(input);
        assertEquals(recordWithId, response);
    }

    @Test
    public void getAll_shouldReturnListOfCakes() {
        List<Cake> cakes = new ArrayList<>();
        cakes.add(input);
        cakes.add(input2);
        Mockito.when(mockCakeRepository.findAll()).thenReturn(cakes);
        List<Cake> response = cakeService.getAll();
        assertEquals(cakes, response);
    }

    @Test
    public void getById_shouldReturnCake() {
        Mockito.when(mockCakeRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Cake response = cakeService.getById(recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void getById_throwsExceptionWhenBookWasNotFound() {
        Mockito.when(mockCakeRepository.findById(id)).thenReturn(Optional.empty());
        CakeNotFoundException exception = assertThrows(CakeNotFoundException.class, () -> cakeService.getById(id));
        assertEquals("A cake with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void getByType_shouldReturnListOfCakes() {
        Mockito.when(mockCakeRepository.findByType(recordWithId.getType())).thenReturn(List.of(recordWithId));
        List<Cake> response = cakeService.getByType(recordWithId.getType());
        assertEquals(List.of(recordWithId), response);
    }

    @Test
    public void update_shouldReturnUpdatedCake() {
        Mockito.when(mockCakeRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCakeRepository.save(Mockito.any())).thenReturn(recordWithId);
        Cake response = cakeService.update(input2, recordWithId.getId());
        assertEquals(recordWithId, response);
    }

    @Test
    public void update_throwsExceptionWhenBookWasNotFound() {
        Mockito.when(mockCakeRepository.findById(id)).thenReturn(Optional.empty());
        CakeNotFoundException exception = assertThrows(CakeNotFoundException.class, () -> cakeService.update(input, id));
        assertEquals("A cake with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_throwsExceptionWhenBookWasNotFound() {
        Mockito.when(mockCakeRepository.findById(id)).thenReturn(Optional.empty());
        CakeNotFoundException exception = assertThrows(CakeNotFoundException.class, () -> cakeService.patch(input, id));
        assertEquals("A cake with id: " + id + " was not found.", exception.getMessage());
    }

    @Test
    public void patch_shouldReturnUpdatedType() {
        Cake input = new Cake();
        input.setType("Raspberry");
        Mockito.when(mockCakeRepository.findById(recordWithId.getId())).thenReturn(Optional.of(recordWithId));
        Mockito.when(mockCakeRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);
        Cake response = cakeService.patch(input, recordWithId.getId());
        assertEquals(recordWithId.getId(), response.getId());
        assertEquals("Raspberry", response.getType());
        assertEquals("chocolate", response.getFrosting());
        assertEquals("brown", response.getFrostingColor());
        assertEquals(3, response.getLayers());
        assertEquals(true, response.getHasTopping());
        assertEquals(false, response.getHasFruit());
        assertEquals(false, response.getIsTiered());
    }
}