package com.restapitests;

import com.pos.Address;
import com.pos.EmployeeRoot;
import com.pos.Home;
import com.pos.Shop;
import com.utils.CommonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.util.*;

import static com.utils.CommonUtils.getId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostTest extends BaseTest {
    @Test
    public void createEmployeeDetailsUsingPlainJson() {
        String id = getId();
        String s = "{\n" +
                "    \"EmpId\": \"" + id + "\",\n" +
                "    \"Name\": \"ABC\",\n" +
                "    \"Addresses\": [\n" +
                "      {\n" +
                "        \"Home\": {\n" +
                "          \"AddressLine1\": \"House No:123\",\n" +
                "          \"AddressLine2\": \"Near Azad Cinema\",\n" +
                "          \"City\": \"Ahmedabad\",\n" +
                "          \"District\": \"Ahmedabad\"\n" +
                "        },\n" +
                "        \"Shop\": {\n" +
                "          \"AddressLine1\": \"shop No:366\",\n" +
                "          \"AddressLine2\": \"Near Miraj Cinema\",\n" +
                "          \"City\": \"Ahmedabad\",\n" +
                "          \"District\": \"Ahmedabad\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        Response rs = given().baseUri(baseurl).header("content-type", ContentType.JSON).body(s)
                .post("/EmployeeDetails");
        assertThat(rs.statusCode(), is(equalTo(201)));
    }

    @Test
    public void createEmployeeDetailsUsingJsonFile() {
           CommonUtils commonUtils = new CommonUtils();
           String content=commonUtils.getFileContent("TestData/Employee.json");
           String postBody = content.replace("551", getId());
           Response rs = given().baseUri(baseurl).header("content-type", ContentType.JSON).body(postBody).log().all()
                    .post("/EmployeeDetails");
            assertThat(rs.statusCode(), is(equalTo(201)));
    }

    @Test
    public void createEmployeeDetailsUsingJavaObject() {
        Map<String, Object> root = new LinkedHashMap<>();
        root.put("EmpId", getId());
        root.put("Name", "ABD");
        List<Object> addresses = new ArrayList<>();

        Map<String, Object> address1 = new HashMap<>();
        Map<String, String> homeAddress = new HashMap<>();
        homeAddress.put("AddressLine1", "House No:123");
        homeAddress.put("AddressLine2", "House No:123");
        homeAddress.put("City", "Mumbai");
        homeAddress.put("District", "Mumbai");
        address1.put("Home", homeAddress);

        Map<String, Object> address2 = new HashMap<>();
        Map<String, String> officeAddress = new HashMap<>();
        officeAddress.put("AddressLine1", "House No:123");
        officeAddress.put("AddressLine2", "House No:123");
        officeAddress.put("City", "Mumbai");
        officeAddress.put("District", "Mumbai");
        address2.put("Shop", officeAddress);

        addresses.add(address1);
        addresses.add(address2);
        root.put("Addresses", addresses);

        Response rs = given().baseUri(baseurl).header("content-type", ContentType.JSON).body(root)
                .post("/EmployeeDetails");
        assertThat(rs.statusCode(), is(equalTo(201)));
    }

    @Test
    public void createEmployeeDetailsUsingJsonObject() {
        JSONObject root = new JSONObject();
        root.put("EmpId", getId());
        root.put("Name", "ABD");
        JSONArray addresses = new JSONArray();

        JSONObject address1 = new JSONObject();
        JSONObject homeAddress = new JSONObject();
        homeAddress.put("AddressLine1", "House No:123");
        homeAddress.put("AddressLine2", "House No:123");
        homeAddress.put("City", "Mumbai");
        homeAddress.put("District", "Mumbai");
        address1.put("Home", homeAddress);

        JSONObject address2 = new JSONObject();
        JSONObject officeAddress = new JSONObject();
        officeAddress.put("AddressLine1", "House No:123");
        officeAddress.put("AddressLine2", "House No:123");
        officeAddress.put("City", "Mumbai");
        officeAddress.put("District", "Mumbai");
        address2.put("Shop", officeAddress);

        addresses.put(address1);
        addresses.put(address2);
        root.put("Addresses", addresses);

        Response rs = given().baseUri(baseurl).header("content-type", ContentType.JSON).body(root.toMap())
                .log().all().post("/EmployeeDetails");
        assertThat(rs.statusCode(), is(equalTo(201)));
    }

    @Test
    public void createEmployeeDetailsUsingPojo() {

        Home home = new Home("House No:123", "House No:123", "Mumbai", "Mumbai");
        Shop shop = new Shop("House No:123", "House No:123", "Mumbai", "Mumbai");
        Address address = new Address(home, shop);
        ArrayList<Address> addresslist = new ArrayList<>();
        addresslist.add(address);
        EmployeeRoot employeeRoot = new EmployeeRoot(Integer.parseInt(getId()), "samir", addresslist);


        Response rs = given().baseUri(baseurl).header("content-type", ContentType.JSON).body(employeeRoot)
                .log().all().post("/EmployeeDetails");
        assertThat(rs.statusCode(), is(equalTo(201)));
    }


}