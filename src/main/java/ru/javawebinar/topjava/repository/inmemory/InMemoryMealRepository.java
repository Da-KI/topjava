package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        if(!Objects.equals(meal.getUserId(), userId)) { return null; }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, Integer userId) {
        if(id != userId) { return false; }
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, Integer userId) {
        if(id != userId) { return null; }
        return repository.getOrDefault(id, null);
    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return getBetweenDates(LocalDate.MIN, LocalDate.MAX, userId);
    }

    @Override
    public Collection<Meal> getBetweenDates(LocalDate startDate, LocalDate endDate, Integer userId) {
        List<Meal> usersMealsBetweenDates = new ArrayList<>();
        for (Meal meal: repository.values()) {
            if (meal.getUserId().equals(userId) && DateTimeUtil.isBetweenHalfOpen(meal.getDate(), startDate, endDate)) {
                usersMealsBetweenDates.add(meal);
            }
        }
        usersMealsBetweenDates.sort(Collections.reverseOrder(Meal.COMPARE_BY_DATE));

        return usersMealsBetweenDates;
    }
}

