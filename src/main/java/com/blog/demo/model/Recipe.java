package com.blog.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "prep_time_minutes")
    private int prepTimeMinutes;

    @Column(name = "cook_time_minutes")
    private int cookTimeMinutes;

    @Column(name = "servings")
    private int servings;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "calories_per_serving")
    private int caloriesPerServing;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "image")
    private String image;

    @Column(name = "rating")
    private float rating;

    @Column(name = "review_count")
    private int reviewCount;

    @ManyToMany
    @JoinTable(name = "recipe_tags",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToMany
    @JoinTable(name = "recipe_meal_types",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "meal_type_id"))
    private List<MealType> mealTypes;
}

