package com.restapitests;

import com.utils.DataProviderUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetTests extends BaseTest {

    @Test(description = "This test will get the details of the employee and validate response")
    public void getEmployeeDetails() {

        Response response = given().log().all().baseUri(baseurl).get("/EmployeeDetails");
        System.out.println(response.asString());
        List<Integer> empId = response.path("EmpId");
        assertThat(empId, hasItems(1, 2));
        assertThat(response.path("Name"), hasItems("ABC", "CBD"));
        response.getHeaders().forEach(header -> {
            System.out.println(header.getName() + " ---> " + header.getValue());
        });
        assertThat(response.getHeaders().getValue("Content-Type"), is(equalTo("application/json; charset=utf-8")));

    }

    @Test(dataProvider = "getEmployeeData",dataProviderClass = DataProviderUtil.class )
    public void getEmployeeDetailsWithResource(String id,String name) {
        Response rs = given().baseUri(baseurl).pathParams("EmpId",id)
                .log().all().
                get("/EmployeeDetails"+"/{EmpId}");
        assertThat(rs.path("Name"), is(equalTo(name)));
    }

}