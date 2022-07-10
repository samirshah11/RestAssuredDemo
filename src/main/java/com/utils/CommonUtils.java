package com.utils;

import com.github.javafaker.Faker;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CommonUtils {

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
        String baseurl;
        if(System.getProperty("ApiHost")!=null){
            baseurl="http://"+System.getProperty("ApiHost")+":8082";
        }
        else {
            baseurl=PropertyUtil.getPropertyValue("baseurl");
        }

        Response response = given().baseUri(baseurl).get("/EmployeeDetails");
        List<Integer> id_lst = response.path("EmpId");
        int randomNumber = CommonUtils.getRandomNumber();
        while (id_lst.contains(randomNumber)) {
            randomNumber = CommonUtils.getRandomNumber();
        }
        return String.valueOf(randomNumber);
    }

    public String getFileContent(String file){

        String content;
        StringBuilder builder = new StringBuilder();
        int i=0;
        InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(file);
        while (true) {
            try {
                if (((i = fileInputStream.read()) != -1)) break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.print((char) i);
            builder.append((char) i);
        }
        content = builder.toString();
        return content;
    }
}
