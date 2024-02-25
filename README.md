## aws-spring-lambda-one
A Java AWS Lambda function implmented as Spring Cloud Function and designed to effortlessly respond to AWS S3 events and store data into DynamoDB using the AWS SDK.

<br/>

## Spring Cloud Function
Spring Cloud Function simplifies development of serverless-style functions using Spring Framework. 
It provides programming model for building stateless functions as microservices, making it easier to deploy and scale them in a serverless architecture.

## Invoking Lambda with S3 events
<picture>
 <source media="(prefers-color-scheme: dark)" srcset="https://docs.aws.amazon.com/images/lambda/latest/dg/images/services-s3-example/s3_tut_config.png">
 <img alt="YOUR-ALT-TEXT" src="https://docs.aws.amazon.com/images/lambda/latest/dg/images/services-s3-example/s3_tut_config.png">
</picture>

## Guide:
- create db entity with attributes and annotations;
- create CRUD repository and don't forget @EnableScan annotation;
- configure AmazonDynamoDB and AmazonS3 clients with @EnableDynamoDBRepositories annotation;
- create function and mark it with @Component annotation;
- build and upload jar to AWS Lambda;
- update handler field with org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest;
- configure AWS S3 trigger for the lambda function.

## Libraries:
- spring cloud function;
- spring cloud function adapter;
- aws java sdk;
- derjust spring data dynamodb;
- log4j;
- lombok.
