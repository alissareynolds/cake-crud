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

}