package com.blog.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.model.Recipe;
import com.blog.demo.service.RecipeService;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
    
}
