package com.base.functions;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.base.entities.Image;
import com.base.services.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component("imageFunction")
public class ImageFunction implements Function<S3Event, S3Event> {

    @Autowired
    ImageService imageService;

    @Override
    public S3Event apply(S3Event s3Event) {
        log.info("S3 Event processing starts with records count: {}", s3Event.getRecords().size());

        s3Event.getRecords().forEach(record -> {

            String s3Bucket = record.getS3().getBucket().getName();
            String s3Key = record.getS3().getObject().getKey();
            log.info("Received record with bucket: {}  and key:  {}", s3Bucket, s3Key);

            Image image = new Image();
            image.setBucketName(record.getS3().getBucket().getName());
            image.setKey(record.getS3().getObject().getKey());

            imageService.addImage(image);
            log.info("Data saved to dynamoDB");
        });

        return s3Event;
    }
}
