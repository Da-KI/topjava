package ru.javawebinar.topjava.CRUDMeals.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.concurrent.atomic.AtomicInteger;

public interface DBUtil {
    void createMeal(Meal meal);
    Meal getMeal(AtomicInteger id);
    void editMeal(Meal meal);
    void deleteMeal(Meal meal);
}
