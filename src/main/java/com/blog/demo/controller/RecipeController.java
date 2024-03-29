package com.blog.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.blog.demo.dto.RecipeRequest;
import com.blog.demo.model.MealType;
import com.blog.demo.model.Recipe;
import com.blog.demo.model.Tag;
import com.blog.demo.service.RecipeServiceImpl;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeServiceImpl recipeService;

    @GetMapping()
    public ResponseEntity<Page<Recipe>> getAllRecipes(Pageable pageable) {
        return new ResponseEntity<>(recipeService.getAllRecipes(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Recipe> createRecipe(@RequestBody RecipeRequest request) {
        Recipe createRecipe = recipeService.createRecipe(request);
        if (createRecipe != null) {
            return new ResponseEntity<>(createRecipe, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") long id, @RequestBody RecipeRequest request) {
        if (recipeService.getRecipeById(id) != null) {
            Recipe recipeUpdate = recipeService.updateRecipe(id, request);
            if (recipeUpdate != null) {
                return new ResponseEntity<>(recipeUpdate, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable("id") long id) {
        if (recipeService.getRecipeById(id) != null) {
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
