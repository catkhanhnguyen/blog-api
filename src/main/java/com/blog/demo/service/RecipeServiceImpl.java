package com.blog.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.demo.model.Recipe;
import com.blog.demo.model.Tag;
import com.blog.demo.dto.RecipeRequest;
import com.blog.demo.model.MealType;
import com.blog.demo.repository.IMealTypeRepository;
import com.blog.demo.repository.IRecipeRepository;
import com.blog.demo.repository.ITagRepository;

@Service
public class RecipeServiceImpl implements IRecipeServiceImpl {

    @Autowired
    private IRecipeRepository recipeRepository;

    @Autowired
    private ITagRepository tagRepository;

    @Autowired
    private IMealTypeRepository mealTypeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        return recipe;
    }

    @Override
    public Recipe createRecipe(RecipeRequest request) {
        Recipe recipe = new Recipe();

        recipe.setName(request.getName());
        recipe.setIngredients(request.getIngredients());
        recipe.setInstructions(request.getInstructions());
        recipe.setPrepTimeMinutes(request.getPrepTimeMinutes());
        recipe.setCookTimeMinutes(request.getCookTimeMinutes());
        recipe.setServings(request.getServings());
        recipe.setDifficulty(request.getDifficulty());
        recipe.setCuisine(request.getCuisine());
        recipe.setCaloriesPerServing(request.getCaloriesPerServing());
        recipe.setUserId(request.getUserId());
        recipe.setImage(request.getImage());
        recipe.setRating(request.getRating());
        recipe.setReviewCount(request.getReviewCount());

        List<Tag> tags = new ArrayList<>();
        for (Long tagId : request.getTagIds()) {
            Tag tag = tagRepository.findById(tagId).orElse(null);
            if (tag != null) {
                tags.add(tag);
            }
        }
        List<MealType> mealTypes = new ArrayList<>();
        for (Long mealTypeId : request.getMealTypeIds()) {
            MealType mealType = mealTypeRepository.findById(mealTypeId).orElse(null);
            if (mealType != null) {
                mealTypes.add(mealType);
            }
        }

        recipe.setTags(tags);
        recipe.setMealTypes(mealTypes);

        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(long id, RecipeRequest request) {
        Recipe existedRecipe = getRecipeById(id);
        if (existedRecipe != null) {
            existedRecipe.setName(request.getName());
            existedRecipe.setIngredients(request.getIngredients());
            existedRecipe.setInstructions(request.getInstructions());
            existedRecipe.setPrepTimeMinutes(request.getPrepTimeMinutes());
            existedRecipe.setCookTimeMinutes(request.getCookTimeMinutes());
            existedRecipe.setServings(request.getServings());
            existedRecipe.setDifficulty(request.getDifficulty());
            existedRecipe.setCuisine(request.getCuisine());
            existedRecipe.setCaloriesPerServing(request.getCaloriesPerServing());
            existedRecipe.setUserId(request.getUserId());
            existedRecipe.setImage(request.getImage());
            existedRecipe.setRating(request.getRating());
            existedRecipe.setReviewCount(request.getReviewCount());

            List<Tag> tags = new ArrayList<>();
            for (Long tagId : request.getTagIds()) {
                Tag tag = tagRepository.findById(tagId).orElse(null);
                if (tag != null) {
                    tags.add(tag);
                }
            }

            List<MealType> mealTypes = new ArrayList<>();
            for (Long mealTypeId : request.getMealTypeIds()) {
                MealType mealType = mealTypeRepository.findById(mealTypeId).orElse(null);
                if (mealType != null) {
                    mealTypes.add(mealType);
                }
            }

            existedRecipe.setTags(tags);
            existedRecipe.setMealTypes(mealTypes);

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
