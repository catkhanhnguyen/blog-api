package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.demo.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
