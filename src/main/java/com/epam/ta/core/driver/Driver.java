package com.epam.ta.core.driver;

import com.epam.ta.config.Environment;
import com.epam.ta.core.enums.PathProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {
    private static final String APPIUM_STOPPED_MSG ="[Appium stopped on port - %d]";
    private static final String APPIUM_STARTED_MSG ="[Appium started on port - %d]";

    private static Logger logger = LogManager.getLogger(Driver.class);
    private static ThreadLocal<Driver> instance = new ThreadLocal<>();
    private static WebDriver driver;

    private Environment env;
    private AppiumDriverLocalService appiumDriverLocalService;

    private DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.NO_RESET, Boolean.FALSE);
        capabilities.setCapability(MobileCapabilityType.UDID, env.getUdid());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, env.getDeviceFullName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, env.getPlatformName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, env.getPlatformVersion());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, env.getAppPackage());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, env.getAutomationName());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, env.getAppActivity());
        capabilities.setCapability(MobileCapabilityType.APP, PathProvider.CHECKLIST_APK.getAbsolutePath());
        return capabilities;
    }

    private void initMobileDriver() {
        appiumDriverLocalService = createAppiumDriverLocalService();
        driver = new AndroidDriver(appiumDriverLocalService, getCapabilities());
    }

    private AppiumDriverLocalService createAppiumDriverLocalService() {
        appiumDriverLocalService = new AppiumServiceBuilder()
                .withIPAddress(env.getIpAddress())
                .usingPort(env.getAppiumPort())
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .build();
        appiumDriverLocalService.start();
        logger.info(String.format(APPIUM_STARTED_MSG, env.getAppiumPort()));
        return appiumDriverLocalService;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public WebDriver getDriver() {
        if (null == driver) {
            initMobileDriver();
        }
        return driver;
    }

    public static Driver getDriverInstance() {
        if (instance.get() == null) {
            instance.set(new Driver());
        }
        return instance.get();
    }

    public void launchMobileApp() {
        ((AndroidDriver) getDriver()).launchApp();
        logger.info("[Application opened]");
    }

    public void closeMobileApp() {
        ((AndroidDriver) getDriver()).closeApp();
        logger.info("[Application closed]");
    }

    public void stopAppium() {
        appiumDriverLocalService.stop();
        logger.info(String.format(APPIUM_STOPPED_MSG, env.getAppiumPort()));
    }

}