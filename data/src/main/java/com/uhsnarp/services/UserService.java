package com.uhsnarp.services;

import com.uhsnarp.model.UserBO;

import java.util.List;

public interface UserService extends CrudService<UserBO, Integer>{
    UserBO findByName(String name);

    List<UserBO> findAllByNameLike(String name);

    UserBO findByBusiness(Integer businessId);
}
