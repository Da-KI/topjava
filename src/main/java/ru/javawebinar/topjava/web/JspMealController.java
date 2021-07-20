package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {

    private final MealService service;

    public JspMealController(MealService service) {
        this.service = service;
    }

    @GetMapping("")
    public String getMeals(Model model) {
        model.addAttribute("meals", MealsUtil.getTos(service.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserCaloriesPerDay()));
        return "meals";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        service.delete(getId(request), SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    /*
        @PostMapping({"/update", "/create"})
        public String update(HttpServletRequest request) {
            final Meal meal = new Meal(
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt("calories"));
            if (request.getParameter("id") == null) {
                service.create(meal, SecurityUtil.authUserId());
            } else {
                service.update(meal, SecurityUtil.authUserId());
            }
            return "redirect:/meals";
        }
    */

    @PostMapping({"","/update", "/create"})
    public String update(HttpServletRequest request) {
        final Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (StringUtils.hasLength(request.getParameter("id"))) {
            service.update(meal, SecurityUtil.authUserId());
        } else {
            service.create(meal, SecurityUtil.authUserId());
        }
        return "redirect:/meals";
    }

    @GetMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Meal meal = service.get(getId(request), SecurityUtil.authUserId());
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/WEB-INF/jsp/mealForm.jsp").forward(request, response);
    }

    @GetMapping( "/create")
    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("action", "create");
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/WEB-INF/jsp/mealForm.jsp").forward(request, response);
    }

    /*

    @GetMapping({"/update", "/create"})
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Meal meal;
        if (StringUtils.hasLength(request.getParameter("id"))) {
            meal = service.get(getId(request), SecurityUtil.authUserId());
        } else {
            meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/WEB-INF/jsp/mealForm.jsp").forward(request, response);
    }

 @PostMapping("")
    public String goBack() {
        return "redirect:/meals";
    }
    @PostMapping("/create")
    public String create(HttpServletRequest request) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/mealForm.jsp");
        return "redirect:/meals";
    }

        @PostMapping("")
        public String goBack() {
            return "redirect:/meals";
        }


        @GetMapping("/update")
        public String update(HttpServletRequest request) {
            final Meal meal = service.get(getId(request), SecurityUtil.authUserId());
            request.setAttribute("meal", meal);
            return "mealForm";
        }

            @PostMapping("")
            public String goBack() {
                return "redirect:/meals";
            }
        */
    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
