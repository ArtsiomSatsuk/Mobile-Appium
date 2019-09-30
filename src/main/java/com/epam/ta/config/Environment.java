package com.epam.ta.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${deviceName}.properties"
})
public interface Environment extends Config {

    @Key("udid")
    String getUdid();

    @Key("emulatorName")
    String getEmulatorName();

    @Key("deviceFullName")
    String getDeviceFullName();

    @Key("platformName")
    String getPlatformName();

    @Key("platformVersion")
    String getPlatformVersion();

    @Key("appPackage")
    String getAppPackage();

    @Key("automationName")
    String getAutomationName();

    @Key("appActivity")
    String getAppActivity();

    @Key("emulatorPort")
    int getEmulatorPort();

    @Key("appiumPort")
    int getAppiumPort();

    @Key("ipAddress")
    String getIpAddress();

}