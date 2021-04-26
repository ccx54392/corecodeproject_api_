package io.corecode.myapi.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ISuiteListener, ITestListener {
    @Override
    public void onStart(ISuite suite) {
        System.out.println("[INFO] Testing "+suite.getName()+" for corecode qa project");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("[INFO] All tests for "+suite.getName()+" have been executed");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[INFO] Test "+result.getName()+" completed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[TEST FAILED] Test "+result.getName()+" failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[TEST SKIPPED] Test "+result.getName()+" was skipped");
    }
}