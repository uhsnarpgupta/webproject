package com.data.services;

import com.data.model.UserBO;

import java.util.List;

public interface UserService extends CrudService<UserBO, Integer>{
    UserBO findByFullName(String lastName);

    List<UserBO> findAllByFullNameLike(String lastName);
}
