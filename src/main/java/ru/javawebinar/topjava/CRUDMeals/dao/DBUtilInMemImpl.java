package ru.javawebinar.topjava.CRUDMeals.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DBUtilInMemImpl implements DBUtil {

    private static Map<AtomicInteger, Meal> mealsDB  = new ConcurrentHashMap<>();

    @Override
    public void createMeal(Meal meal) {
        mealsDB.putIfAbsent(meal.getId(), meal);
    }
    @Override
    public Meal getMeal(AtomicInteger id) {
        return mealsDB.get(id);
    }
    @Override
    public void editMeal(Meal meal) {
        mealsDB.replace(meal.getId(), meal);
    }
    @Override
    public void deleteMeal(Meal meal) {
        mealsDB.remove(meal.getId());
    }
}
