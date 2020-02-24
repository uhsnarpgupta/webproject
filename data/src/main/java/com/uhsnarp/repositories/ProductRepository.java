package com.uhsnarp.repositories;

import com.uhsnarp.model.ProductBO;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductBO, Integer> {
    ProductBO findByName(String name);

    ProductBO findAllByBusiness(Integer businessCode);

    ProductBO findAllByCategory(Integer categoryCode);
}
