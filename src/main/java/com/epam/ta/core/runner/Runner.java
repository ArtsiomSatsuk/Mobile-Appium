package com.epam.ta.core.runner;

import com.beust.jcommander.JCommander;
import com.epam.ta.core.service.helpers.CLIParser;
import com.epam.ta.core.service.helpers.SuiteConfigurator;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        parseCLIParams(args);
        TestNG testNG = new TestNG();
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(SuiteConfigurator.createSuite());
        testNG.setXmlSuites(suites);
        testNG.run();
    }

    private static void parseCLIParams(String[] cmdArgs) {
        JCommander.
                newBuilder().
                addObject(CLIParser.getInstance()).
                build().
                parse(cmdArgs);
    }

}