package com.blog.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "recipes")
public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "prepTimeMinues")
    private int prepTimeMinutes;

    @Column(name = "cookTimeMinutes")
    private int cookTimeMinutes;

    @Column(name = "servings")
    private int servings;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "caloriesPerServing")
    private int caloriesPerServing;

    @Column(name = "userId")
    private long userId;

    @Column(name = "image")
    private String image;

    @Column(name = "raing")
    private float rating;

    @Column(name = "reviewCount")
    private int reviewCount;

    

}
