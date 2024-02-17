package com.base.services;

import com.amazonaws.services.s3.event.S3EventNotification;

public interface IImageDataService {
    void addImageData(S3EventNotification.S3EventNotificationRecord record);
}
