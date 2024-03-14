package com.blog.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.demo.model.MealType;
import com.blog.demo.repository.IMealTypeRepository;

@Service
public class MealTypeServiceImpl implements IMealTypeService {
    @Autowired
    private IMealTypeRepository mealTypeRepository;
    
    @Override
    public MealType getMealTypeById(long id) {
        return mealTypeRepository.findById(id).orElse(null);
    }

    @Override
    public MealType createMealType(MealType mealType) {
        MealType mealTypeCreate = mealTypeRepository.findByName(mealType.getName());
        if (mealTypeCreate == null) {
            return mealTypeRepository.save(mealType);
        }
        return null;
    }

    @Override
    public MealType updateMealType(long id, MealType mealType) {
        if(mealTypeRepository.existsById(id)) {
            mealType.setId(id);
            return mealTypeRepository.save(mealType);
        }
        return null;
    }

    @Override
    public void deleteMealType(long id) {
        mealTypeRepository.deleteById(id);
    }

    @Override
    public List<MealType> getAllMealTypes() {
        return mealTypeRepository.findAll();
    }
}
