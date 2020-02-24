package com.uhsnarp.services.springdatajpa;

import com.uhsnarp.model.BusinessBO;
import com.uhsnarp.repositories.BusinessRepository;
import com.uhsnarp.repositories.CategoryRepository;
import com.uhsnarp.services.BusinessService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class BusinessSDJpaService implements BusinessService {

    private final BusinessRepository businessRepository;
    private final CategoryRepository categoryRepository;

    public BusinessSDJpaService(BusinessRepository businessRepository,
                                CategoryRepository categoryRepository) {
        this.businessRepository = businessRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public BusinessBO findByName(String lastName) {
        return businessRepository.findByName(lastName);
    }

    @Override
    public List<BusinessBO> findAllByNameLike(String lastName) {
        return businessRepository.findAllByNameLike(lastName);
    }


    @Override
    public Set<BusinessBO> findAll() {
        Set<BusinessBO> businessBOS = new HashSet<>();
        businessRepository.findAll().forEach(businessBOS::add);
        return businessBOS;
    }

    @Override
    public BusinessBO findById(Integer id) {
        Optional<BusinessBO> optionalBusiness = businessRepository.findById(id);
        return optionalBusiness.orElse(null);
    }

    @Override
    public BusinessBO save(BusinessBO object) {
        return businessRepository.save(object);
    }

    @Override
    public void delete(BusinessBO object) {
        businessRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        businessRepository.deleteById(id);
    }
}
