package com.data.services.springdatajpa;

import com.data.model.ProductBO;
import com.data.repositories.CategoryRepository;
import com.data.repositories.UserRepository;
import com.data.repositories.ProductRepository;
import com.data.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ProductSDJpaService implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductSDJpaService(ProductRepository productRepository, UserRepository userRepository,
                               CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<ProductBO> findAll() {
        Set<ProductBO> productBOS = new HashSet<>();
        productRepository.findAll().forEach(productBOS::add);
        return productBOS;
    }

    @Override
    public ProductBO findById(Integer id) {
        Optional<ProductBO> optionalBusiness = productRepository.findById(id);
        return optionalBusiness.orElse(null);
    }

    @Override
    public ProductBO save(ProductBO object) {
        return productRepository.save(object);
    }

    @Override
    public void delete(ProductBO object) {
        productRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
