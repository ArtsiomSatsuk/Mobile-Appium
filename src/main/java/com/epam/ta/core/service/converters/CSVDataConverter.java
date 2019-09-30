package com.epam.ta.core.service.converters;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVDataConverter {
    private static Logger logger = LogManager.getLogger(CSVDataConverter.class);

    private static final String FAILED_TO_RETRIEVE_DATA_MSG = "[Cannot get data from CSV file - %s]";

    private CSVDataConverter() {
    }

    public static List<Object> getDataAsBean(Class classOfT, String pathToCSVFile) {
        List<Object> beans = null;
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pathToCSVFile))) {
            beans = (new CsvToBeanBuilder(bufferedReader))
                    .withType(classOfT)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (IOException e) {
            logger.error(String.format(FAILED_TO_RETRIEVE_DATA_MSG, pathToCSVFile));
        }
        return beans;
    }

    public static List<String[]> getDataAsListOfArrays(String pathToCSVFile) {
        CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(pathToCSVFile), StandardCharsets.UTF_8);
             CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser).build()) {
            rows = reader.readAll();
        } catch (IOException e) {
            logger.error(String.format(FAILED_TO_RETRIEVE_DATA_MSG, pathToCSVFile));
        }
        return rows;
    }


}