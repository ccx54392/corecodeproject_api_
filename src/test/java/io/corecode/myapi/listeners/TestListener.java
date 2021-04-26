package io.corecode.myapi.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;

public class TestListener implements ISuiteListener, ITestListener {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public void onStart(ISuite suite) {
        System.out.println("suite starts");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("suite ends");
    }
}
