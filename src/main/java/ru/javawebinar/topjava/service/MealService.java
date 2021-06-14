package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Collection<Meal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    public Collection<Meal> getBetweenDates(LocalDate startDate, LocalDate  endDate, Integer userId) {
        return repository.getBetweenDates(startDate, endDate, userId);
    }

    public Meal get(Integer mealId, Integer userId) { return repository.get(mealId, userId); }

    public Meal create(Meal meal, Integer userId) { return repository.save(meal, userId); }

    public void delete(Integer mealId, Integer userId) { repository.delete(mealId, userId); }

    public void update(Meal meal, Integer userId) { repository.save(meal, userId); }
}