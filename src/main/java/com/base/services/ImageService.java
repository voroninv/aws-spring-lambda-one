package com.base.services;

import com.base.entities.Image;
import com.base.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService{

    @Autowired
    ImageRepository imageRepository;

    public void addImage(Image image) {
        imageRepository.save(image);
    }

}
