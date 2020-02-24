package com.uhsnarp.repositories;

import com.uhsnarp.model.UserBO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserBO, Integer> {
    UserBO findByName(String name);

    List<UserBO> findAllByNameLike(String lastName);

    UserBO findByBusiness(Integer businessId);
}
