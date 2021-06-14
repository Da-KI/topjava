package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    public MealRestController(MealService service) { this.service = service; }

    public List<MealTo> getAll() {
        log.info("getAll");
        return MealsUtil.getTos(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public List<MealTo> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getBetween {} {}, {} {}", startDate, startTime, endDate, endTime);
        return MealsUtil.getFilteredTos(
                service.getBetweenDates(startDate, endDate, authUserId()),
                authUserCaloriesPerDay(),
                startTime,
                endTime);
    }

    public Meal get(Integer id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public void delete(Integer id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal, Integer id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal, authUserId());
    }

}