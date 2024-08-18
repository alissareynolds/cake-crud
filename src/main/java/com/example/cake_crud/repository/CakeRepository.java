package com.example.cake_crud.repository;

import com.example.cake_crud.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CakeRepository extends JpaRepository<Cake, UUID> {
    List<Cake> findByType(String type);
}
