package io.gojek.utils;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public class RestClient {

    //To increase the request time
    static {
        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", 30000).
                setParam("http.socket.timeout", 30000).
                setParam("http.connection-manager.timeout", 30000));
    }

    private RestClient() {

    }

    /**
     * @param url
     * @return
     */
    public static Response getResponse(String url) {

        return RestAssured.given().relaxedHTTPSValidation().accept("application/json").when().get(url).thenReturn();
    }
}
