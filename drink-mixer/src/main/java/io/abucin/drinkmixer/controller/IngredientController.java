package io.abucin.drinkmixer.controller;

import io.abucin.drinkmixer.entity.Ingredient;
import io.abucin.drinkmixer.repository.IIngredientRepository;
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
public class IngredientController {
    private final IIngredientRepository repository;

    @Autowired
    public IngredientController(IIngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/ingredients/{id}")
    public Optional<Ingredient> ingredient(@PathVariable Long id) {
        return this.repository.findById(id);
    }

    @GetMapping("/ingredients")
    public List<Ingredient> ingredients(@RequestParam(required = false, defaultValue = "") String name) {
        return repository.findByNameStartingWith(name);
    }

    @PostMapping("/ingredient")
    public ResponseEntity<Ingredient> ingredient(@Valid @RequestBody Ingredient ingredient) {
        this.repository.save(ingredient);
        return ResponseEntity.ok(ingredient);
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
