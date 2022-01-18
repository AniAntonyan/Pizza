package com.service;

import com.model.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient read(int id);

    List<Ingredient> readAll();

    void create (Ingredient ingredient);

    Ingredient update(int id, Ingredient ingredient);

    void delete (int id);
}
