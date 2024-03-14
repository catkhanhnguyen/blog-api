package com.blog.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blog.demo.model.Recipe;
import com.blog.demo.model.Tag;
import com.blog.demo.model.MealType;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByTags(Tag tag);

    List<Recipe> findByMealTypes(MealType mealType);
}
