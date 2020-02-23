package com.data.services.springdatajpa;

import com.data.model.CategoryBO;
import com.data.repositories.BusinessRepository;
import com.data.repositories.CategoryRepository;
import com.data.repositories.UserRepository;
import com.data.services.CategoryService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class CategorySDJpaService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    public CategorySDJpaService(CategoryRepository categoryRepository, UserRepository userRepository,
                                BusinessRepository businessRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.businessRepository = businessRepository;
    }

    @Override
    public Set<CategoryBO> findAll() {
        Set<CategoryBO> businesses = new HashSet<>();
        categoryRepository.findAll().forEach(businesses::add);
        return businesses;
    }

    @Override
    public CategoryBO findById(Integer id) {
        Optional<CategoryBO> optionalBusiness = categoryRepository.findById(id);
        return optionalBusiness.orElse(null);
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
