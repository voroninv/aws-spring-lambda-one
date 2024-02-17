package com.base.repositories;

import com.base.entities.ImageData;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ImageDataRepository extends CrudRepository<ImageData, Integer> {
}
