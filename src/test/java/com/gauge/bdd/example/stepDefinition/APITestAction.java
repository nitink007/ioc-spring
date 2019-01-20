package com.gauge.bdd.example.stepDefinition;

import com.github.wnameless.json.flattener.JsonFlattener;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.when;

@Component
public class APITestAction {

    @Step("User send a <method> request")
    public void sendGETRequest(String method) throws IOException {
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/users?page=2");
        System.out.println(response.getBody().asString());

        String responseString = response.asString();

//        when().get("https://reqres.in/api/users?page=2").then()



        Map<String, Object> responseMap = JsonFlattener.flattenAsMap(responseString);
        System.out.println(responseMap);

//        Workbook workbook = WorkbookFactory.create("E:\\Nitin\\Study\\Extras\\sample1.xlsx");

    }
}
