package com.blog.demo.service;

import java.util.List;
import com.blog.demo.model.MealType;

public interface IMealTypeService {
    MealType getMealTypeById(long id);

    MealType createMealType(MealType mealType);

    MealType updateMealType(long id, MealType mealType);

    void deleteMealType(long id);

    List<MealType> getAllMealTypes();
}
