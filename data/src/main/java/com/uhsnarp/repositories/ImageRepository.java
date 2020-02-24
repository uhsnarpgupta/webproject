package com.uhsnarp.repositories;

import com.uhsnarp.constants.Media;
import com.uhsnarp.model.ImageBO;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<ImageBO, Integer> {
    ImageBO findByName(String name);

    ImageBO findAllByProduct(Integer productId);

    ImageBO findAllByType(Media type);
}
