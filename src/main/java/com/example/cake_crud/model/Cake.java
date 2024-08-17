package com.example.cake_crud.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "cakes")
@AllArgsConstructor
@NoArgsConstructor
public class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String type;

    private String frosting;

    private String frostingColor;

    private Integer layers;

    private Boolean hasTopping;

    private Boolean hasFruit;

    private Boolean isTiered;

    public Cake(String type, String frosting, String frostingColor, Integer layers, Boolean hasTopping, Boolean hasFruit, Boolean isTiered) {
        this.type = type;
        this.frosting = frosting;
        this.frostingColor = frostingColor;
        this.layers = layers;
        this.hasTopping = hasTopping;
        this.hasFruit = hasFruit;
        this.isTiered = isTiered;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrosting() {
        return frosting;
    }

    public void setFrosting(String frosting) {
        this.frosting = frosting;
    }

    public String getFrostingColor() {
       return frostingColor;
    }

    public void setFrostingColor(String frostingColor) {
        this.frostingColor = frostingColor;
    }

    public Integer getLayers() {
        return layers;
    }

    public void setLayers(Integer layers) {
        this.layers = layers;
    }

    public Boolean getHasTopping() {
        return hasTopping;
    }

    public void setHasTopping(Boolean hasTopping) {
        this.hasTopping = hasTopping;
    }

    public Boolean getHasFruit() {
        return hasFruit;
    }

    public void setHasFruit(Boolean hasFruit) {
        this.hasFruit = hasFruit;
    }

    public Boolean getIsTiered() {
        return isTiered;
    }

    public void setIsTiered(Boolean isTiered) {
        this.isTiered = isTiered;
    }

}
