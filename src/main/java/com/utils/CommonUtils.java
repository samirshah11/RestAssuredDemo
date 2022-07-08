package com.utils;

import com.github.javafaker.Faker;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;

public final class CommonUtils {

    private CommonUtils(){}
    public static int getRandomNumber(){
        return new Faker().number().numberBetween(1,100000);
    }

    public static String getFirstName(){
        return new Faker().name().firstName();
    }

    public static String getLastName(){
        return new Faker().name().lastName();
    }

    public static String getId() {

        Response response = given().baseUri(PropertyUtil.getPropertyValue("baseurl")).get("/EmployeeDetails");
        List<Integer> id_lst = response.path("EmpId");
        int randomNumber = CommonUtils.getRandomNumber();
        while (id_lst.contains(randomNumber)) {
            randomNumber = CommonUtils.getRandomNumber();
        }
        return String.valueOf(randomNumber);
    }

}
