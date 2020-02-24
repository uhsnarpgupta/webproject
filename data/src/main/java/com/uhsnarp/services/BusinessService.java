package com.uhsnarp.services;

import com.uhsnarp.model.BusinessBO;

import java.util.List;

public interface BusinessService extends CrudService<BusinessBO, Integer> {
    BusinessBO findByName(String name);
    List<BusinessBO> findAllByNameLike(String name);
}
