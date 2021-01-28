package com.napoleongames.test.service.core.spec;

import com.napoleongames.test.service.core.config.CommonProperties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationProvider
{
    public static RequestSpecification defaultSpec()
    {
        CommonProperties commonProperties = CommonProperties.getInstance();
        return new RequestSpecBuilder()
                .setBaseUri(commonProperties.baseUrl())
                .setPort(commonProperties.port())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
