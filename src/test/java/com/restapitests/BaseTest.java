package com.restapitests;

import com.aventstack.extentreports.Status;
import com.utils.ExtentReportUtil;
import com.utils.PropertyUtil;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class BaseTest {
    static Hashtable<String,String> propMap ;
    static String baseurl;

    @BeforeSuite
    public void initializeReport(){
        System.out.println("Initializing reports...........");
        ExtentReportUtil.initReport();
        propMap= PropertyUtil.loadProperties();
        if(System.getProperty("ApiHost")!=null){
            baseurl="http://"+System.getProperty("ApiHost")+":8082";
        }
        else {
            baseurl=propMap.get("baseurl");
        }

    }

    @BeforeMethod
    public void createTest(Method m){
        ExtentReportUtil.createTests(m.getName());

    }

    @AfterMethod
    public void storeResult(ITestResult result){
     if(!result.isSuccess()){
         ExtentReportUtil.getTest().log(Status.FAIL,String.valueOf(result.getThrowable()));
     }
    }

    @AfterSuite
    public void tearDown(){
        ExtentReportUtil.tearDown();
    }
}
