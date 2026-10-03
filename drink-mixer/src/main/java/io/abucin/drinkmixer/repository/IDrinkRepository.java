package io.abucin.drinkmixer.repository;

import io.abucin.drinkmixer.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByNameStartingWith(String name);
}
