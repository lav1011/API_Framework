package com.framework.core.commonUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    private final Logger BASE_LOGGER = Logger.getLogger(ApiUtils.class);

    Response response;
    RequestSpecification requestSpecification;

    public RequestSpecification getRequestspecification(RequestSpecificationBuilder parameters) {

        try {

            requestSpecification = given().pathParams(parameters.getPathparams()).queryParams(parameters.getQueryparams()).contentType("application/json")
                    .headers(parameters.getheaders());
            if (null != requestSpecification) {
                BASE_LOGGER.info(" requestSpecification has valid inputs ");
                return requestSpecification;
            } else {
                BASE_LOGGER.error("--- requestSpecification retrieved as NULL ---");
            }
        } catch (Exception e) {
            BASE_LOGGER.error("--- Exception occurred while performing getRequestSpecification---");
            BASE_LOGGER.error(e.getCause().getMessage());
        }
        return null;
    }

    public Response getRequestUsingSpec(String url, RequestSpecificationBuilder build) throws Exception {

        try {
            response = given().spec(getRequestspecification(build)).get(url);

            try {
                if (response.statusCode() == 200) {
                    BASE_LOGGER.info(" Successfull response received from the api where status code is :- " + response.statusCode());
                } else {
                    BASE_LOGGER.error(" Failed reponse received from the api where status code is :- " + response.getStatusCode());
                    response.getBody().asString();
                }
            } catch (Exception e) {
                BASE_LOGGER.error(e.getCause().getMessage());
            }
        } catch (Exception e) {
            BASE_LOGGER.error(" Exception found while fetching the response ");
        }

        return response;
    }


}
