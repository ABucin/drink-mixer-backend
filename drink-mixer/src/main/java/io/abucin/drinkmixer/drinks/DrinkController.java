package io.abucin.drinkmixer.drinks;

import io.abucin.drinkmixer.repository.IDrinkRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class DrinkController {

    private final IDrinkRepository repository;

    @Autowired
    public DrinkController(IDrinkRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/drinks/{id}")
    public Optional<Drink> drink(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @GetMapping("/drinks")
    public List<Drink> drinks() {
        return this.repository.findAll();
    }

    @PostMapping("/drinks")
    public ResponseEntity<Drink> drink(@Valid @RequestBody Drink drink) {

        this.repository.save(drink);

        return ResponseEntity.ok(drink);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return errors;
    }
}
