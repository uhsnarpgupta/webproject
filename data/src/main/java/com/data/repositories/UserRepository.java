package com.data.repositories;

import com.data.model.UserBO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserBO, Integer> {
    UserBO findByFullName(String name);

    List<UserBO> findAllByFullNameLike(String lastName);
}
