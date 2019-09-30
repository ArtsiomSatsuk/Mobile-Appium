package com.epam.ta.core.service.helpers;

import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;

public class SuiteConfigurator {

    public static XmlSuite createSuite() {
        XmlSuite suite = new XmlSuite();
        List<String> suiteFiles = new ArrayList<>();
        suite.setParameters(CLIParser.getInstance().getSuiteParams());
        suiteFiles.add(CLIParser.getInstance().getSuitePath());
        suite.setSuiteFiles(suiteFiles);
        return suite;
    }

    private SuiteConfigurator() {
    }

}