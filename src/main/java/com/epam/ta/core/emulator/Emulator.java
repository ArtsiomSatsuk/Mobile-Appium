package com.epam.ta.core.emulator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Emulator {
    private static final String FAILED_TO_EXECUTE_COMMANDS_MSG = "[Couldn't execute batch commands - {%s}]";
    private static final String EXECUTING_MSG = "[Executing cmd command - {%s}]";
    private static final String EMULATOR_CREATED_MSG = "[Emulator %s running on port %d]";
    private static final String EMULATOR_CLOSED_MSG = "[Emulator %s closed on port %d]";
    private static final String COMMAND_TO_RUN_EMULATOR_BAT_FILE = "cmd /c start %s/bat/emulatorRunner.bat %s %d";

    private static Logger logger = LogManager.getLogger(Emulator.class);

    private String name;
    private Process cmdProcess;
    private int port;

    public Emulator(int port, String name) {
        this.port = port;
        this.name = name;
    }

    public void run() {
        String runBatFileCommand = String.format(COMMAND_TO_RUN_EMULATOR_BAT_FILE, System.getProperty("user.dir"), name, port);
        try {
            cmdProcess = Runtime.getRuntime().exec(runBatFileCommand);
            logger.info(String.format(EXECUTING_MSG, runBatFileCommand));
        } catch (IOException e) {
            logger.error(String.format(FAILED_TO_EXECUTE_COMMANDS_MSG, runBatFileCommand));
        }
        logger.info(String.format(EMULATOR_CREATED_MSG, name, port));
    }

    public void close() {
        cmdProcess.descendants().forEach(ProcessHandle::destroyForcibly);
        logger.info(String.format(EMULATOR_CLOSED_MSG, name, port));
    }

}
