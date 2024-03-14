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
import com.blog.demo.service.MealTypeServiceImpl;

@RestController
@RequestMapping("/mealtypes")

public class MealTypeController {
    @Autowired
    private MealTypeServiceImpl mealTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<MealType> getMealTypeById(@PathVariable("id") long id) {
        MealType mealType = mealTypeService.getMealTypeById(id);
        if (mealType != null) {
            return new ResponseEntity<>(mealType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MealType> createMealType(@RequestBody MealType mealType) {
        MealType createdMealType = mealTypeService.createMealType(mealType);
        return new ResponseEntity<>(createdMealType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealType> updateMealType(@PathVariable("id") long id, @RequestBody MealType mealType) {
        MealType updatedMealType = mealTypeService.updateMealType(id, mealType);
        if (updatedMealType != null) {
            return new ResponseEntity<>(updatedMealType, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealType(@PathVariable("id") long id) {
        mealTypeService.deleteMealType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<MealType>> getAllMealType() {
        List<MealType> mealTypes = mealTypeService.getAllMealTypes();
        return new ResponseEntity<>(mealTypes, HttpStatus.OK);
    }
}
