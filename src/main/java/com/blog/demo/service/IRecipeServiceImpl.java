package com.blog.demo.service;

import java.util.List;

import com.blog.demo.model.MealType;
import com.blog.demo.model.Recipe;
import com.blog.demo.model.Tag;

public interface IRecipeServiceImpl {
    List<Recipe> getAllRecipes();

    Recipe getRecipeById(long id);

    Recipe createRecipe(Recipe recipe);

    Recipe updateRecipe(long id, Recipe recipe);

    void deleteRecipe(long id);

    List<Recipe> findRecipesByTag(Tag tag);

    List<Recipe> findRecipesByMealType(MealType mealType);

}
