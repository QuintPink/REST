package be.kuleuven.foodrestservice.controllers;

import be.kuleuven.foodrestservice.domain.Meal;
import be.kuleuven.foodrestservice.domain.MealsRepository;
import be.kuleuven.foodrestservice.exceptions.MealNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class MealsRestRpcStyleController {

    private final MealsRepository mealsRepository;

    @Autowired
    MealsRestRpcStyleController(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @GetMapping("/restrpc/meals/{id}")
    Meal getMealById(@PathVariable String id) {
        Optional<Meal> meal = mealsRepository.findMeal(id);

        return meal.orElseThrow(() -> new MealNotFoundException(id));
    }

    @GetMapping("/restrpc/meals")
    Collection<Meal> getMeals() {
        return mealsRepository.getAllMeal();
    }

    @PostMapping("/restrpc/meals")
     Meal addMeal(@RequestBody Meal meal) {
        mealsRepository.addMeal(meal);
        return meal;
    }

    @PutMapping("/restrpc/meals/{id}")
    Meal updateMeal(@PathVariable String id, @RequestBody Meal meal) {
        mealsRepository.updateMeal(meal,id);
        return meal;
    }

    @DeleteMapping("/restrpc/meals/{id}")
    Meal delete(@PathVariable String id) {
        return mealsRepository.deleteMeal(id);
    }

}
