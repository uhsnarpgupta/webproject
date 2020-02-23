package com.data.repositories;

import com.data.constants.Media;
import com.data.model.ImageBO;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageBO, Integer> {
    ImageBO findByName(String name);

    ImageBO findAllByProduct(Integer productId);

    ImageBO findAllByType(Media type);
}
