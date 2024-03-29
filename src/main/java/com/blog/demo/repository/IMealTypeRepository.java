package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.demo.model.MealType;

@Repository
public interface IMealTypeRepository extends JpaRepository<MealType, Long> {
    MealType findByName(String name);
}
