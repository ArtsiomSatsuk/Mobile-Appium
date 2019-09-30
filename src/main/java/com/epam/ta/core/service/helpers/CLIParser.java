package com.epam.ta.core.service.helpers;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.epam.ta.core.enums.Device;

import java.util.Map;

public class CLIParser {
    private static final String PROPERTY_NAME = "deviceName";

    private static CLIParser instance;

    private CLIParser() {
    }

    @Parameter(names = {"--suite", "-s"}, description = "Path to test configuration xml file")
    private String suiteName = "Smoke"; //will be chosen by default in case if no 'suite' name will be provided

    @Parameter(names = {"--deviceName", "-dn"}, description = "Name of device to choose respective properties file", converter = DeviceConverter.class)
    private Device deviceName = Device.NEXUS; //will be chosen by default in case if no 'deviceName' will be provided

    private static class DeviceConverter implements IStringConverter<Device> {
        public Device convert(String s) {
            return Device.valueOf(s.toUpperCase());
        }
    }

    public static CLIParser getInstance() {
        if (instance == null) {
            instance = new CLIParser();
        }
        return instance;
    }

    public String getSuitePath() {
        return "suites/" + suiteName + "Tests.xml";
    }

    public Map<String, String> getSuiteParams() {
       return Map.of(PROPERTY_NAME, deviceName.getValue());
    }

}