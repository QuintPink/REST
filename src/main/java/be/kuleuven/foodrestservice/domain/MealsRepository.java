package be.kuleuven.foodrestservice.domain;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class MealsRepository {
    // map: id -> meal
    private static final Map<String, Meal> meals = new HashMap<>();
    int simple_id_tracker = 1;

    @PostConstruct
    public void initData() {

        Meal a = new Meal();
        a.setId("5268203c-de76-4921-a3e3-439db69c462a");
        a.setName("Steak");
        a.setDescription("Steak with fries");
        a.setMealType(MealType.MEAT);
        a.setKcal(1100);
        a.setPrice((10.00));

        meals.put(a.getId(), a);

        Meal b = new Meal();
        b.setId("4237681a-441f-47fc-a747-8e0169bacea1");
        b.setName("Portobello");
        b.setDescription("Portobello Mushroom Burger");
        b.setMealType(MealType.VEGAN);
        b.setKcal(637);
        b.setPrice((7.00));

        meals.put(b.getId(), b);

        Meal c = new Meal();
        c.setId("cfd1601f-29a0-485d-8d21-7607ec0340c8");
        c.setName("Fish and Chips");
        c.setDescription("Fried fish with chips");
        c.setMealType(MealType.FISH);
        c.setKcal(950);
        c.setPrice(5.00);

        meals.put(c.getId(), c);
    }

    public Optional<Meal> findMeal(String id) {
        Assert.notNull(id, "The meal id must not be null");
        Meal meal = meals.get(id);
        return Optional.ofNullable(meal);
    }

    public Collection<Meal> getAllMeal() {
        return meals.values();
    }

    public Meal getCheapestMeal() {
        Meal current_lowest_meal = new Meal();
        double current_lowest_price = 0;
        for (Meal meal: meals.values()) {
            if (meal.getPrice() < current_lowest_price) {
                current_lowest_meal = meal;
                current_lowest_price = meal.getPrice();
            }
        }
        return current_lowest_meal;
    }

    public Meal getLargestMeal() {
        Meal current_largest_meal = new Meal();
        int current_highest_kcal = 0;
        for (Meal meal: meals.values()) {
            if (meal.getKcal() > current_highest_kcal) {
                current_largest_meal = meal;
                current_highest_kcal = meal.getKcal();
            }
        }
        return current_largest_meal;
    }

    public Meal addMeal(Meal meal) {
        String oldId = Integer.toString(simple_id_tracker);
        meal.setId(oldId);
        meals.put(oldId,meal);
        simple_id_tracker += 1;
        // En eig moet als de meal al bestaat er gewoon niks gebeuren ma eh fuck da
        return meals.get(oldId);
    }

    public void updateMeal(Meal meal, String id) {
        meals.put(id,meal);

    }

    public Meal deleteMeal(String id) {
        return meals.remove(id);
    }

}
