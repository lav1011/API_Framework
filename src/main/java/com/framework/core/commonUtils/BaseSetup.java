package com.framework.core.commonUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.asserts.SoftAssert;


public class BaseSetup {

    static String pathOfFile = "/configration.properties";
    public static String baseUrl;
    public static Properties prop;
    public Logger BASE_LOGGER = Logger.getLogger(BaseSetup.class);


    public void urlsetup() {

        try {
            prop = new Properties();

            FileInputStream inputfile = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/" + pathOfFile + "");
            prop.load(inputfile);
            String env = prop.getProperty("Environment");

            try {
                baseUrl = urlGenerator(env);

                BASE_LOGGER.info("--- BaseUrl retrieved as : " + baseUrl + " ---");


            } catch (Exception e) {

                BASE_LOGGER.error("--- Exception occurred while performing URL setup ---");
                BASE_LOGGER.error(e.getCause().getMessage());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String urlGenerator(String env) throws Exception {

        if (null != env) {
            BASE_LOGGER.info("Env is : " + env);
            try {
                if (env.equals("staging")) {
                    String apiStagingUrl = prop.getProperty("url");
                    if (null != apiStagingUrl) {
                        return apiStagingUrl;
                    } else {
                        BASE_LOGGER.error("apiStagingUrl is set as : NULL");
                    }
                }
            } catch (Exception e) {
                BASE_LOGGER.error("--- Exception occurred while performing urlGenerator ---");
                BASE_LOGGER.error(e.getCause().getMessage());

            }
        } else {
            BASE_LOGGER.error("ENV is set as : NULL");
        }
        return null;
    }

}









