package com.framework.testcases;

import com.framework.core.apiServices.ApiServices;
import com.framework.endpoints.CommonEndpoints;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

public class CategoryTestcases extends BaseTest {


    ApiServices apiService = new ApiServices();
    SoftAssert softAssert = new SoftAssert();
    public Logger BASE_LOGGER = Logger.getLogger(CategoryTestcases.class);
    public Response response;
    int count = 5;


    @Test(description = "Fetching all the categories from zomato")
    public void checkingTotalCategories() throws Exception {

        response = apiService.getCategoriesInZomato(CommonEndpoints.Zomato_Categories, "user-key", prop.getProperty("Api-key"));
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        int totalcategories = jsonObject.getJSONArray("categories").length();
        softAssert.assertEquals(13, totalcategories, " Total categories count in the respponse matches the actual categories ");
        softAssert.assertEquals(200, response.getStatusCode(), " Successful Response");
        softAssert.assertAll();

    }

    @Test(description = "Verifying the categories with invalid header")
    public void verifyingCategoriesWithInvalidHeader() throws Exception {
        response = apiService.getCategoriesInZomato(CommonEndpoints.Zomato_Categories, "user-keey", prop.getProperty("Api-key"));
        softAssert.assertEquals(403, response.getStatusCode(), " Invlaid Api-Key");
        softAssert.assertEquals("Forbidden", response.jsonPath().getString("status"), " It is showing Forbidden status");
        softAssert.assertAll();

    }

    @Test(description = "Verifying the categories with invalid api-key")
    public void verifyingCategoriesWithInvalidApiKey() throws Exception {
        response = apiService.getCategoriesInZomato(CommonEndpoints.Zomato_Categories, "user-key", prop.getProperty("invalid-Key"));
        softAssert.assertEquals("Invalid API Key", response.jsonPath().getString("message"), " Invalid API key");
        softAssert.assertAll();
    }


    @Test(description = " Verifying the cities api without passing any query")
    public void checkingCitiesWithoutAnyQuery() throws Exception {

        response = apiService.getCategoriesInZomato(CommonEndpoints.Zomato_Cities, "user-key", prop.getProperty("Api-key"));
        JSONObject obj1 = new JSONObject(response.getBody().asString());
        int length = obj1.getJSONArray("location_suggestions").length();
        softAssert.assertEquals(length, 0);
        softAssert.assertEquals("success", response.jsonPath().getString("status"));
        softAssert.assertAll();

    }

    @Test(description = " Verifying the cities api without passing any api key")
    public void checkingCitiesWithoutAnyApikey() throws Exception {

        response = apiService.getCategoriesInZomato(CommonEndpoints.Zomato_Cities, "user-key", prop.getProperty("invalid-Key"));
        softAssert.assertEquals("Invalid API Key", response.jsonPath().getString("message"), " Invalid API key");
        softAssert.assertEquals(403, response.getStatusCode());
        softAssert.assertEquals("Forbidden", response.jsonPath().getString("status"), " It is showing Forbidden status");
        softAssert.assertAll();

    }

    @Test(description = "Searching the cities")
    public void searchingCities() throws Exception {
        response = apiService.getCitiesInZomato(CommonEndpoints.Zomato_Cities, "user-key", prop.getProperty("Api-key"), "Indore");
        JSONObject obj1 = new JSONObject(response.getBody().asString());
        String expectedcitynamedetail = (String) obj1.getJSONArray("location_suggestions").getJSONObject(0).get("name");
        softAssert.assertEquals(expectedcitynamedetail, "Indore");
        softAssert.assertEquals(obj1.getJSONArray("location_suggestions").getJSONObject(0).get("country_name"), "India");
        softAssert.assertAll();

    }

    @Test(description = "Searching multiple cities")
    public void searchingMultipleCities() throws Exception {

        response = apiService.getCitiesInZomato(CommonEndpoints.Zomato_Cities, "user-key", prop.getProperty("Api-key"), "bengaluru,mumbai,indore");
        JSONObject obj1 = new JSONObject(response.getBody().asString());
        ArrayList<Object> citylist = new ArrayList<Object>();
        int length = obj1.getJSONArray("location_suggestions").length();
        for (int i = 0; i < length; i++) {
            citylist.add(obj1.getJSONArray("location_suggestions").getJSONObject(i).get("name"));
            softAssert.assertEquals(response.jsonPath().get("location_suggestions[" + i + "].name"), citylist.get(i));
            softAssert.assertEquals(obj1.getJSONArray("location_suggestions").getJSONObject(i).get("country_name"), "India");
        }
        softAssert.assertAll();
    }

