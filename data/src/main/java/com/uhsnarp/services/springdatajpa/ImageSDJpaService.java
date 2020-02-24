package com.uhsnarp.services.springdatajpa;

import com.uhsnarp.model.CategoryBO;
import com.uhsnarp.model.ImageBO;
import com.uhsnarp.repositories.CategoryRepository;
import com.uhsnarp.repositories.ImageRepository;
import com.uhsnarp.services.CategoryService;
import com.uhsnarp.services.ImageService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ImageSDJpaService implements ImageService {

    private final ImageRepository imageRepository;

    public ImageSDJpaService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Set<ImageBO> findAll() {
        Set<ImageBO> images = new HashSet<>();
        imageRepository.findAll().forEach(images::add);
        return images;
    }

    @Override
    public ImageBO findById(Integer id) {
        Optional<ImageBO> optionalImage = imageRepository.findById(id);
        return optionalImage.orElse(null);
    }

    @Override
    public ImageBO save(ImageBO object) {
        return imageRepository.save(object);
    }

    @Override
    public void delete(ImageBO object) {
        imageRepository.delete(object);
    }

    @Override
    public void deleteById(Integer id) {
        imageRepository.deleteById(id);
    }
}
