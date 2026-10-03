package io.abucin.drinkmixer.repository;

import io.abucin.drinkmixer.drinks.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDrinkRepository extends JpaRepository<Drink, Long> {

}
