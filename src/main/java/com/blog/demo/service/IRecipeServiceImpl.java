package com.blog.demo.service;

import java.util.List;

import com.blog.demo.dto.RecipeRequest;
import com.blog.demo.model.MealType;
import com.blog.demo.model.Recipe;
import com.blog.demo.model.Tag;

public interface IRecipeServiceImpl {
    List<Recipe> getAllRecipes();

    Recipe getRecipeById(long id);

    Recipe createRecipe(RecipeRequest request);

    Recipe updateRecipe(long id, RecipeRequest request);

    void deleteRecipe(long id);

    List<Recipe> findRecipesByTag(Tag tag);

    List<Recipe> findRecipesByMealType(MealType mealType);

}
