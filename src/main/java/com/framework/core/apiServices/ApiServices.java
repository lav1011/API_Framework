package com.framework.core.apiServices;

import com.framework.core.commonUtils.ApiUtils;
import com.framework.core.commonUtils.BaseSetup;
import com.framework.core.commonUtils.RequestSpecificationBuilder;
import io.restassured.response.Response;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class ApiServices extends ApiUtils {


    private final Logger BASE_LOGGER = Logger.getLogger(ApiServices.class);

    RequestSpecificationBuilder rsp = new RequestSpecificationBuilder();

    Response response;


    public Response getCategoriesInZomato(String endpoint, String headername, String apikey) throws Exception {

        try {

            rsp.setHeader(headername, apikey);

            Response response = getRequestUsingSpec(BaseSetup.baseUrl + endpoint, rsp);

            if (response != null) {
                BasicConfigurator.configure();
                BASE_LOGGER.info("Successfully retrieved the response ");
                return response;
            } else {
                BASE_LOGGER.error("Response is coming as null ");
                throw new Exception("Response is coming as null");
            }

        } catch (Exception e) {
            BASE_LOGGER.error(e.getCause().getMessage());
        }

        return response;
    }

    public Response getCitiesInZomato(String endpoint, String headername, String apikey, String citynames) throws Exception {

        try {

            rsp.setHeader(headername, apikey).setqueryParams("q", citynames);


            Response response = getRequestUsingSpec(BaseSetup.baseUrl + endpoint, rsp);

            if (response != null) {
                BasicConfigurator.configure();
                BASE_LOGGER.info("Successfully retrieved the response ");
                return response;
            } else {
                BASE_LOGGER.error("Response is coming as null ");
                throw new Exception("Response is coming as null");
            }

        } catch (Exception e) {
            BASE_LOGGER.error(e.getCause().getMessage());
        }

        return response;
    }

    public Response getCitiesInZomatowithcount(String endpoint, String headername, String apikey, String citynames, int count) throws Exception {

        try {

            rsp.setHeader(headername, apikey).setqueryParams("q", citynames).setqueryParams("count", count);


            Response response = getRequestUsingSpec(BaseSetup.baseUrl + endpoint, rsp);

            if (response != null) {
                BasicConfigurator.configure();
                BASE_LOGGER.info("Successfully retrieved the response ");
                return response;
            } else {
                BASE_LOGGER.error("Response is coming as null ");
                throw new Exception("Response is coming as null");
            }

        } catch (Exception e) {
            BASE_LOGGER.error(e.getCause().getMessage());
        }

        return response;
    }

    public Response getCityNameByCityId(String endpoint, String headername, String apikey, String Queryparam, int cityid) throws Exception {

        try {

            rsp.setHeader(headername, apikey).setqueryParams(Queryparam, cityid);

            Response response = getRequestUsingSpec(BaseSetup.baseUrl + endpoint, rsp);

            if (response != null) {
                BasicConfigurator.configure();
                BASE_LOGGER.info("Successfully retrieved the response ");
                return response;
            } else {
                BASE_LOGGER.error("Response is coming as null ");
                throw new Exception("Response is coming as null");
            }

        } catch (Exception e) {
            BASE_LOGGER.error(e.getCause().getMessage());
        }

        return response;
    }

    public Response getCitiesByIdAndResctrictCount(String endpoint, String headername, String apikey, int cityid, int count) throws Exception {

        try {

            rsp.setHeader(headername, apikey).setqueryParams("city_id", cityid).setqueryParams("count", count);


            Response response = getRequestUsingSpec(BaseSetup.baseUrl + endpoint, rsp);

            if (response != null) {
                BasicConfigurator.configure();
                BASE_LOGGER.info("Successfully retrieved the response ");
                return response;
            } else {
                BASE_LOGGER.error("Response is coming as null ");
                throw new Exception("Response is coming as null");
            }

        } catch (Exception e) {
            BASE_LOGGER.error(e.getCause().getMessage());
        }

        return response;
    }
}
