package com.example.cake_crud.controller;

import com.example.cake_crud.exception.CakeNotFoundException;
import com.example.cake_crud.model.Cake;
import com.example.cake_crud.service.CakeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cake")
public class CakeController {

    private final CakeService cakeService;

    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    @PostMapping
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {
        Cake newCake = cakeService.create(cake);
        return new ResponseEntity<>(newCake, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cake>> getAllCakes() {
        List<Cake> cakes = cakeService.getAll();
        return new ResponseEntity<>(cakes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cake> getCakeById(@PathVariable UUID id) {
        Cake cake;
        try {
            cake = cakeService.getById(id);
        } catch (CakeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cake, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Cake> getCakeByType(@PathVariable String type) {
        Cake cake;
        try {
            cake = cakeService.getByType(type);
        } catch (CakeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cake, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cake> updateCake(@RequestBody Cake cake, @PathVariable UUID id) {
        Cake newCake;
        try {
            newCake = cakeService.update(cake, id);
        } catch (CakeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCake, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cake> patchCake(@RequestBody Cake cake, @PathVariable UUID id) {
        Cake newCake;
        try {
            newCake = cakeService.patch(cake, id);
        } catch (CakeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(newCake, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cake> deleteCake(@PathVariable UUID id) {
        Cake cake;
        try {
            cake = cakeService.delete(id);
        } catch (CakeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cake, HttpStatus.OK);
    }
}