    @Test(description = "Counting the total city ")
    public void searchingMultipleCitiesWithRestrictCount() throws Exception {

        response = apiService.getCitiesInZomatowithcount(CommonEndpoints.Zomato_Cities, "user-key", prop.getProperty("Api-key"), "bengaluru,mumbai,indore,jaipur,pune", count);
        JSONObject obj1 = new JSONObject(response.getBody().asString());
        int length = obj1.getJSONArray("location_suggestions").length();
        softAssert.assertEquals(length, count);
        softAssert.assertAll();

    }

    @Test(description = "Search by cityId's ")
    public void searchByCityIds() throws Exception {

        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Cities, "user-key", prop.getProperty("Api-key"), "city_ids", 4);
        softAssert.assertEquals(response.jsonPath().get("location_suggestions[0].name"), "Bengaluru");
        softAssert.assertAll();
    }


    @Test(description = "Searching collection on the basis of cityid")
    public void searcCollectionByInvaliadQueryParam() throws Exception {
        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Collection, "user-key", prop.getProperty("Api-key"), "null", 3);

        softAssert.assertEquals(400, response.getStatusCode());
        softAssert.assertEquals(response.jsonPath().get("message"), "Invalid or missing parameter \"city_id\"");
        softAssert.assertAll();
    }

    @Test(description = "Searching collection on the basis of cityid")
    public void searchCollectionByValidQueryParam() throws Exception {
        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Collection, "user-key", prop.getProperty("Api-key"), "city_id", 3);
        softAssert.assertEquals(200, response.statusCode());
        softAssert.assertAll();
    }

    @Test(description = "Searching collection on the basis of cityid and restricitng it by count")
    public void searchCollectionByCountand() throws Exception {
        response = apiService.getCitiesByIdAndResctrictCount(CommonEndpoints.Zomato_Collection, "user-key", prop.getProperty("Api-key"), 3, count);
        JSONObject obj1 = new JSONObject(response.getBody().asString());
        int length = obj1.getJSONArray("collections").length();
        softAssert.assertEquals(length, count);
        softAssert.assertAll();

    }

    @Test(description = "Searching collection on the basis of invalid cityid ")
    public void searchCollectionWithInvalidCityid_1() throws Exception {
        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Collection, "user-key", prop.getProperty("Api-key"), "city_id", -4);
        softAssert.assertEquals(response.statusCode(), 200, "Bug in the API It should not accept negative values");
        softAssert.assertAll();
    }

    @Test(description = "Searching collection on the basis of invalid cityid ")
    public void searchCollectionWithInvalidCityid_2() throws Exception {
        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Collection, "user-key", prop.getProperty("Api-key"), "city_id", 4 / 21);
        softAssert.assertEquals(response.statusCode(), 400, "Bug in the API It should not accept These invalid values");
        softAssert.assertAll();
    }

    @Test(description = "Searching Establishments on the basis of valid cityid ")
    public void searchEstablishmentsWithvalidCityid() throws Exception {
        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Establishments, "user-key", prop.getProperty("Api-key"), "city_id", 3);
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertAll();
    }

    @Test(description = "Checking the Basic Information of restaurant")

    public void checkBasicInfoOfRestaurant() throws Exception {
        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Restaurants, "user-key", prop.getProperty("Api-key"), "res_id", 5411);
        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertAll();
    }

    @Test(description = "Checking the Detailed Information of restaurant")
    public void checkDetailedInfoOfRestaurant() throws Exception {
        response = apiService.getCityNameByCityId(CommonEndpoints.Zomato_Restaurants, "user-key", prop.getProperty("Api-key"), "res_id", 5411);
        softAssert.assertEquals(response.statusCode(), 200);
        String res_id = response.jsonPath().get("R.res_id").toString();
        String id = response.jsonPath().get("id").toString();
        softAssert.assertEquals(res_id, id);
        softAssert.assertAll();
    }


}

