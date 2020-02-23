package com.data.services;

import com.data.model.BusinessBO;

import java.util.List;

public interface BusinessService extends CrudService<BusinessBO, Integer> {
    BusinessBO findByName(String name);
    List<BusinessBO> findAllByNameLike(String name);
}
