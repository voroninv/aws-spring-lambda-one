package com.base.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.base.entities.ImageData;
import com.base.repositories.ImageDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class ImageDataService implements IImageDataService {
    private static final List<String> CREATE_EVENTS = List.of( "ObjectCreated:Post");

    @Autowired
    ImageDataRepository imageRepository;

    @Autowired
    AmazonS3 amazonS3;

    public void addImageData(S3EventNotification.S3EventNotificationRecord record) {
        Map<String, String> userMetadata = null;

        String bucketName = record.getS3().getBucket().getName();
        String key = record.getS3().getObject().getKey();
        String eventName = record.getEventName();

        if (CREATE_EVENTS.contains(eventName)) {
            userMetadata = getMeta(bucketName, key).getUserMetadata();
        }

        imageRepository.save(
                ImageData.builder()
                        .key(key)
                        .bucketName(bucketName)
                        .eventName(eventName)
                        .principalId(record.getUserIdentity().getPrincipalId())
                        .eTag(record.getS3().getObject().geteTag())
                        .service(Objects.nonNull(userMetadata) ? userMetadata.get("service") : "")
                        .build()
        );
        log.info("Record with key:  {} saved to dynamoDB.", record.getS3().getObject().getKey());
    }

    private ObjectMetadata getMeta(String bucketName, String key) {
        log.info("Loading metadata for:  {}.", key);

        return amazonS3.getObjectMetadata(
                new GetObjectMetadataRequest(bucketName, key)
        );
    }

}
