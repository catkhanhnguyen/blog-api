package com.blog.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.demo.model.MealType;
import com.blog.demo.repository.MealTypeRepository;

@Service
public class MealTypeServiceImpl implements IMealTypeService {
    @Autowired
    private MealTypeRepository mealTypeRepository;

    @Override
    public List<MealType> getAllMealTypes() {
        return mealTypeRepository.findAll();
    }
}
