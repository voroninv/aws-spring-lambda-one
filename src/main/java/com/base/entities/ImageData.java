package com.base.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@DynamoDBTable(tableName = "images")
public class ImageData {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute
    private String key;
    @DynamoDBAttribute
    private String bucketName;
    @DynamoDBAttribute
    private String eventName;
    @DynamoDBAttribute
    private String principalId;
    @DynamoDBAttribute
    private String eTag;
    @DynamoDBAttribute
    private String service;
}
