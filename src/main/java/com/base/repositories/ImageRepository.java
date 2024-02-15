package com.base.repositories;

import com.base.entities.Image;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ImageRepository extends CrudRepository<Image, Integer> {
}
