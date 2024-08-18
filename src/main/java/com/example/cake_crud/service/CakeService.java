package com.example.cake_crud.service;

import com.example.cake_crud.exception.CakeNotFoundException;
import com.example.cake_crud.model.Cake;
import com.example.cake_crud.repository.CakeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CakeService {

    private final CakeRepository cakeRepository;

    public CakeService(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    public Cake create(Cake cake) {
        return cakeRepository.save(cake);
    }

    public List<Cake> getAll() {
        return cakeRepository.findAll();
    }

    public Cake getById(UUID id) {
        Optional<Cake> optionalCake = cakeRepository.findById(id);
        if (optionalCake.isEmpty()) {
            throw new CakeNotFoundException("A cake with id: " + id + " was not found.");
        }
        return optionalCake.get();
    }

    public List<Cake> getByType(String type) {
        return cakeRepository.findByType(type);
    }

    public Cake update(Cake cake, UUID id) {
        Optional<Cake> originalCake = cakeRepository.findById(id);
        if (originalCake.isEmpty()) {
            throw new CakeNotFoundException("A cake with id: " + id + " was not found.");
        }
        cake.setId(id);
       return cakeRepository.save(cake);
    }

    public Cake patch(Cake cake, UUID id) {
        Optional<Cake> originalCake = cakeRepository.findById(id);
        if (originalCake.isEmpty()) {
            throw new CakeNotFoundException("A cake with id: " + id + " was not found.");
        }
        Cake updatedCake = originalCake.get();
        if (cake.getType() != null) {
            updatedCake.setType(cake.getType());
        }
        if (cake.getFrosting() != null) {
            updatedCake.setFrosting(cake.getFrosting());
        }
        if (cake.getFrostingColor() != null) {
            updatedCake.setFrostingColor(cake.getFrostingColor());
        }
        if (cake.getLayers() != null) {
            updatedCake.setLayers(cake.getLayers());
        }
        if (cake.getHasTopping() != null) {
            updatedCake.setHasTopping(cake.getHasTopping());
        }
        if (cake.getHasFruit() != null) {
            updatedCake.setHasFruit(cake.getHasFruit());
        }
        if (cake.getIsTiered() != null) {
            updatedCake.setIsTiered(cake.getIsTiered());
        }
        return cakeRepository.save(updatedCake);
    }

    public void delete(UUID id) {
        cakeRepository.deleteById(id);
    }
}
