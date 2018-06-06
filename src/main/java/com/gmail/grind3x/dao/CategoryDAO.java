package com.gmail.grind3x.dao;

import com.gmail.grind3x.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAll();

    void insert(Category category);

    void delete(Category category);

    Category findByName(String name);

    Category findById(Long id);

    void update(Category category);
}
