package com.blog.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.model.MealType;
import com.blog.demo.model.Recipe;
import com.blog.demo.model.Tag;
import com.blog.demo.service.RecipeServiceImpl;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.createRecipe(recipe), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        if (getRecipeById(id) != null) {
            return new ResponseEntity<>(recipeService.updateRecipe(id, recipe), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") long id) {
        if (getRecipeById(id) != null) {
            recipeService.deleteRecipe(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/tags/{tagId}")
    public ResponseEntity<List<Recipe>> findRecipesByTag(@PathVariable long tagId) {
        Tag tag = new Tag();
        tag.setId(tagId);
        return new ResponseEntity<>(recipeService.findRecipesByTag(tag), HttpStatus.OK);
    }

    @GetMapping("/mealtypes/{mealtypeId}")
    public ResponseEntity<List<Recipe>> findRecipesByMealType(@PathVariable long mealtypeId) {
        MealType mealType = new MealType();
        mealType.setId(mealtypeId);
        return new ResponseEntity<>(recipeService.findRecipesByMealType(mealType), HttpStatus.OK);
    }

    
}
