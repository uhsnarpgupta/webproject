package com.uhsnarp.repositories;

import com.uhsnarp.model.CategoryBO;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryBO, Integer> {
    CategoryBO findByName(String name);
}
