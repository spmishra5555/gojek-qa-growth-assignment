package io.gojek.tests;

import io.gojek.model.response.PaginatedUserResponse;
import io.gojek.utils.DataProviderFactory;
import io.gojek.utils.FileUtils;
import io.gojek.utils.RestClient;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompareUserResponseTest {

    static Logger logger = LoggerFactory.getLogger(CompareUserResponseTest.class.getName());

    private List<String> csvResponse = new ArrayList<String>();

    @Test(dataProvider = "urls", dataProviderClass = DataProviderFactory.class, description = "Compare both the responses")
    public void testResponse(String url1, String url2) {

        Response response1 = RestClient.getResponse(url1);
        Response response2 = RestClient.getResponse(url2);

        boolean isEqual = compareResponse(response1, response2);

        String[] args = {url1, isEqual ? "equals" : "not equals", url2};

        logger.info(String.join(" ", args));

        csvResponse.add(String.join(",", args));//To update the csv file with output
    }

    /**
     * This method is to compare the responses of both the urls of respective files
     */
    private boolean compareResponse(Response response1, Response response2) {
        boolean isEqual = response1.getStatusCode() == response2.getStatusCode();

        if (isEqual) {
            PaginatedUserResponse paginatedUserResponse1 = FileUtils.jsonToPojo(response1.getBody().asString(),
                    PaginatedUserResponse.class);
            PaginatedUserResponse paginatedUserResponse2 = FileUtils.jsonToPojo(response2.getBody().asString(),
                    PaginatedUserResponse.class);


            isEqual = 0 == paginatedUserResponse1.compareTo(paginatedUserResponse2);

        }
        return isEqual;
    }

    @AfterClass(description = "To print the result in CSV file")
    public void printResponseInCSV() throws IOException {

        File file = new File("Result.csv");//Name of the file

        try (FileWriter writer = new FileWriter(file)) {
            writer.append("URL from the 1st file,IsEqual,URL from the 2nd file" + System.lineSeparator());
            for (int i = 0; i < csvResponse.size(); i++) {
                try {
                    writer.append(csvResponse.get(i) + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try (InputStream stream = new FileInputStream(file)) {
            Allure.getLifecycle().addAttachment("Result", "text/csv", ".csv", stream);
        }
    }
}
