package com.base.functions;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.base.services.ImageDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Slf4j
@Component("imageFunction")
public class ImageDataFunction implements Function<S3Event, S3Event> {

    @Autowired
    ImageDataService imageDataService;

    @Override
    public S3Event apply(S3Event s3Event) {
        log.info("S3 Event processing started.");
        s3Event.getRecords().forEach(this.imageDataService::addImageData);
        log.info("S3 Event processing completed.");

        return s3Event;
    }
}
