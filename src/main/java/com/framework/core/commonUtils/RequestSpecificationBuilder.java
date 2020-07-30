package com.framework.core.commonUtils;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class RequestSpecificationBuilder {


    public static Map<String, Object> pathparams = new HashMap<String, Object>();
    public static Map<String, Object> queryparams = new HashMap<String, Object>();
    private final Logger BASE_LOGGER = Logger.getLogger(RequestSpecificationBuilder.class);
    Map<String, Object> headers = new HashMap<String, Object>();

    public Map<String, Object> getPathparams() {
        return pathparams;
    }

    public Map<String, Object> getQueryparams() {
        return queryparams;
    }

    public Map<String, Object> getheaders() {
        return headers;
    }


    public RequestSpecificationBuilder setPathParams(String paramname, Object paramvalue) {


        try {
            if (paramname != null || paramvalue != null) {

                pathparams.put(paramname, paramvalue);
                return this;
            } else {
                BASE_LOGGER.error(" Paramname is null");
                return null;
            }

        } catch (Exception e) {
            BASE_LOGGER.error(" Execption while setting the pathparams");
            BASE_LOGGER.error(e.getCause().getMessage());
        }

        return null;
    }


    public RequestSpecificationBuilder setqueryParams(String paramname, Object paramvalue) {

        try {
            if (paramname != null || paramvalue != null) {
                queryparams.put(paramname, paramvalue);
                return this;
            } else {

                BASE_LOGGER.error(" Paramname is null ");
                return null;
            }

        } catch (Exception e) {
            BASE_LOGGER.error(" Execption while setting the pathparams");
            BASE_LOGGER.error(e.getCause().getMessage());
        }
        return null;

    }

    public RequestSpecificationBuilder setHeader(String headername, Object headervalue) {
        {

            headers.put(headername, headervalue);
            return this;


        }
    }
}




