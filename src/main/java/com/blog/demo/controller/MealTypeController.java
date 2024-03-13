package com.blog.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.model.MealType;
import com.blog.demo.service.MealTypeServiceImpl;

@RestController
@RequestMapping("/mealtypes")
public class MealTypeController {
    
    @Autowired
    private MealTypeServiceImpl mealTypeService;

    @GetMapping("/")
    public List<MealType> getAllMealTypes() {
        return mealTypeService.getAllMealTypes();
    }
}
