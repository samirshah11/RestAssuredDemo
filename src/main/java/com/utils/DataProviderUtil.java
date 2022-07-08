package com.utils;

import com.aventstack.extentreports.gherkin.model.Given;
import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider
    public Object[][] getEmployeeData(){
        Object[][] employee = new Object[2][2];
        employee[0][0]="1";
        employee[0][1]="ABC";

        employee[1][0]="2";
        employee[1][1]="CBD";
        return employee;
    }
}