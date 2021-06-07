package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");


        final int caloriesPerDay = 2000;

        List<MealTo> mealsTos = MealsUtil.filteredByStreams(MealsUtil.hardcoreMeals(), LocalTime.of(0, 0), LocalTime.of(23, 59), caloriesPerDay);

        request.setAttribute("meals", mealsTos);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }
}
