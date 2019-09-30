package com.epam.ta.core.listeners;

import com.epam.ta.core.service.helpers.ScreenshotMaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

public class Listener implements ITestListener, ISuiteListener {

    private static Logger logger = LogManager.getLogger(Listener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info(String.format("Test method started - %s", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(String.format("Test method succeeded - %s", iTestResult.getName()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        ScreenshotMaker.captureScreen(String.format("[Capturing test failure - %s]", iTestResult.getName()));
        logger.error(String.format("Test method failed - %s", iTestResult.getName()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.warn(String.format("Test method skipped - %s", iTestResult.getName()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.error(String.format("Test method failed but within success percentage - %s", iTestResult.getName()));
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info(String.format("Testing started - %s", iTestContext.getName()));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info(String.format("Testing finished - %s", iTestContext.getName()));
    }

    @Override
    public void onStart(ISuite iSuite) {
        logger.info(String.format("Suite started - %s", iSuite.getName()));
    }

    @Override
    public void onFinish(ISuite iSuite) {
        logger.info(String.format("Suite finished - %s", iSuite.getName()));
    }

}