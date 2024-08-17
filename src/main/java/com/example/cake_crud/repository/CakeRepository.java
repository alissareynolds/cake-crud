package com.example.cake_crud.repository;

import com.example.cake_crud.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CakeRepository extends JpaRepository<Cake, UUID> {
    Optional<Cake> findByType(String type);
}
