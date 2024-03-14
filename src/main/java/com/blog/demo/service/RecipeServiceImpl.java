package com.blog.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.demo.model.Recipe;
import com.blog.demo.model.Tag;
import com.blog.demo.model.MealType;
import com.blog.demo.repository.IRecipeRepository;

@Service
public class RecipeServiceImpl implements IRecipeServiceImpl {

    @Autowired
    private IRecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(long id) {
        return recipeRepository.findById(id).orElse(null);
    }

    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(long id, Recipe recipe) {
        Recipe existedRecipe = getRecipeById(id);
        if (existedRecipe != null) {
            existedRecipe.setName(recipe.getName());
            return existedRecipe;
        }
        return null;
    }

    @Override
    public void deleteRecipe(long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findRecipesByTag(Tag tag) {
        return recipeRepository.findByTags(tag);
    }

    @Override
    public List<Recipe> findRecipesByMealType(MealType mealType) {
        return recipeRepository.findByMealTypes(mealType);
    }
}
