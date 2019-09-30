package com.epam.ta.core.service.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ConnectionHandler {
    private static final String PORT_IS_NOT_AVAILABLE_MSG = "[Specified port - %d is occupied by some process]";
    private static final String PORT_IS_AVAILABLE_MSG = "[Specified port - %d is available and ready to use]";
    private static final String PORT_HAS_BEEN_SET_FREE = "[Specified port - %d has been set free]";
    private static final String COMMAND_TO_RUN_BAT_FILE_TO_TERMINATE_PROCESS_ON_PORT = "cmd /c start %s/bat/killProcess.bat %d";
    private static final String FAILED_TO_EXECUTE_BAT_COMMANDS_MSG = "[Couldn't execute bat file commands - {%s}]";
    private static final String EXECUTING_MSG = "[Executing cmd command - {%s}]";

    private static Logger logger = LogManager.getLogger(ConnectionHandler.class);

    private ConnectionHandler(){
    }

    public static void makePortAvailableIfOccupied(int port) {
        if (!ConnectionHandler.isPortFree(port)) {
            makePortAvailable(port);
            logger.info(String.format(PORT_HAS_BEEN_SET_FREE,port));
        }
    }

    private static void makePortAvailable(int port){
        String runBatFileCommand = String.format(COMMAND_TO_RUN_BAT_FILE_TO_TERMINATE_PROCESS_ON_PORT, System.getProperty("user.dir"), port);
        try {
            Runtime.getRuntime().exec(runBatFileCommand);
            logger.info(String.format(EXECUTING_MSG,runBatFileCommand));
        } catch (IOException e) {
            logger.error(String.format(FAILED_TO_EXECUTE_BAT_COMMANDS_MSG, runBatFileCommand));
        }
    }

    private static boolean isPortFree(int port) {
        boolean isFree = TRUE;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info(String.format(PORT_IS_AVAILABLE_MSG, port));
        } catch (Exception e) {
            isFree = FALSE;
            logger.warn(String.format(PORT_IS_NOT_AVAILABLE_MSG, port));
        }
        return isFree;
    }

}