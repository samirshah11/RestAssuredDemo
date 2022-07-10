package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public final class ExtentReportUtil {
    static ExtentReports reports;
    static ExtentTest test;

    public static ExtentTest getTest() {
        return test;
    }

    static ExtentSparkReporter reporter;

    private ExtentReportUtil() {
    }

    public static void initReport() {
        reports = new ExtentReports();
        reporter = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "/test-output/spark.html"));
        reports.attachReporter(reporter);
    }

    public static void createTests(String testName) {
        test= reports.createTest(testName);
        test.log(Status.PASS,"pass");
    }

    public static void tearDown() {
        reports.flush();
    }

}