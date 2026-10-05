package io.abucin.drinkmixer.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "drinks")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @OneToMany()
    private List<Ingredient> ingredients;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() { return this.ingredients; }

    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
}