package ru.javawebinar.topjava.web.meal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.service.MealService;

public class AbstractMealController {

    protected static final Logger log = LoggerFactory.getLogger(MealRestController.class);
    protected final MealService service;

    public AbstractMealController(MealService service) {
        this.service = service;
    }
}
