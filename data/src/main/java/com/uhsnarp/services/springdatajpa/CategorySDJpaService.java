package com.uhsnarp.services.springdatajpa;

import com.uhsnarp.model.CategoryBO;
import com.uhsnarp.repositories.CategoryRepository;
import com.uhsnarp.services.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class CategorySDJpaService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategorySDJpaService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<CategoryBO> findAll() {
        Set<CategoryBO> categories = new HashSet<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public CategoryBO findById(Integer id) {
        Optional<CategoryBO> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public CategoryBO save(CategoryBO object) {
        return categoryRepository.save(object);
    }

    @Override
    public void delete(CategoryBO object) {
        categoryRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
