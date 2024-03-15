package com.blog.demo.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeRequest {

    private String name;

    private String ingredients;

    private String instructions;

    private int prepTimeMinutes;

    private int cookTimeMinutes;

    private int servings;

    private String difficulty;

    private String cuisine;

    private int caloriesPerServing;

    private long userId;

    private String image;

    private float rating;

    private int reviewCount;

    private List<Long> tagIds;

    private List<Long> mealTypeIds;

}

