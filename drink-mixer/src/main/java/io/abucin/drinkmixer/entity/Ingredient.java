package io.abucin.drinkmixer.entity;

import io.abucin.drinkmixer.enums.EIngredientType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Type is mandatory")
    @Enumerated(EnumType.STRING)
    private EIngredientType type;
    @ManyToMany(mappedBy = "ingredients")
    private Set<Drink> drinks = new HashSet<>();

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

    public EIngredientType getType() {
        return this.type;
    }

    public void setType(EIngredientType type) {
        this.type = type;
    }
}
