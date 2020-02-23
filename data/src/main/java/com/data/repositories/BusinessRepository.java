package com.data.repositories;

import com.data.model.BusinessBO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BusinessRepository extends CrudRepository<BusinessBO, Integer> {
    BusinessBO findByName(String name);
    List<BusinessBO> findAllByNameLike(String lastName);
}
