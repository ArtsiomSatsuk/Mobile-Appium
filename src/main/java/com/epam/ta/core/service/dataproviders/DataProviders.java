package com.epam.ta.core.service.dataproviders;

import com.epam.ta.core.service.converters.CSVDataConverter;
import com.epam.ta.product.bo.Checklist;
import org.testng.annotations.DataProvider;

import java.util.List;

public class DataProviders {

    private static final String PATH_TO_ALL_CSV_FILES = "data/";
    private static final String CSV_FILE_WITH_CHECKLISTS = PATH_TO_ALL_CSV_FILES + "ChecklistsData.csv";
    private static final String CSV_FILE_WITH_NOTES_FOR_CHECKLISTS = PATH_TO_ALL_CSV_FILES + "TasksForChecklists.csv";

    @DataProvider(name = "checklists")
    public Object[][] provideChecklists() {
        Object[] beans = CSVDataConverter.getDataAsBean(Checklist.class, CSV_FILE_WITH_CHECKLISTS).toArray();
        Object[][] resultMatrix = new Object[beans.length][1];
        for (int i = 0; i < beans.length; i++) {
            resultMatrix[i][0] = beans[i];
        }
        return resultMatrix;
    }

    @DataProvider(name = "checklists and tasks")
    public Object[][] provideChecklistAndTasks() {
        Object[] checklists = CSVDataConverter.getDataAsBean(Checklist.class, CSV_FILE_WITH_CHECKLISTS).toArray();
        List<String[]> notes = CSVDataConverter.getDataAsListOfArrays(CSV_FILE_WITH_NOTES_FOR_CHECKLISTS);
        Object[][] resultMatrix = new Object[checklists.length][2];
        for (int i = 0; i < checklists.length; i++) {
            resultMatrix[i][0] = checklists[i];
            resultMatrix[i][1] = notes.get(i);
        }
        return resultMatrix;
    }

}